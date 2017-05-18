package com.product.jiamiao.healthbooks.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.product.jiamiao.healthbooks.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Reinhard Tristan Eugen Heydrich on 2016/11/24 09:00
 */
public class GetDataBaseUtils {
	private final int BUFFER_SIZE = 40000;
	public static final String DB_NAME = "location.db"; // 保存的数据库文件名（raw文件夹下数据库文件的名称）
	public static final String PACKAGE_NAME = "com.product.jiamiao.healthbooks"; // sd卡数据库在此位置下（修改为你项目所在的包名）
	public static final String DB_PATH = "/data"
			+ Environment.getDataDirectory().getAbsolutePath() + "/"
			+ PACKAGE_NAME;
	// 在手机里存放数据库的位置(/data/data/com.example.proName（你的项目的包名）/dictionarydanci.db（数据库文件名称）)
	private SQLiteDatabase database;
	private Context context;

	public GetDataBaseUtils(Context context) {
		this.context = context;
	}

	public SQLiteDatabase getDatabase() {
		return database;
	}

	public void setDatabase(SQLiteDatabase database) {
		this.database = database;
	}

	public void openDatabase() {
		this.database = this.openDatabase(DB_PATH + "/" + DB_NAME);
	}

	private SQLiteDatabase openDatabase(String dbfile) {
		try {
			if (!(new File(dbfile).exists())) {
				// 判断数据库文件是否存在，若不存在则执行导入，否则直接打开数据库
				InputStream is = this.context.getResources().openRawResource(
						R.raw.location); // 欲导入的数据库
				FileOutputStream fos = new FileOutputStream(dbfile);
				byte[] buffer = new byte[BUFFER_SIZE];
				int count = 0;
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count);
				}
				fos.close();
				is.close();
			}
			SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile,
					null);
			return db;
		} catch (FileNotFoundException e) {
			LogUtils.e("没有找到数据库。");
			e.printStackTrace();
		} catch (IOException e) {
			LogUtils.e( "IO异常。");
			e.printStackTrace();
		}
		return null;
	}

	public void closeDatabase() {
		this.database.close();
	}
}