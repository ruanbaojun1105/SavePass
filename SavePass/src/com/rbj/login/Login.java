package com.rbj.login;


import java.util.List;

import com.rbj.db.PwdDAO;
import com.rbj.db.Tb_pwd;

import android.opengl.Visibility;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {

	private String user;
	private String pass;
	private boolean flag=false;
	private EditText usereEditText;
	private EditText passEditText;
	private Button loginbButton;
	private Button registbButton;
	private Button resetButton;
	public static Login login = null;
	private PwdDAO pwdDAO = new PwdDAO(Login.this);// 创建InaccountDAO对象
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		login=this;
		
		usereEditText=(EditText) findViewById(R.id.user);
		passEditText=(EditText) findViewById(R.id.pass);
		
		loginbButton=(Button) findViewById(R.id.login);		
		registbButton=(Button) findViewById(R.id.regist);
		resetButton=(Button) findViewById(R.id.reset);
		try {
			if (pwdDAO.getCount()<1) {
				registbButton.setVisibility(View.VISIBLE);
				registbButton.setFocusable(true);
				resetButton.setVisibility(View.GONE);
				resetButton.setFocusable(false);
			}else {
				resetButton.setVisibility(View.VISIBLE);
				resetButton.setFocusable(true);
				registbButton.setVisibility(View.GONE);
				registbButton.setFocusable(false);
			}
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("出现异常");
			e.printStackTrace();
		}
		
		resetButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				for (int i = 1; i <=pwdDAO.getMaxId(); i++) 
				{	pwdDAO.detele(i);
					Log.e("删除了：",i+"条数据");
				}
				startActivity(new Intent(getApplicationContext(),Login.class));
				Toast.makeText(getApplicationContext(), "已重置完成", 0).show();
				
			}
		});
		loginbButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ShowInfoToLogin();
				if (flag==true) {
					Toast.makeText(getApplicationContext(), "登陸成功", 0).show();
					android.util.Log.i("提示：", "登录成功了");
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(),
						MainActivity.class);
				intent.putExtra("user", usereEditText.getText().toString());
				intent.putExtra("pass", passEditText.getText().toString());
				startActivity(intent);
				login.finish();
				}else {
					Toast.makeText(getApplicationContext(), "登陸失敗，如若第一次进入此应用可以先添加保存一个账户登陆吧！", 0).show();
				}
			}
		});
		registbButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),Regist.class).putExtra("intent", "login"));
				login.finish();
			}
		});
		
	}
	public void ShowInfoToLogin() {// 用来根据传入的管理类型，显示相应的信息
		user=usereEditText.getText().toString();
		pass=passEditText.getText().toString();
		String[] strInfos = null;// 定义字符串数组，用来存储收入信息
		// ArrayAdapter<String> arrayAdapter = null;// 创建ArrayAdapter对象
		// 获取所有收入信息，并存储到List泛型集合中
		List<Tb_pwd> listinfos = pwdDAO.getScrollData(0,
				(int) pwdDAO.getCount());
		strInfos = new String[listinfos.size()];// 设置字符串数组的长度
		int m = 0;// 定义一个开始标识
		for (Tb_pwd tb_pwd : listinfos) {// 遍历List泛型集合
			// 将收入相关信息组合成一个字符串，存储到字符串数组的相应位置
			strInfos[m] = tb_pwd.getId() + "|" + tb_pwd.getUsername() + "|"
					+ tb_pwd.getPassword() + "|" + tb_pwd.getName();
			System.out.println(strInfos[m]);
			Log.i(tb_pwd.getUsername(), tb_pwd.getPassword());
			String test1=tb_pwd.getUsername().toString();
			String test2=tb_pwd.getPassword().toString();
			//Log.i(test1+"++++++++", test2);
			Log.i(user, pass+"刚输入的！");
			if (user.equals(test1)
					&& pass.equals(test2)) {
				//System.out.println(tb_pwd.getName());
				Log.i(tb_pwd.getUsername(), tb_pwd.getPassword());
				System.out.println(tb_pwd.getPassword());
				//nichen=tb_pwd.getName();				
				
				flag = true;
				break;
			}
			m++;// 标识加1

		}
		// 使用字符串数组初始化ArrayAdapter对象
		// arrayAdapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1, strInfos);
		// lvinfo.setAdapter(arrayAdapter);// 为ListView列表设置数据源
	}


	
}
