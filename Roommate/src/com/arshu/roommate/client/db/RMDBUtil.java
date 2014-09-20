package com.arshu.roommate.client.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.arshu.roommate.client.db.entity.CLMate;
import com.arshu.roommate.client.db.entity.CLRoom;
import com.arshu.roommate.client.db.entity.CLRoomMate;
import com.arshu.roommate.client.util.RMLog;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;

public class RMDBUtil {
	
	public static List<Class<?> > getAllTables(){
		List<Class<?> > dbTables = new ArrayList<Class<?>>(1);
		
		dbTables.add(CLMate.class);
		dbTables.add(CLRoom.class);
		dbTables.add(CLRoomMate.class);
		
		return dbTables;
	}
	
	public static void clearDaoObjects(){
		mateDao = null;
		roomDao = null;
		roomMateDao = null;
	}
	
	private static Dao<CLMate, Integer> mateDao = null;
	private static Dao<CLRoom, Integer> roomDao = null;
	private static Dao<CLRoomMate, Integer> roomMateDao = null;
	
	public static Dao<CLRoomMate, Integer> getRoomMateDao(OrmLiteSqliteOpenHelper helper) throws SQLException  {
		if (roomMateDao == null) {
			roomMateDao = helper.getDao(CLRoomMate.class);
		}
		return roomMateDao;
	}
	
	public static Dao<CLMate, Integer> getMateDao(OrmLiteSqliteOpenHelper helper) throws SQLException  {
		if (mateDao == null) {
			mateDao = helper.getDao(CLMate.class);
		}
		return mateDao;
	}
	
	public static Dao<CLRoom, Integer> getRoomDao(OrmLiteSqliteOpenHelper helper) throws SQLException  {
		if (roomDao == null) {
			roomDao = helper.getDao(CLRoom.class);
		}
		return roomDao;
	}
	
	
	/**
	 * Code just to make sure Ormlite working fine (Can remove)
	 */
	
	public void testInsertValue(Context context){
		
		
		CLMate mate = new CLMate();
		mate.setUserName("Test");
		mate.setPassword("Pass");
		mate.setEmailAddress("email");
		mate.setMateId(Long.valueOf(12312));
		
		
		CLRoom room = new CLRoom();
		room.setName("roomName");
		room.setRoomId(12l);
		room.setDescription("desc");  
		
		CLRoomMate roomMate = new CLRoomMate(room,mate);
		
		CLRoom room2 = new CLRoom();
		room2.setName("second room");
		room2.setRoomId(13L);
		room2.setDescription("second desc");      
		
		CLRoomMate roomMate2 = new CLRoomMate(room2,mate);
		
	
		if(null != mate){
			RMLog.d(this,"POC", "mate id: "+mate.getMateId());
			try {
				RMDBHelper helper = RMDBHelper.getHelper(context);
				RMDBUtil.getMateDao(helper).createOrUpdate(mate);
				RMDBUtil.getRoomDao(helper).createOrUpdate(room);
				RMDBUtil.getRoomDao(helper).createOrUpdate(room2);
				
				RMDBUtil.getRoomMateDao(helper).createOrUpdate(roomMate);
				RMDBUtil.getRoomMateDao(helper).createOrUpdate(roomMate2);
			} catch (SQLException e) {
				RMLog.d(this,"POC","SQLException createOrUpdate");
				e.printStackTrace();
			}
			//return true;
		}
	}
	
	public void testReadValue(Context context){
		
		try {
			RMDBHelper helper = RMDBHelper.getHelper(context);
			List<CLMate> mates = RMDBUtil.getMateDao(helper).queryForAll();
			if(mates != null){
				RMLog.d(this,"POC","mates not null "+mates.size());
				List<CLRoom> inRooms = CLRoomMate.lookupRoomsForMate(mates.get(0), helper);
				RMLog.d(this,"POC","inRooms not null "+inRooms.size());
				for(CLRoom room:inRooms){
					 RMLog.d(this,"POC","inRooms name  "+room.getName());
				}
				
			}else{
				RMLog.d(this,"POC","mates null");
			}
			
			// now from room 
			List<CLRoom> rooms = RMDBUtil.getRoomDao(helper).queryForAll(); 
			if(rooms != null){
				RMLog.d(this,"POC","rooms not null "+rooms.size());
				List<CLMate> allMates = CLRoomMate.lookupMatesForRoom(rooms.get(0), helper); 
				RMLog.d(this,"POC","rooms not null "+allMates.size());
				for(CLMate mate:allMates){
					 RMLog.d(this,"POC","CLMate in room name  "+mate.getUserName());
				}
				
			}else{
				RMLog.d(this,"POC","rooms null");
			}
			
		} catch (SQLException e) {
			RMLog.d(this,"POC","SQLException query");
			e.printStackTrace();
		}
	}

	public static void saveInitialValues(RMDBHelper helper) {

		CLMate mate = new CLMate();
		mate.setUserName("Test");
		mate.setPassword("Pass");
		mate.setEmailAddress("email");
		mate.setMateId(Long.valueOf(12312));
		
		
		CLRoom room = new CLRoom();
		room.setName("roomName");
		room.setRoomId(11l);
		room.setDescription("desc");  
		
		CLRoomMate roomMate = new CLRoomMate(room,mate);
		
		CLRoom room2 = new CLRoom();
		room2.setName("second room");
		room2.setRoomId(12L);
		room2.setDescription("second desc");      
		
		CLRoomMate roomMate2 = new CLRoomMate(room2,mate);
		
		
		CLRoom room3 = new CLRoom();
		room3.setName("3 room");
		room3.setRoomId(13L);
		room3.setDescription("second desc");      
		
		CLRoomMate roomMate3 = new CLRoomMate(room3,mate);
		
		
		CLRoom room4 = new CLRoom();
		room4.setName("4 room");
		room4.setRoomId(14L);
		room4.setDescription("second desc");      
		
		CLRoomMate roomMate4 = new CLRoomMate(room4,mate);
		
		
		CLRoom room5 = new CLRoom();
		room5.setName("5 room");
		room5.setRoomId(15L);
		room5.setDescription("second desc");      
		
		CLRoomMate roomMate5 = new CLRoomMate(room5,mate);
		
	
		if(null != mate){
			RMLog.d(RMDBUtil.class,"POC", "mate id: "+mate.getMateId());
			try {
				
				RMDBUtil.getMateDao(helper).createOrUpdate(mate);
				RMDBUtil.getRoomDao(helper).createOrUpdate(room);
				RMDBUtil.getRoomDao(helper).createOrUpdate(room2);
				RMDBUtil.getRoomDao(helper).createOrUpdate(room3);
				RMDBUtil.getRoomDao(helper).createOrUpdate(room4);
				RMDBUtil.getRoomDao(helper).createOrUpdate(room5);
				
				RMDBUtil.getRoomMateDao(helper).createOrUpdate(roomMate);
				RMDBUtil.getRoomMateDao(helper).createOrUpdate(roomMate2);
				RMDBUtil.getRoomMateDao(helper).createOrUpdate(roomMate3);
				RMDBUtil.getRoomMateDao(helper).createOrUpdate(roomMate4);
				RMDBUtil.getRoomMateDao(helper).createOrUpdate(roomMate5);
			} catch (SQLException e) {
				RMLog.d(RMDBUtil.class,"POC","SQLException createOrUpdate");
				e.printStackTrace();
			}
			//return true;
		} 
		
	}

}
