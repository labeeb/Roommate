package com.arshu.roommate.viewhelper;

import android.app.Fragment;

import com.arshu.roommate.RMBaseActivity;
import com.arshu.roommate.vo.DrawerItem;

public interface RMViewHelper {
	public DrawerItem getDrawerItem();
	public Fragment getFragment(RMBaseActivity activity);
}
