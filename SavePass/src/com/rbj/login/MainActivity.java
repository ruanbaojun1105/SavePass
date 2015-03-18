package com.rbj.login;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.rbj.db.PwdDAO;
import com.rbj.db.Tb_pwd;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	TextView user;
	TextView pass;
	private static Boolean isExit = false;
	private String stringuser;
	private String stringpass;
	public List<String> list1=new ArrayList<String>();
	public List<String> list2=new ArrayList<String>();
	ListView listView;
	public static MainActivity mainActivity = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		user=(TextView) findViewById(R.id.user2);
		pass=(TextView) findViewById(R.id.pass2);
		
		initUser();
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		//getIntentName=bundle.getString("intent").toString();
		stringuser=bundle.getString("user");
		stringpass=bundle.getString("pass");
		bundle.clear();
		user.setText(stringuser);
		pass.setText(stringpass);
		
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK)
			exitBy2Click();

		return false;
	}
	public void addUser(View view) {
		Intent intent = new Intent(getApplicationContext(),Regist.class);
		intent.putExtra("user",stringuser);
		intent.putExtra("pass", stringpass);
		intent.putExtra("intent", "main");
		startActivity(intent);
		MainActivity.this.finish();
	}
	private void initUser() {
		// TODO Auto-generated method stub
		//final Tb_pwd tb_pwd = new Tb_pwd();
		 PwdDAO pwdDAO = new PwdDAO(MainActivity.this);// 创建PwdDAO对象
		List<Tb_pwd> listinfos = pwdDAO.getScrollData(0,(int) pwdDAO.getCount());
		//String[] strInfos = null;// 定义字符串数组，用来存储收入信息
		//strInfos = new String[listinfos.size()];// 设置字符串数组的长度
		int m = 0;// 定义一个开始标识
		for (Tb_pwd tb_pwd: listinfos) {// 遍历List泛型集合
			// 将收入相关信息组合成一个字符串，存储到字符串数组的相应位置	
//			strInfos[m] = bw_bookmark.getid() +"【" + bw_bookmark.getFlag() + "】"
//					+ bw_bookmark.getWeburl()+bw_bookmark.getTime();
//			System.out.println(strInfos[m]);
			list1.add(tb_pwd.getUsername());
			list2.add(tb_pwd.getPassword());
			System.out.println(tb_pwd.getUsername());
			System.out.println(tb_pwd.getPassword());
			m++;
			}
		listView= (ListView) findViewById(R.id.listView);
		listView.setAdapter(new Myadapter());
	}

	// 双击退出
		@SuppressLint("ShowToast")
		private void exitBy2Click() {
			Timer tExit = null;
			if (isExit == false) {
				isExit = true; // 准备退出
				Toast.makeText(getApplicationContext(), "再按一次退出桌面", 0).show();
				tExit = new Timer();
				tExit.schedule(new TimerTask() {
					@Override
					public void run() {
						isExit = false; // 取消退出
					}
				}, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

			} else {
				Intent home = new Intent(Intent.ACTION_MAIN);
				home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				home.addCategory(Intent.CATEGORY_HOME);
				startActivity(home);
				// finish();
				// System.exit(0);
			}

		}
	private class Myadapter extends BaseAdapter {

		/**
		 * LISTVIEW顯示全部已註冊用戶
		 * 
		 */
		//final Tb_pwd tb_pwd = new Tb_pwd();
		final PwdDAO pwdDAO = new PwdDAO(MainActivity.this);// 创建PwdDAO对象
		
		int y=(int) pwdDAO.getCount();
		@Override
		public int getCount() {
			// TODO Auto-generated method stuba
			return y;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int positiy, View view, ViewGroup viewGroup) {
			// TODO Auto-generated method stub
			view=View.inflate(MainActivity.this, R.layout.list_style, null);
			TextView name=(TextView)view.findViewById(R.id.username);
		    TextView realname=(TextView)view.findViewById(R.id.password);	
			    Log.i("返回画面的位置", positiy+"");
				// 获取所有收入信息，并存储到List泛型集合中
				
			    for (int i =0; i <=positiy; i++) {
			    	name.setText(list1.get(i).toString());
					realname.setText(list2.get(i).toString());
				}
					System.gc();
			return view;
		}
		
	}
	
}
