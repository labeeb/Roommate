package com.arshu.roommate.fragment;

import android.app.Fragment;
import android.os.Bundle;

import com.arshu.roommate.bean.Mate;
import com.arshu.roommate.util.RMConstants;
import com.arshu.roommate.util.RMLog;

public class RMBaseFragment extends Fragment{
	private Mate mate = null;
	
	/**
	 * Read the mate object from getArguments
	 * Declare in base fragment since this same need for most of the fragments 
	 * @return
	 */
	public Mate getCurrentMate(){
		
		if(mate == null){
			Bundle bundle = getArguments();
			if(null != bundle){
				mate = bundle.getParcelable(RMConstants.BK_LOGGED_MATE);
			}else{
				RMLog.unexpected(getClass(), " Fragment with getArguments() null");
			}
		}
		return mate;
	}
}
