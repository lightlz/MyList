package com.light.myilists.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

import java.util.List;

/**
 * @desciption 获取本app的信息
 * @create 2014年11月5日
 */
public class AppUtil {

	/**
	 * @desciption 获取应用的名称
	 * @create 2014年11月5日
	 * @param context
	 * @return
	 */
	public static String getVersionName(Context context){
		String name = "beta1.0";
		try {  
		    PackageInfo info = context.getPackageManager()
		    		.getPackageInfo(context.getPackageName(), 0);  
		    // 当前应用的版本名称  
		    name = info.versionName;  

		    // 当前版本的版本号  
		    int versionCode = info.versionCode;  
		    // 当前版本的包名  
		    String packageNames = info.packageName;  
		} catch (NameNotFoundException e) {  
		    e.printStackTrace();  
		} finally {
			return name;
		}
	}
	
	/**
	 * @desciption 获取引用的版本号
	 * @create 2014年11月5日
	 * @param context
	 * @return
	 */
	public static int getVersionCode(Context context){
		
		int code = 1;
		try {  
		    PackageInfo info = context.getPackageManager()
		    		.getPackageInfo(context.getPackageName(), 0);  
		    code = info.versionCode;  
		    // 当前版本的包名  
		    String packageNames = info.packageName;  
		} catch (NameNotFoundException e) {  
		    e.printStackTrace();  
		} finally {
			return code;
		}
	}
	
	/**
	 * @desciption 获取应用的包名
	 * @create 2014年11月5日
	 * @param context
	 * @return
	 */
	public static String getPackageName(Context context){
		String name = "light";
		try {  
		    PackageInfo info = context.getPackageManager()
		    		.getPackageInfo(context.getPackageName(), 0);  

		    // 当前版本的包名  
		    name = info.packageName;  
		} catch (NameNotFoundException e) {  
		    e.printStackTrace();  
		} finally {
			return name;
		}
	}
	
	/**
	 * @desciption 判断本应用是否被打开
	 * @create 2014年11月5日
	 * @param context
	 * @return
	 */
	public static boolean isOpen(Context context){
		boolean isOpen = false;

		String pageckName = getPackageName(context);

		ActivityManager manager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE) ;
	     List<RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1) ;
	         
	     if(runningTaskInfos != null){
	    	 if((runningTaskInfos.get(0).topActivity).toString().contains(pageckName))
	    		 isOpen = true;
	     }
		return isOpen;
	}
}
