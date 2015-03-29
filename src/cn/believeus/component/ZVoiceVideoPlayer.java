package cn.believeus.component;

import cn.believeus.handler.LyricHandler;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.MediaController;
import android.widget.VideoView;

public class ZVoiceVideoPlayer extends VideoView{
	private LyricHandler lyricHandler;
	
	public LyricHandler getLyricHandler() {
		return lyricHandler;
	}

	public void setLyricHandler(LyricHandler lyricHandler) {
		this.lyricHandler = lyricHandler;
	}

	public ZVoiceVideoPlayer(Context context) {
		super(context);
		
	}
	 public ZVoiceVideoPlayer(Context context, AttributeSet attrs) {
	        super(context, attrs);
	      //  lyricHandler=new LyricHandler(this);
			MediaController mc = new MediaController(context);  
			this.setMediaController(mc);
	    }

	    public ZVoiceVideoPlayer(Context context, AttributeSet attrs, int defStyle) {
	        super(context, attrs, defStyle);
	    }

	@Override
	public void pause() {
		//lyricHandler.pause();
		super.pause();
	}
	@Override
	public void start() {
		//lyricHandler.start();
		super.start();
	}
	@Override
	public void stopPlayback() {
		//lyricHandler.stop();
		super.stopPlayback();
	}
}
