package com.arshu.roommate.vo;

import com.arshu.roommate.server.entity.AEMate;
import com.arshu.roommate.server.entity.AERoom;

public class LoginResponse {
	private AEMate mate;
	private AERoom room;
	public AEMate getMate() {
		return mate;
	}
	public void setMate(AEMate mate) {
		this.mate = mate;
	}
	public AERoom getRoom() {
		return room;
	}
	public void setRoom(AERoom room) {
		this.room = room;
	}
}
