package cn.believeus.main;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import cn.believeus.main.R;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class ZVoicePlayer extends Activity {
	private VideoView vvZvoicePlayerVideoview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.zvioce_player_ui);
		if (!LibsChecker.checkVitamioLibs(this)){
			return;
		}
		Intent intent = getIntent();
		String videoName = intent.getStringExtra(Variables.VIDEO_NAME);
		String videoUrl = intent.getStringExtra(Variables.VIDEO_URL);
		String videoDesc = intent.getStringExtra(Variables.VIDEO_DESC);
		
		TextView tvVideoName = (TextView)findViewById(R.id.tvZvoicePlayerVideoName);
		TextView tvVideoDesc = (TextView)findViewById(R.id.tvZvoicePlayerVideoDesc);
		vvZvoicePlayerVideoview = (VideoView) findViewById(R.id.vvZvoicePlayerVideoview);
		
		tvVideoName.setText(videoName);
		tvVideoDesc.setText(videoDesc);
		// 访问的时候注意需要将防火墙的端口开放
		vvZvoicePlayerVideoview.setVideoURI(Uri.parse(videoUrl));
		vvZvoicePlayerVideoview.setMediaController(new MediaController(this));
		vvZvoicePlayerVideoview.start();

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
