package com.arshu.roommate.server.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RoomMate {
	
	public enum RoomMateType{
		OWNER, SHARE;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long roomMateId;
	
	private long mateId;
	private long roomId;
	private int type;
	
	public RoomMate() {}

	public long getRoomMateId() {
		return roomMateId;
	}

	public void setRoomMateId(long roomMateId) {
		this.roomMateId = roomMateId;
	}

	public long getMateId() {
		return mateId;
	}

	public void setMateId(long mateId) {
		this.mateId = mateId;
	}

	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
}
