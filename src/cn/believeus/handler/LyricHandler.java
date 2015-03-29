package cn.believeus.handler;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.VideoView;

public class LyricHandler extends Handler implements Runnable {
	private static final int BEGIN = 0;
	private static final int PLAYING = 1;
	private static final int STOP = 2;
	private boolean flag=true;
	private static final String TAG = LyricHandler.class.getName();
	private VideoView videoView;
	private Thread thread = new Thread(this);

	public LyricHandler(VideoView videoView) {
		this.videoView = videoView;
	}

	@Override
	public void handleMessage(Message msg) {
		if (msg.arg1 == BEGIN) {
			flag=true;
			if(!thread.isAlive()){
				thread.start();
			}
		} else if (msg.arg1 == PLAYING) {
			if (videoView.isPlaying()) {
				//获取当前播放位置
				int position = videoView.getCurrentPosition();
				int duration=videoView.getDuration();
				if(position==duration){
					flag=false;
				}
				Log.i(TAG, "position:" + position);
			}
		} else if (msg.arg1 == STOP) {
			flag=false;
		}
	}

	@Override
	public void run() {
		while (flag) {
			Message message = new Message();
			message.arg1 = PLAYING;
			this.sendMessage(message);
		}
	}

	public void start() {
		Message message = new Message();
		message.arg1 = LyricHandler.BEGIN;
		this.sendMessage(message);
	}
	public  void stop(){
		Message message = new Message();
		message.arg1 = LyricHandler.STOP;
		this.sendMessage(message);
	}
	public void pause(){
		flag=false;
	}
}
