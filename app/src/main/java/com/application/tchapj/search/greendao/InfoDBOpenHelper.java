package com.application.tchapj.search.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// 创建数据库
public class InfoDBOpenHelper extends SQLiteOpenHelper {

	public InfoDBOpenHelper(Context context) {
		super(context, "info.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table info (_id integer primary key autoincrement,name varchar(20),phone varchar(20))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
