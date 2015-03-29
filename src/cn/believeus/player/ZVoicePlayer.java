package cn.believeus.player;

import cn.believeus.component.ZVoiceVideoPlayer;
import cn.believeus.handler.LyricHandler;
import cn.believeus.main.R;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

public class ZVoicePlayer extends Activity {
	private static final String TAG = ZVoicePlayer.class.getName();
	private ZVoiceVideoPlayer player;
	private String playFilePath;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.zvioce_player_ui);
		player = (ZVoiceVideoPlayer) findViewById(R.id.videoPlayer);
		/*String filename = "view.mp4";
		playFilePath = SDCardUtil.findFromSdCard(filename);*/
        playFilePath="android.resource://cn.believeus.main/"+R.raw.vedio;
		/*File file=new File(playFilePath);
		if (!file.exists()) {
			Toast.makeText(this, "文件不存在",Toast.LENGTH_LONG).show();
		}*/
		Uri uri = Uri.parse(playFilePath);
		player.setVideoURI(uri);
		player.requestFocus();
		player.start();
		
	}
	@Override
	protected void onDestroy() {
		LyricHandler lyricHandler = player.getLyricHandler();
		lyricHandler.stop();
		super.onDestroy();
	}
}
