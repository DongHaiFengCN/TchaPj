package com.application.tchapj.search.greendao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

// 数据库操作类 提供增删改查
public class InfoDao {

	// 数据库帮助类
	private InfoDBOpenHelper helper;

    // 构造方法
	public InfoDao(Context context) {
		helper = new InfoDBOpenHelper(context);
	}

	/**
	 * 添加一个学生信息
	 * @param name 姓名
	 * @param phone 电话
	 * @return 添加在数据库的第几行
	 */
	// 添加数据
	public long add(String name,String phone){
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", name);
		values.put("phone", phone);
		long rowid = db.insert("info", null, values);
		db.close();
		return rowid;
	}

	/**
	 * 添加一个学生信息
	 * @param name 姓名
	 *
	 * @return 添加在数据库的第几行
	 */
	// 添加数据
	public long add(String name){
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", name);
		long rowid = db.insert("info", null, values);
		db.close();
		return rowid;
	}

	/**
	 * 删除一条学生信息
	 * @param id 学生id
	 * @return 影响了几行，0代表删除失败
	 */
	// 删除数据
	public int delete(String id){
		SQLiteDatabase db = helper.getWritableDatabase();
		int result = db.delete("info", "_id=?", new String[]{id});
		db.close();
		return result;
	}

	/**
	 * 获取所有的学生信息
	 * @return
	 */
	// 查询数据
	public List<Info> findAll(){
		SQLiteDatabase db = helper.getReadableDatabase();
		List<Info> infos = new ArrayList<Info>();
		Cursor cursor = db.query("info", new String[]{"_id","name","phone"}, null, null, null, null, null);
		while(cursor.moveToNext()){
			Info info = new Info();
			String id = cursor.getString(0);
			String name = cursor.getString(1);
			String phone = cursor.getString(2);
			info.setId(id);
			info.setName(name);
			info.setPhone(phone);
			infos.add(info);
		}
		cursor.close();
		db.close();
		return infos;
	}

}