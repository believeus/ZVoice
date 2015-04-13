package cn.believeus.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.widget.Toast;

public class Dialog {

	public static void finishActivity(final Activity activity, String message) {
		AlertDialog.Builder builder = new Builder(activity);
		builder.setTitle("提示");
		builder.setMessage(message);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				activity.finish();
			}
		}).show();
	}

	public static void show(final Activity activity, String message) {
		AlertDialog.Builder builder = new Builder(activity);
		builder.setTitle("提示");
		builder.setMessage(message);
		builder.setPositiveButton("确定", null).show();
	}
	public static void showToast(final Activity activity, String message){
		Toast toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT);
		toast.show();
	}
}
