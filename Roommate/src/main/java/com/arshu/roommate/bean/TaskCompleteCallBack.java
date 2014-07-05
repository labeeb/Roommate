package com.arshu.roommate.bean;

import com.arshu.roommate.exception.RMException;
import com.arshu.roommate.util.RMConstants;
import com.parse.ParseObject;

public interface TaskCompleteCallBack {
	
	public void onComplete(RMConstants.TaskStatus status,ParseObject parseObject, RMException exception);
}
