package cn.believeus.main;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

//参考：http://blog.csdn.net/liuhe688/article/details/6715983
public class DBManager {
	private DatabaseHelper helper;
	private SQLiteDatabase db;
	private static final String LOG_TAG = DBManager.class.getName();

	public DBManager(Context context) {
		Log.d(LOG_TAG, "DBManager --> Constructor");
		helper = new DatabaseHelper(context);
		// 因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0,
		// mFactory);
		// 所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
		db = helper.getWritableDatabase();
	}

	public void save(TVideo video) {
		Log.d(LOG_TAG, "DBManager --> add");
		// 采用事务处理，确保数据完整性
		db.beginTransaction(); // 开始事务
		try {

			String hql = "INSERT INTO " + DatabaseHelper.TABLE_NAME
					+ " VALUES(null, ?, ?, ?,?, ?, ?,?)";
			String videoName = video.getVideoname();
			String desc = video.getDesc();
			String videoUrl = video.getVideoUrl();
			String thumb1 = video.getThumb1();
			String thumb2 = video.getThumb2();
			String thumb3 = video.getThumb3();
			String thumb4 = video.getThumb4();
			Object[] args = new Object[] { videoName, desc, videoUrl, thumb1,
					thumb2, thumb3, thumb4 };
			db.execSQL(hql, args);
			// 带两个参数的execSQL()方法，采用占位符参数？，把参数值放在后面，顺序对应
			// 一个参数的execSQL()方法中，用户输入特殊字符时需要转义
			// 使用占位符有效区分了这种情况
			db.setTransactionSuccessful(); // 设置事务成功完成
		} finally {
			db.endTransaction(); // 结束事务
		}
	}

	public void update(String column, String value) {
		Log.d(LOG_TAG, "DBManager --> update");
		ContentValues contentValues = new ContentValues();
		contentValues.put(column, value);
		db.update(DatabaseHelper.TABLE_NAME, contentValues, null, null);
	}

	/**
	 * delete old ZVoiceVideo
	 * 
	 * @param ZVoiceVideo
	 */
	public void delete(Integer id) {
		Log.d(LOG_TAG, "DBManager --> deleteOldZVoiceVideo");
		db.delete(DatabaseHelper.TABLE_NAME, "_id = ?",
				new String[] { String.valueOf(id) });
	}

	public List<TVideo> queryList() {
		Log.d(LOG_TAG, "DBManager --> query");
		ArrayList<TVideo> zvoiceVideos = new ArrayList<TVideo>();
		String sql = "SELECT * FROM " + DatabaseHelper.TABLE_NAME;
		Cursor c = db.rawQuery(sql, null);
		while (c.moveToNext()) {
			TVideo video = new TVideo();
			video.set_id(c.getInt(c.getColumnIndex("_id")));
			video.setVideoname(c.getString(c.getColumnIndex("videoname")));
			video.setDesc(c.getString(c.getColumnIndex("desc")));
			video.setVideoUrl(c.getString(c.getColumnIndex("videoUrl")));
			video.setThumb1(c.getString(c.getColumnIndex("thumb1")));
			video.setThumb2(c.getString(c.getColumnIndex("thumb2")));
			video.setThumb3(c.getString(c.getColumnIndex("thumb3")));
			video.setThumb4(c.getString(c.getColumnIndex("thumb4")));
			zvoiceVideos.add(video);
		}
		
		return zvoiceVideos;
	}

	public TVideo findByProperty(String property, String value) {
		String sql = "select * from " + DatabaseHelper.TABLE_NAME + " where "
				+ property + "=?";
		TVideo video = new TVideo();
		Cursor c = db.rawQuery(sql, new String[] { value });
		filling(video, c);
		return video;
	}

	public TVideo findById(int id) {
		String sql = "select * from " + DatabaseHelper.TABLE_NAME
				+ " where _id=?";
		TVideo video = new TVideo();
		Cursor c = db.rawQuery(sql, new String[] { String.valueOf(id) });
		filling(video, c);
		return video;

	}

	private void filling(TVideo video, Cursor c) {
		while (c.moveToNext()) {
			video.set_id(c.getInt(c.getColumnIndex("_id")));
			video.setVideoname(c.getString(c.getColumnIndex("videoname")));
			video.setDesc(c.getString(c.getColumnIndex("desc")));
			video.setVideoUrl(c.getString(c.getColumnIndex("videoUrl")));
			video.setThumb1(c.getString(c.getColumnIndex("thumb1")));
			video.setThumb2(c.getString(c.getColumnIndex("thumb2")));
			video.setThumb3(c.getString(c.getColumnIndex("thumb3")));
			video.setThumb4(c.getString(c.getColumnIndex("thumb4")));
		}
		c.close();
	}

	/**
	 * close database
	 */
	public void closeDB() {
		Log.d(LOG_TAG, "DBManager --> closeDB");
		// 释放数据库资源
		db.close();
	}

}