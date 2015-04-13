package cn.believeus.main;

import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ArrayList<HashMap<String, Object>> listItem;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 开启一个线程初始化列表数据
		new Thread(new Runnable() {

			@Override
			public void run() {
				final ListView musicItems = (ListView) findViewById(R.id.musicItems);
				listItem = new ArrayList<HashMap<String, Object>>();

				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put(Variables.VIDEO_NAME, "香水有毒");
				map.put(Variables.VIDEO_DESC,"我曾經愛過這樣一個男人他說我是世上最美的女人我為他保留著那一份天真關上愛別人的門");
				map.put(Variables.VIDEO_URL, "rtsp://192.168.1.114/song.mp4");
				listItem.add(map);
				// 这里要从网络获取数据
				AdapterMusicItem adapter = new AdapterMusicItem(
						MainActivity.this, listItem);
				musicItems.setAdapter(adapter);
				// 给每一个listViewItem添加点击事件
				musicItems.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						 Intent intent=new Intent(); 
						 intent.putExtra(Variables.VIDEO_NAME,listItem.get(position).get(Variables.VIDEO_NAME).toString());
						 intent.putExtra(Variables.VIDEO_DESC,listItem.get(position).get(Variables.VIDEO_DESC).toString());
						 intent.putExtra(Variables.VIDEO_URL,listItem.get(position).get(Variables.VIDEO_URL).toString());
						 intent.setClass(MainActivity.this, ZVoicePlayer.class);
						 startActivity(intent);
						 
					}

				});
			}
		}).start();
		/*
		 * final Button download = (Button)findViewById(R.id.btnDownload);
		 * download.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { String basepath="";
		 * //优先存储在外设SDCard上创建存储目录 String storageState =
		 * Environment.getExternalStorageState(); if
		 * (storageState.equals(Environment.MEDIA_MOUNTED)) { String
		 * storageDirectory =
		 * Environment.getExternalStorageDirectory().toString();
		 * basepath=storageDirectory+File.separator+"zvoice/store";
		 * Dialog.showToast(MainActivity.this,"在外设SDCard创建目录:"+basepath);
		 * //如果外置SDCard不存在就存储在内置存储空间创建存储目录 }else { String storageDirectory =
		 * Environment.getDataDirectory().getPath();
		 * basepath=storageDirectory+File.separator+"zvoice/store";
		 * Dialog.showToast(MainActivity.this,"在手机内置空间上创建目录:"+basepath); }
		 * download.setEnabled(false); final String createpath=basepath; new
		 * Thread(new Runnable() {
		 * 
		 * @Override public void run() { if (!new File(createpath).exists()) {
		 * Folder.initFolder(createpath); } String
		 * host=PropertiesUtil.findValue(MainActivity.this,Variables.HOST);
		 * String
		 * port=PropertiesUtil.findValue(MainActivity.this,Variables.PORT);
		 * MydfsTrackerServer trackerServer=new
		 * MydfsTrackerServer(host,Integer.parseInt(port)); InputStream
		 * inputStream = trackerServer.receiveData(""); } }).start(); } });
		 */
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
