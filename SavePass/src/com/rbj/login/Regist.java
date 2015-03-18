package com.rbj.login;

import com.rbj.db.DataUser;
import com.rbj.db.PwdDAO;
import com.rbj.db.Tb_pwd;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Regist extends Activity {


	EditText usereEditText;
	EditText passEditText;
	Button readybButton;
	int id;
	String intentsString;
	public static Regist regist = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.regist);
		regist=this;
		usereEditText =(EditText) findViewById(R.id.user1);
		passEditText =(EditText) findViewById(R.id.pass1);
		readybButton =(Button) findViewById(R.id.ready);
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		intentsString=bundle.getString("intent");
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			intentSet();
			
		}
		return false;
	
	}
	public void ready(View view) {
		final Tb_pwd tb_pwd = new Tb_pwd();
		final PwdDAO pwdDAO = new PwdDAO(Regist.this);// 创建PwdDAO对象
		final DataUser dataUser = new DataUser();// 更新数据库，添加初始输出
		if (passEditText.getText().toString().trim().equals("") || usereEditText
				.getText().toString().trim().equals("")) {
			Toast.makeText(getApplicationContext(), "用戶名和密碼二者不能有一项为空!", 0).show();
		}else {
					tb_pwd.setUsername(usereEditText.getText().toString()
							.trim());
					tb_pwd.setPassword(passEditText.getText().toString()
							.trim());

					id =dataUser.InfoGet(Regist.this);
					tb_pwd.setId(id+1);
					pwdDAO.add(tb_pwd);// 存入数据库
					dataUser.InfoGet(Regist.this);//再次查看数据库中的数据
					Toast.makeText(getApplicationContext(), "完成添加", 0).show();
					
					intentSet();
				}
				
		
	}
	public void intentSet() {
		if (intentsString.equals("main")) {
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(),
					MainActivity.class);
			Intent intent1=getIntent();
			Bundle bundle1=intent1.getExtras();
			intent.putExtra("user", bundle1.getString("user").toString());
			intent.putExtra("pass", bundle1.getString("pass").toString());
			startActivity(intent);
		}else {
			startActivity(new Intent(getApplicationContext(),Login.class));
		}
		Regist.this.finish();
	}
	
}
