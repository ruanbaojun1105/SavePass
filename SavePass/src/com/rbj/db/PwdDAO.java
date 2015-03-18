package com.rbj.db;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PwdDAO {
	private DBOpenHelper helper;// 创建DBOpenHelper对象
	private SQLiteDatabase db;// 创建SQLiteDatabase对象

	public PwdDAO(Context context)// 定义构造函数
	{
		helper = new DBOpenHelper(context);// 初始化DBOpenHelper对象
	}

	/**
	 * 添加密码信息
	 * 
	 * @param tb_pwd
	 */
	public void add(Tb_pwd tb_pwd) {
		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
		// 执行添加密码操作
		db.execSQL(
				"insert into tb_pwd (_id,username,password,name,email) values (?,?,?,?,?)",
				new Object[] { 
						tb_pwd.getId(),
						tb_pwd.getUsername() ,
						tb_pwd.getPassword(),
						tb_pwd.getName(),
						tb_pwd.getEmail()});

	}

	/**
	 * 设置密码信息
	 * 
	 * @param tb_pwd
	 */
	public void update(Tb_pwd tb_pwd) {
		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
		// 执行修改密码操作
		db.execSQL("update tb_pwd set _id = ?, username = ?,password = ?, name = ?, email = ?",
				new Object[] { 
				tb_pwd.getId(),
				tb_pwd.getUsername() ,
				tb_pwd.getPassword(),
				tb_pwd.getName(),
				tb_pwd.getEmail()});
		
	}

	/**
	 * 查找密码信息
	 * 
	 * @return
	 */
	public Tb_pwd find(String username) {
		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
		// 查找密码并存储到Cursor类中
		Cursor cursor = db.rawQuery(
				"select _id,username,password,name,email from tb_pwd where username = ?", new String[] { String.valueOf(username)});
		if (cursor.moveToNext())// 遍历查找到的密码信息
		{
			// 将密码存储到Tb_pwd类中
			return new Tb_pwd(
					cursor.getInt(cursor.getColumnIndex("_id")),
					cursor.getString(cursor.getColumnIndex("username")), 
					cursor.getString(cursor.getColumnIndex("password")), 
					cursor.getString(cursor.getColumnIndex("name")),
					cursor.getString(cursor.getColumnIndex("email")));
		}
		return null;// 如果没有信息，则返回null
	}

	/**
	 * 刪除用户信息
	 * 
	 * @param ids
	 */
	public void detele(Integer... ids) {
		if (ids.length > 0)// 判断是否存在要删除的id
		{
			StringBuffer sb = new StringBuffer();// 创建StringBuffer对象
			for (int i = 0; i < ids.length; i++)// 遍历要删除的id集合
			{
				sb.append('?').append(',');// 将删除条件添加到StringBuffer对象中
			}
			sb.deleteCharAt(sb.length() - 1);// 去掉最后一个“,“字符
			db = helper.getWritableDatabase();// 创建SQLiteDatabase对象
			// 执行删除便签信息操作
			db.execSQL("delete from tb_pwd where _id in (" + sb + ")",
					(Object[]) ids);
		}
	}

	/**
	 * 获取用户信息
	 * 
	 * @param start
	 *            起始位置
	 * @param count
	 *            每页显示数量
	 * @return
	 */
	public List<Tb_pwd> getScrollData(int start, int count) {
		List<Tb_pwd> tb_pwd = new ArrayList<Tb_pwd>();// 创建集合对象
		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
		// 获取所有便签信息
		Cursor cursor = db.rawQuery("select * from tb_pwd limit ?,?",
				new String[] { String.valueOf(start), String.valueOf(count) });
		while (cursor.moveToNext())// 遍历所有的便签信息
		{
			// 将遍历到的便签信息添加到集合中
			tb_pwd.add(new Tb_pwd(
					cursor.getInt(cursor.getColumnIndex("_id")),
					cursor.getString(cursor.getColumnIndex("username")), 
					cursor.getString(cursor.getColumnIndex("password")), 
					cursor.getString(cursor.getColumnIndex("name")),
					cursor.getString(cursor.getColumnIndex("email"))));
		}
		return tb_pwd;// 返回集合
	}

	/**
	 * 获取总记录数
	 * 
	 * @return
	 */
	public long getCount() {
		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
		Cursor cursor = db.rawQuery("select count(_id) from tb_pwd", null);// 获取便签信息的记录数
		if (cursor.moveToNext())// 判断Cursor中是否有数据
		{
			return cursor.getLong(0);// 返回总记录数
		}
		return 0;// 如果没有数据，则返回0
	}

	/**
	 * 获取便签最大编号
	 * 
	 * @return
	 */
	public int getMaxId() {
		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
		Cursor cursor = db.rawQuery("select max(_id) from tb_pwd", null);// 获取便签信息表中的最大编号
		while (cursor.moveToLast()) {// 访问Cursor中的最后一条数据
			return cursor.getInt(0);// 获取访问到的数据，即最大编号
		}
		return 0;// 如果没有数据，则返回0
	}
	
}
