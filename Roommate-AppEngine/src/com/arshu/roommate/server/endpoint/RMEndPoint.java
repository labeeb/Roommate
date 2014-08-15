package com.arshu.roommate.server.endpoint;

import java.util.ArrayList;
import java.util.List;

import com.arshu.roommate.RMConstants;
import com.arshu.roommate.RMOfyService;
import com.arshu.roommate.server.entity.Mate;
import com.arshu.roommate.server.entity.Room;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Query;


@Api(name = "rmendpoint", 
 namespace = @ApiNamespace(ownerDomain = "arshu.com", ownerName = "arshu.com", packagePath = "roommate.server.endpoint")
)
public class RMEndPoint {
	@ApiMethod(name = "doLogin")
	public Mate doLogin(Mate checkMate) {
		//ObjectifyService ofy;
		
		Objectify ofy = RMOfyService.ofy();
		//Mate mate = new Mate();
		
		Query<Mate> query = ofy.query(Mate.class);
		query.filter("emailAddress", checkMate.getEmailAddress());
		Mate mate =query.get();
		if(null != mate){
			mate.loadAllRooms();
		}
		return mate;
		
	}
	
	
	@ApiMethod(name = "registerMate")
	public Mate registerMate(Mate checkMate) {
		Objectify ofy =  RMOfyService.ofy();
		//Mate mate = new Mate();
		Room home = new Room();
		home.setDescription("Auto gerneration room");
		home.setName(RMConstants.HOME+checkMate.getEmailAddress());
		ofy.put(home);
		List<Key<Room>> inRooms = new ArrayList<Key<Room>>(1);
		Key<Room> roomKey = ofy.put(home);
		inRooms.add(roomKey);
		checkMate.setInRooms(inRooms);
		List<Key<Mate>> matesInRoom = new ArrayList<Key<Mate>>();
		Key<Mate> mates = ofy.put(checkMate);
		matesInRoom.add(mates);
		home.setMatesInRoom(matesInRoom);
		ofy.put(home);
		
		return checkMate;
		
	}
}
