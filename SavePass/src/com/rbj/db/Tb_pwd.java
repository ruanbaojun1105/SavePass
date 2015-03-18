package com.rbj.db;

public class Tb_pwd// 密码数据表实体类
{
	private String password;// 定义字符串，表示用户密码
	private String username;
	private String name;
	private String email;
	private int _id;
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	

	public String getName() {
		return name;
	}

	public int getId() {
		return _id;
	}

	public void setId(int id) {
		this._id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Tb_pwd()// 默认构造函数
	{
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Tb_pwd(int id,String username,String password,String name,String email)// 定义有参构造函数
	{
		super();
		this.password = password;// 为密码赋值
		this.username = username;//登录名
		this._id=id;
		this.name = name;//昵称
	}

	public String getPassword()// 定义密码的可读属性
	{
		return password;
	}

	public void setPassword(String password)// 定义密码的可写属性
	{
		this.password = password;
	}
}
