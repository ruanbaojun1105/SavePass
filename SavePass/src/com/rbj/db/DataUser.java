package com.rbj.db;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;


public final class DataUser {
	//用户信息
    public static final String[][] USER = {{"baojun","123456","宝军"}    };
    String username[]=null, password[]=null, name[]=null,email[]=null;
    int id=0;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String[] getUsername() {
		return username;
	}
	public String[] getEmail() {
		return email;
	}
	public void setEmail(String[] email) {
		this.email = email;
	}
	public void setUsername(String[] username) {
		this.username = username;
	}
	public String[] getPassword() {
		return password;
	}
	public void setPassword(String[] password) {
		this.password = password;
	}
	public String[] getName() {
		return name;
	}
	public void setName(String[] name) {
		this.name = name;
	}
	public void DataUserFirst(Context context) {
		// TODO Auto-generated constructor stub
		   
			 Tb_pwd tb_pwd=new Tb_pwd();
				PwdDAO pwdDAO = new PwdDAO(context);// 创建PwdDAO对象
			// TODO Auto-generated constructor stub
			   for (int i = 0; i < USER.length; i++) {
				tb_pwd.setUsername(USER[i][0]);
				tb_pwd.setPassword(USER[i][1]);
				tb_pwd.setName(USER[i][2]);
				tb_pwd.setId(i+1);
				pwdDAO.add(tb_pwd);		
				android.util.Log.i(USER[i][0], USER[i][1]);
				android.util.Log.i("昵称", USER[i][2]);
				android.util.Log.i("用户数据查询", pwdDAO.find(USER[i][0]).toString());
				
			}
			   //Toast.makeText(context, "数据加载OK", 0).show();	
			   
		
			   
	}
	public int InfoGet(Context context) {// 用来根据传入的管理类型，显示相应的信息		
		String[] strInfos = null;// 定义字符串数组，用来存储收入信息
		PwdDAO pwdDAO = new PwdDAO(context);// 创建PwdDAO对象
		
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
			Log.i(test1+"++++++++", test2);			
//			name[m]=tb_pwd.getName().toString();
//			username[m]=tb_pwd.getUsername().toString();
//			password[m]=tb_pwd.getPassword().toString();
//			email[m]=tb_pwd.getEmail().toString();
			id=tb_pwd.getId();
				System.out.println(id);
				Log.i(tb_pwd.getName()+"---"+tb_pwd.getUsername(), tb_pwd.getPassword()+"---"+tb_pwd.getEmail());
				System.out.println("密码："+tb_pwd.getPassword()+"\t\temail:"+tb_pwd.getEmail());										
			m++;// 标识加1
			}
		return id;

		}
		// 使用字符串数组初始化ArrayAdapter对象
		// arrayAdapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1, strInfos);
		// lvinfo.setAdapter(arrayAdapter);// 为ListView列表设置数据源

	}
	

	