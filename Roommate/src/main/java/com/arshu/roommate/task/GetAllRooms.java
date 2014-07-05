package com.arshu.roommate.task;

import java.util.ArrayList;
import java.util.List;

import com.arshu.roommate.bean.Mate;
import com.arshu.roommate.bean.MateInRoom;
import com.arshu.roommate.bean.Room;
import com.arshu.roommate.exception.RMException;
import com.arshu.roommate.util.RMLog;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class GetAllRooms extends FindCallback<ParseObject> {
	private GetRoomsCallBack callBack;

	public GetAllRooms(Mate mate, GetRoomsCallBack callBack) {
		this.callBack = callBack;
		
		//TODO first check the local database 
		//Then initiate the sync
		//should broad cast if there is change 
		
		//TODO Update date 
		
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery(MateInRoom.class.getSimpleName());
		//query.fromLocalDatastore();
		query.whereEqualTo(MateInRoom.MATEOBJECT, mate.getParseObject());
		query.findInBackground(this);
	}

	@Override
	public void done(final List<ParseObject> scoreList, ParseException e) {
		if (e == null && scoreList != null && scoreList.size() > 0) {
			final List<Room> rooms = new ArrayList<Room>();
			for (ParseObject parseObject : scoreList) {
				final MateInRoom inRoom = new MateInRoom();
				inRoom.loadValuesFromParseobject(parseObject);
				Room room = new Room();
				room.loadValuesFromParseobject(inRoom.getRoomObject());
				rooms.add(room);
				
			}
			GetAllRooms.this.callBack.onSuccess(rooms);
			
		} else {
			if (e != null) {
				this.callBack.onError(new RMException(e));
			} else {
				this.callBack.onError(new RMException("No room "));
			}

		}
		
		//ParseQuery<ParseObject> query = ParseQuery.getQuery(MateInRoom.class.getSimpleName());
		//query.s
	}

	public interface GetRoomsCallBack {
		public void onSuccess(List<Room> rooms);

		public void onError(RMException exception);

	}

}
