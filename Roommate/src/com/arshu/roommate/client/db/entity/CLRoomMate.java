package com.arshu.roommate.client.db.entity;

import java.sql.SQLException;
import java.util.List;

import com.arshu.roommate.client.db.RMDBUtil;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class CLRoomMate  extends BaseTable{
	
	public final static String MATE_ID = "mate_id";
	@DatabaseField(foreign = true, columnName = MATE_ID)
	CLMate mate;
	
	public final static String ROOM_ID = "room_id";
	@DatabaseField(foreign = true, columnName = ROOM_ID)
	CLRoom room;

	public CLRoomMate() {
		// for ormlite
	}
	
	public CLRoomMate(CLRoom room,CLMate mate) {
		this.mate = mate;
		this.room = room;
	}
	
	/*
	 *  methods to build and run our prepared queries.
	 */

	private static PreparedQuery<CLRoom> roomsForMateQuery = null;
	private static PreparedQuery<CLMate> matesForRoomQuery = null;

	public static List<CLRoom> lookupRoomsForMate(CLMate mate,OrmLiteSqliteOpenHelper helper) throws SQLException {
		if (roomsForMateQuery == null) {
			roomsForMateQuery = makeRoomsForMateQuery(helper);
		}
		roomsForMateQuery.setArgumentHolderValue(0, mate);
		return RMDBUtil.getRoomDao(helper).query(roomsForMateQuery);
	}

	public static List<CLMate> lookupMatesForRoom(CLRoom room,OrmLiteSqliteOpenHelper helper) throws SQLException {
		if (matesForRoomQuery == null) {
			matesForRoomQuery = makeMatesForRoomQuery(helper);
		}
		matesForRoomQuery.setArgumentHolderValue(0, room);
		return RMDBUtil.getMateDao(helper).query(matesForRoomQuery);
	}

	/**
	 * Build our query for Room objects that match a Mate.
	 */
	private static PreparedQuery<CLRoom> makeRoomsForMateQuery(OrmLiteSqliteOpenHelper helper) throws SQLException {
		// build our inner query for MateRoom objects
		QueryBuilder<CLRoomMate, Integer> roomMateQb = RMDBUtil.getRoomMateDao(helper).queryBuilder();
		// just select the room-id field
		roomMateQb.selectColumns(CLRoomMate.ROOM_ID);
		SelectArg userSelectArg = new SelectArg();
		// you could also just pass in mate1 here
		roomMateQb.where().eq(CLRoomMate.MATE_ID, userSelectArg);

		// build our outer query for Room objects
		QueryBuilder<CLRoom, Integer> roomQb = RMDBUtil.getRoomDao(helper).queryBuilder();
		// where the id matches in the room-id from the inner query
		roomQb.where().in(CLRoom.ROW_ID, roomMateQb);
		return roomQb.prepare();
	}

	/**
	 * Build our query for Mate objects that match a Room
	 */
	private static PreparedQuery<CLMate> makeMatesForRoomQuery(OrmLiteSqliteOpenHelper helper) throws SQLException {
		QueryBuilder<CLRoomMate, Integer> roomMateQb = RMDBUtil.getRoomMateDao(helper).queryBuilder();
		// this time selecting for the mate-id field
		roomMateQb.selectColumns(CLRoomMate.MATE_ID);
		SelectArg postSelectArg = new SelectArg();
		roomMateQb.where().eq(CLRoomMate.ROOM_ID, postSelectArg);

		// build our outer query
		QueryBuilder<CLMate, Integer> mateQb = RMDBUtil.getMateDao(helper).queryBuilder();
		// where the mate-id matches the inner query's mate-id field
		mateQb.where().in(CLMate.ROW_ID, roomMateQb);
		return mateQb.prepare();
	}

}
