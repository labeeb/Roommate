package com.arshu.roommate.vo;

import com.arshu.roommate.util.RMConstants;

public class DrawerItem {
	public String title; 
	public RMConstants.PageName page;
	
	@Override
	public String toString() {
		return title;
	}
}
