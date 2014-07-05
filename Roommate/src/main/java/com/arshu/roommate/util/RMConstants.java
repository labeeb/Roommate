package com.arshu.roommate.util;

public class RMConstants {
	public static enum  TaskStatus{
		SCUCCESS, FAILURE
	}
	
	public static enum PageName{
		ADDUSER, LISTROOM
	}
	
	public static enum UserType {
		ADMIN(1), NORMAL(2);

		private final int value;

		UserType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
		
		public static UserType getUserType(int value){
			switch (value) {
			case 1:
				return ADMIN;

			default:
				return NORMAL;
			}
			
		}
	}
	
	public static final String BK_LOGGED_MATE= "BK_LOGGED_MATE"; //BK = Bundle Key
	
	
	public static final String TAG = "RM";
	
	//Broad cast actions 
	public static final String BA_ROOMLIST_UPDATED = "BA_ROOMLIST_UPDATED";//BA = Broadcast action
	
	//Loader 
	public static final int LOADER_ROOMLIST = 1;
}
