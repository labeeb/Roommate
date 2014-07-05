package com.arshu.roommate.viewhelper;

import android.app.Fragment;
import android.os.Bundle;

import com.arshu.roommate.RMBaseActivity;
import com.arshu.roommate.fragment.AddRoomFragment;
import com.arshu.roommate.util.RMConstants.PageName;
import com.arshu.roommate.vo.DrawerItem;

public class RMAddRoomViewHelper implements RMViewHelper{
	static RMAddRoomViewHelper instance = null;
	private RMAddRoomViewHelper() {
	}
	public static RMAddRoomViewHelper getInstance() {
		if(null == instance){
			instance = new RMAddRoomViewHelper();
		}
		return instance;
	}

	@Override
	public DrawerItem getDrawerItem() {
		DrawerItem drawerItem = new DrawerItem();
		drawerItem.title = "Add Room";
		drawerItem.page = PageName.ADDUSER;
		return drawerItem;
	}

	@Override
	public Fragment getFragment(RMBaseActivity activity) {
		Fragment fragment = new AddRoomFragment();
		return fragment;
	}
	
}
