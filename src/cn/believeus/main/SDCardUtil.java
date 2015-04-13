package cn.believeus.main;

import java.io.File;
import android.app.Activity;
import android.os.Environment;

public class SDCardUtil {
	public static String findFromSdCard(String filename) {
		String filePath = "";
		String sdcard = Environment.getExternalStorageState();
		if (sdcard.equals(Environment.MEDIA_MOUNTED)) {
			File file = Environment.getExternalStorageDirectory();
			filePath = file.getAbsolutePath() + File.separator + filename;
			return filePath;
		}

		return filePath;
	}

	public static boolean sdcardStatusReport(Activity activity) {
		String status="";
		String storageState = Environment.getExternalStorageState();
		if (storageState.equals(Environment.MEDIA_MOUNTED)) {
			status="true:当前SD卡已经插入卡槽!";
		} else if (storageState.equals(Environment.MEDIA_SHARED)) {
			status="false:当前手机已连接其他设备,SD卡暂时不可用!";
		} else if(storageState.equals(Environment.MEDIA_REMOVED)){
			status="false:SD卡不存在!";
		}else if (storageState.equals(Environment.MEDIA_CHECKING)) {
			status="false:SD卡正在准备中,请稍等!";
		}
		Dialog.showToast(activity,status.split(":")[1]);
		return Boolean.parseBoolean(status.split(":")[0]);
	}
}
