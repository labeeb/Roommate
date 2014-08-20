package com.arshu.roommate.vo;

import java.util.ArrayList;
import java.util.List;

import com.arshu.roommate.server.entity.Room;

public class JoinRoomRequest {
	private List<Long> mateList = new ArrayList<>();
	private Room room;
	public List<Long> getMateList() {
		return mateList;
	}
	public void setMateList(List<Long> mateList) {
		this.mateList = mateList;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	
	
}
