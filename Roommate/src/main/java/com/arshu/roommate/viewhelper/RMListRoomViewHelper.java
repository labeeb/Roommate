package com.arshu.roommate.viewhelper;

import android.app.Fragment;
import android.os.Bundle;

import com.arshu.roommate.RMBaseActivity;
import com.arshu.roommate.fragment.RoomListFragment;
import com.arshu.roommate.util.RMConstants.PageName;
import com.arshu.roommate.vo.DrawerItem;

public class RMListRoomViewHelper implements RMViewHelper{
	
	static RMListRoomViewHelper instance = null;
	private RMListRoomViewHelper() {
	}
	public static RMListRoomViewHelper getInstance() {
		if(null == instance){
			instance = new RMListRoomViewHelper();
		}
		return instance;
	}
	


	@Override
	public DrawerItem getDrawerItem() {
		DrawerItem drawerItem = new DrawerItem();
		drawerItem.title = "List Room";
		drawerItem.page = PageName.LISTROOM;
		return drawerItem;
	}

	@Override
	public Fragment getFragment(RMBaseActivity activity) {
		Fragment fragment = new RoomListFragment();
		return fragment;
	}

}
