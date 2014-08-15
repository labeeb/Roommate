package com.arshu.roommate.server.endpoint;

import java.util.ArrayList;
import java.util.List;

import com.arshu.roommate.RMConstants;
import com.arshu.roommate.RMOfyService;
import com.arshu.roommate.server.entity.Mate;
import com.arshu.roommate.server.entity.Room;
import com.arshu.roommate.vo.LoginResponse;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Entity;
import  com.googlecode.objectify.*;


@Api(name = "testendpoint", namespace = @ApiNamespace(ownerDomain = "arshu.com", ownerName = "arshu.com", packagePath = "roommate.server.endpoint"))
public class TestEndpoint {

	

	@ApiMethod(name = "doLoginTest")
	public List<Mate> doLogin1(Mate checkMate) {
		//ObjectifyService ofy;
		
		Objectify ofy = RMOfyService.ofy();
		//Mate mate = new Mate();
		
		Query<Mate> query = ofy.query(Mate.class);
		query.filter("emailAddress", checkMate.getEmailAddress());
		Mate mate =query.get();
		if(null != mate){
			mate.loadAllRooms();
		}
		
//		List<Mate> result = new ArrayList<>();
//		for (Mate mate:query) {
//			result.add(mate);
//		}
		
		List<Mate> result = new ArrayList<>();
		result.add(mate);
		return result;
		
	}
	
	
	@ApiMethod(name = "insert")
	public Mate insert(Mate checkMate) {
		//ObjectifyService ofy;
		
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
		//inRooms.add(home.get);
		//inRooms.add(h);
		//ListmatesInRoom
		////home.setMatesInRoom(matesInRoom);
		List<Key<Mate>> matesInRoom = new ArrayList<Key<Mate>>();
		Key<Mate> mates = ofy.put(checkMate);
		matesInRoom.add(mates);
		home.setMatesInRoom(matesInRoom);
		ofy.put(home);
		
		return checkMate;
		
	}
	
//	@ApiMethod(name = "doLoginobjectify")
//	public List<Mate> doLoginObjectify(Mate checkMate) {
//		//ObjectifyService ofy;
//		
//		Objectify ofy = OfyService.ofy();
//		//Mate mate = new Mate();
//		
//		Query<Mate> query = ofy.query(Mate.class);
//		if(null != checkMate){
//			query.filter("emailAddress", checkMate.getEmailAddress());
//		}
//		
//		List<Mate> result = new ArrayList<>();
//		for (Mate mate:query) {
//			result.add(mate);
//		}
//		return result;
//		
//	}
//	
//	
//	@ApiMethod(name = "insert")
//	public Mate insert(Mate checkMate) {
//		//ObjectifyService ofy;
//		
//		Objectify ofy = OfyService.ofy();
//		//Mate mate = new Mate();
//		ofy.put(checkMate);
//		
//		return checkMate ;
//		
//	}
	
	

}
