package com.arshu.roommate;

public class RMUtils {

	public static boolean isUserNameValid(String userName) {
		return userName.length() > 4;
	}

	public static  boolean isPasswordValid(String password) {
		return password.length() > 4;
	}
}
