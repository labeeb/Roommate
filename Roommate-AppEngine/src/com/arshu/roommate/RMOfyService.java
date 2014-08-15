package com.arshu.roommate;

import com.arshu.roommate.server.entity.Mate;
import com.arshu.roommate.server.entity.Room;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

public class RMOfyService {
	static {
        factory().register(Mate.class);
        factory().register(Room.class);
    }

    public static Objectify ofy() {
        return ObjectifyService.begin();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}
