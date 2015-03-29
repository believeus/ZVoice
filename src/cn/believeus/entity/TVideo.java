package cn.believeus.entity;

import java.io.Serializable;

public class TVideo implements Serializable {
	public static final String ID="_id";
	public static final String VIDEO_NAME="videoname";
	public static final String DESC="desc";
	public static final String VIDEO_URL="videoUrl";
	public static final String THUMB1="thumb1";
	public static final String THUMB2="thumb2";
	public static final String THUMB3="thumb3";
	public static final String THUMB4="thumb4";
	private static final long serialVersionUID = -7672514730049611050L;
	private int _id;
	// 视频标题
	private String videoname;
	// 视频描述
	private String desc;
	// 视频存放路径
	private String videoUrl;
	// 视频截图路径
	private String thumb1;
	private String thumb2;
	private String thumb3;
	private String thumb4;

	

	public int get_id() {
		return _id;
	}

	public void set_id(int i) {
		this._id = i;
	}

	public String getVideoname() {
		return videoname;
	}

	public void setVideoname(String videoname) {
		this.videoname = videoname;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getThumb1() {
		return thumb1;
	}

	public void setThumb1(String thumb1) {
		this.thumb1 = thumb1;
	}

	public String getThumb2() {
		return thumb2;
	}

	public void setThumb2(String thumb2) {
		this.thumb2 = thumb2;
	}

	public String getThumb3() {
		return thumb3;
	}

	public void setThumb3(String thumb3) {
		this.thumb3 = thumb3;
	}

	public String getThumb4() {
		return thumb4;
	}

	public void setThumb4(String thumb4) {
		this.thumb4 = thumb4;
	}

	

}
