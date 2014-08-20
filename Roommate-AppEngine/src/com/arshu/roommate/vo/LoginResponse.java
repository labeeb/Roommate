package com.arshu.roommate.vo;

import com.arshu.roommate.server.entity.Mate;
import com.arshu.roommate.server.entity.Room;

public class LoginResponse {
	private Mate mate;
	private Room room;
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
}
