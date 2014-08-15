package com.arshu.roommate.vo;

import com.arshu.roommate.server.entity.Mate;
import com.arshu.roommate.server.entity.Room;
import com.arshu.roommate.server.entity.RoomMate;

public class LoginResponse {
	private Mate mate;
	private Room room;
	private RoomMate roomMate;
	public Mate getMate() {
		return mate;
	}
	public void setMate(Mate mate) {
		this.mate = mate;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public RoomMate getRoomMate() {
		return roomMate;
	}
	public void setRoomMate(RoomMate roomMate) {
		this.roomMate = roomMate;
	}
	
	
	

}
