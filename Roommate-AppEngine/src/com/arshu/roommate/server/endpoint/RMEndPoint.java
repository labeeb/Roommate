package com.arshu.roommate.server.endpoint;

import java.util.ArrayList;
import java.util.List;

import com.arshu.roommate.RMConstants;
import com.arshu.roommate.RMOfyService;
import com.arshu.roommate.server.entity.AEMate;
import com.arshu.roommate.server.entity.AERoom;
import com.arshu.roommate.vo.JoinRoomRequest;
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
	
	@ApiMethod(name = "matesJoinRoom")
	public AERoom matesJoinRoom(JoinRoomRequest joinRoomRequest){
		Objectify ofy = RMOfyService.ofy();
		
		Key<AERoom> roomKey = null;
		AERoom room = joinRoomRequest.getRoom();
		
		Long roomId = room.getRoomId();
		
		if( roomId != null && roomId > 0){
			roomKey = Key.create(AERoom.class, roomId);
			room = ofy.get(roomKey);
		}else{
			roomKey = ofy.put(room);
		}
		
		for(Long mateId: joinRoomRequest.getMateList() ){
			Key<AEMate> mateKey = Key.create(AEMate.class, mateId);
			
			room.getMatesInRoom().add(mateKey);
			
			AEMate mate = ofy.get(mateKey);
			mate.getInRooms().add(roomKey);
			ofy.put(mate);
		}
		ofy.put(room);
		
		room.refreshAllMates();
		
		return room;
	}
	
	@ApiMethod(name = "getAllRooms")
	public List<AERoom> getAllRooms(AEMate mate){
		Objectify ofy = RMOfyService.ofy();
		AEMate mate1 = ofy.get(Key.create(AEMate.class, mate.getMateId()));
		mate1.loadAllRooms();
		return mate1.getInRoomValues();
	}
	
	@ApiMethod(name = "doLogin")
	public AEMate doLogin(AEMate checkMate) {
		//ObjectifyService ofy;
		
		Objectify ofy = RMOfyService.ofy();
		//Mate mate = new Mate();
		
		Query<AEMate> query = ofy.query(AEMate.class);
		query.filter(AEMate.USER_NAME, checkMate.getUserName());
		AEMate mate =query.get();
		if(null != mate){
			mate.loadAllRooms();
		}
		return mate;
		
	}
	
	
	@ApiMethod(name = "registerMate")
	public AEMate registerMate(AEMate checkMate) {
		Objectify ofy =  RMOfyService.ofy();
		//Mate mate = new Mate();
		AERoom home = new AERoom();
		home.setDescription("Auto gerneration room");
		home.setName(RMConstants.HOME+checkMate.getEmailAddress());
		ofy.put(home);
		List<Key<AERoom>> inRooms = new ArrayList<Key<AERoom>>(1);
		Key<AERoom> roomKey = ofy.put(home);
		inRooms.add(roomKey);
		checkMate.setInRooms(inRooms);
		List<Key<AEMate>> matesInRoom = new ArrayList<Key<AEMate>>();
		Key<AEMate> mates = ofy.put(checkMate);
		matesInRoom.add(mates);
		home.setMatesInRoom(matesInRoom);
		ofy.put(home);
		
		return checkMate;
		
	}
}
