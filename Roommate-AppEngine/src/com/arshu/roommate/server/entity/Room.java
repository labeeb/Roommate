package com.arshu.roommate.server.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.Key;

@Entity
public class Room {
	
	@Id
	private Long roomId;
	
	private String name;
	private String description;
	private String ownerMateId;
	
	@ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	private List<Key<Mate>> matesInRoom = new ArrayList<Key<Mate>>();
	
	public Room() {
	}
	
	public List<Key<Mate>> getMatesInRoom() {
		return matesInRoom;
	}

	public void setMatesInRoom(List<Key<Mate>> matesInRoom) {
		this.matesInRoom = matesInRoom;
	}

	public Long getRoomId() {
		return roomId;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOwnerMateId() {
		return ownerMateId;
	}

	public void setOwnerMateId(String ownerMateId) {
		this.ownerMateId = ownerMateId;
	}
}
