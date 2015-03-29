package cn.believeus.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import cn.believeus.variables.Variables;
import android.content.Context;
import android.content.res.AssetManager;

public class PropertiesUtil {
	private static Properties properties;
	static{
		properties=new Properties();
	}
	public static String findValue(Context context,String key) {
		String value="";
		AssetManager assetManager = context.getAssets();
		try {
			InputStream inputStream = assetManager.open(Variables.PROPERTIES_FILE);
			properties.load(inputStream);
			inputStream.close();
			value = (String) properties.get(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}
}
