package com.arshu.roommate;

import com.arshu.roommate.server.entity.AEMate;
import com.arshu.roommate.server.entity.AERoom;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

public class RMOfyService {
	static {
        factory().register(AEMate.class);
        factory().register(AERoom.class);
    }

    public static Objectify ofy() {
        return ObjectifyService.begin();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}
