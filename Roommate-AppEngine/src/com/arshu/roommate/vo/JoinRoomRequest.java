package com.arshu.roommate.vo;

import java.util.ArrayList;
import java.util.List;

import com.arshu.roommate.server.entity.AERoom;

public class JoinRoomRequest {
	private List<Long> mateList = new ArrayList<>();
	private AERoom room;
	public List<Long> getMateList() {
		return mateList;
	}
	public void setMateList(List<Long> mateList) {
		this.mateList = mateList;
	}
	public AERoom getRoom() {
		return room;
	}
	public void setRoom(AERoom room) {
		this.room = room;
	}
	
	
}
