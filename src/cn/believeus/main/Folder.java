package cn.believeus.main;

import java.io.File;

public class Folder {
	private static String[] varchar={
				"0","1","2","3","4",
				"5","6","7","8","9",
				"A","B","C","D","E","F"};
	public static String basepath;
	
	public static boolean initFolder(String basepath){
		boolean status=false;
		Folder.basepath=basepath;
		//创建失败
		File file=new File(basepath);
		//文件在不存在的情况下创建失败
		if(!file.exists()&&!file.mkdirs())
			return status;
		//创建子文件夹
		try{
			createSubFolder(file);
			File[] subFolders = file.listFiles();
			for (int i = 0; i < subFolders.length; i++) {
				createSubFolder(subFolders[i]);
			}
			status=true;
			System.out.println("Success:all folder create success");
		}catch(Exception ex){
			System.out.println("Error:folder create error.error message:"+ex.getMessage());
			status=false;
		}
		return status;
	}
	
	//创建子文件夹
	private static void createSubFolder(File file) {
		String subfolder;
		for (int i = 0; i < varchar.length; i++) {
			for(int j=0;j<varchar.length;j++){
				subfolder=file.getAbsolutePath()+"/"+varchar[i]+varchar[j];
				//创建子文件夹
				File dfsFile=new File(subfolder);
				if(!dfsFile.exists()&&dfsFile.mkdirs())
					System.out.println("create mydfs subfolder:"+dfsFile.getAbsolutePath());
			}
		}
	}
	public static String getStoragePath(String md5){
    	String subfolder1 = md5.substring(0, 2).toUpperCase();
    	String subfolder2 = md5.substring(2, 4).toUpperCase();
    	String fileName=md5.substring(4);
    	return basepath+"/"+subfolder1+"/"+subfolder2+"/"+fileName;
	}
}
