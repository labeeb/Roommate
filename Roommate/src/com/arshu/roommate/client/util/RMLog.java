package com.arshu.roommate.client.util;

import android.util.Log;

public class RMLog {
	public static final String TAG = "RMLog";
	
	public static final void unexpted(Class<?> inClass,String... msg){
		StringBuilder allMessages = new StringBuilder();
		for(String eachMsg:msg){
			allMessages.append(eachMsg).append(":");
		}
		Log.e(TAG+"Unexpected",inClass.getSimpleName()+":"+ allMessages.toString());
	}
	
	public static final void e(String tag,String... msg){
		StringBuilder allMessages = new StringBuilder();
		for(String eachMsg:msg){
			allMessages.append(eachMsg).append(":");
		}
		Log.e(TAG,tag+":"+ allMessages.toString());
	}
	
	public static final void e(Object object,String... msg){
		e(object.getClass(), msg);
	}
	
	public static final void e(Class<?> className,String... msg){
		e(className.getSimpleName(), msg);
	}
	
	
	public static final void d(String tag,String... msg){
		StringBuilder allMessages = new StringBuilder();
		for(String eachMsg:msg){
			allMessages.append(eachMsg).append(":");
		}
		
		Log.d(TAG,tag+":"+allMessages.toString());
	}
	
	public static final void d(Object object,String... msg){
		d(object.getClass(),msg);
	}
	
	public static final void d(Class<?> tag,String... msg){
		d(tag.getSimpleName(),msg);
	}
}
