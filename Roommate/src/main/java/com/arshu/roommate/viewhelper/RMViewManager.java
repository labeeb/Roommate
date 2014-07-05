package com.arshu.roommate.viewhelper;

import java.util.ArrayList;
import java.util.List;

import com.arshu.roommate.vo.DrawerItem;

public class RMViewManager {
	
	
	public static RMViewHelper getHomeDefaultPage(){
		return RMListRoomViewHelper.getInstance();
	}
	
	public static RMViewHelper getViewHelper(DrawerItem drawerItem){
		RMViewHelper helper = null;
		if(null != drawerItem){
			switch (drawerItem.page) {
			case ADDUSER:
				helper = RMAddRoomViewHelper.getInstance();
				break;
			case LISTROOM:
				helper = RMListRoomViewHelper.getInstance();
			default:
				helper = getHomeDefaultPage();
				break;
			}
		}else{
			//Default view
			helper = getHomeDefaultPage();
		}
		return helper;
	}
	
	
	public static List<DrawerItem> getDrawerItemsForHomePage(){
		List<DrawerItem> drawerItems  = new ArrayList<DrawerItem>();
		drawerItems.add(RMListRoomViewHelper.getInstance().getDrawerItem());
		drawerItems.add(RMAddRoomViewHelper.getInstance().getDrawerItem());
		return drawerItems;
	}

}
