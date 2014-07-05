package com.arshu.roommate.util;

import android.util.Log;

public class RMLog {

//	public static void d(Object object,String... messages){
//		d(object.getClass(), messages);
//	}
	
	public static void d(Class<?> className,String... messages){
		Log.d(RMConstants.TAG,className.getSimpleName()+":"+ messages[0]); //TODO iterate and print all messages 
	}
	
//	public static void unexpected(Object object,String... messages){
//		unexpected(object.getClass(), messages);
//	}
	public static void unexpected(Class<?> className,String... messages){
		Log.e(RMConstants.TAG,"unexpected:"+className.getSimpleName()+":"+messages[0]);//TODO iterate and print all messages 
	}
}
