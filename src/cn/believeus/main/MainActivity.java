package cn.believeus.main;

import java.io.File;
import java.io.InputStream;

import mydfs.storage.server.MydfsTrackerServer;
import cn.believeus.component.Dialog;
import cn.believeus.dbhelp.DBManager;
import cn.believeus.mydfs.Folder;
import cn.believeus.util.PropertiesUtil;
import cn.believeus.variables.Variables;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity
{
	private Button btnStart;
	private DBManager dbManager;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        dbManager=new DBManager(MainActivity.this);
        btnStart=(Button)findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				/*Intent intent=new Intent();
				intent.setClass(MainActivity.this, ZVoicePlayer.class);
				startActivity(intent);*/
				Dialog.showToast(MainActivity.this, "你点击了我!");
			}
		});
        final Button download = (Button)findViewById(R.id.btnDownload);
        download.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String basepath="";
				//优先存储在外设SDCard上创建存储目录
				String storageState = Environment.getExternalStorageState();
				if (storageState.equals(Environment.MEDIA_MOUNTED)) {
					String storageDirectory = Environment.getExternalStorageDirectory().toString();
					basepath=storageDirectory+File.separator+"zvoice/store";
					Dialog.showToast(MainActivity.this,"在外设SDCard创建目录:"+basepath);
				//如果外置SDCard不存在就存储在内置存储空间创建存储目录
				}else {
					String storageDirectory = Environment.getDataDirectory().getPath();
					basepath=storageDirectory+File.separator+"zvoice/store";
					Dialog.showToast(MainActivity.this,"在手机内置空间上创建目录:"+basepath);
				}
				download.setEnabled(false);
				final String createpath=basepath;
				new Thread(new Runnable() {
					
					@Override
					public void run() {
					if (!new File(createpath).exists()) {
						Folder.initFolder(createpath);
					}
					String host=PropertiesUtil.findValue(MainActivity.this,Variables.HOST);
					String port=PropertiesUtil.findValue(MainActivity.this,Variables.PORT);
					MydfsTrackerServer trackerServer=new MydfsTrackerServer(host,Integer.parseInt(port));
					InputStream inputStream = trackerServer.receiveData("");
					}
				}).start();
			}
		});
    }
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    }
}
