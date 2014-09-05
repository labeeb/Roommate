package com.arshu.roommate.client.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.arshu.roommate.client.db.entity.CLMate;
import com.arshu.roommate.client.db.entity.CLRoom;
import com.arshu.roommate.client.db.entity.CLRoomMate;
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

}
