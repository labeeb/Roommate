package com.arshu.roommate.server.entity;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.NotPersistent;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.arshu.roommate.RMOfyService;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;

@Entity
public class AERoom {
	
	@Id
	private Long roomId;
	public static final String ROOM_ID = "roomId";
	
	private String name;
	private String description;
	private String ownerMateId;
	
	@ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	private List<Key<AEMate>> matesInRoom = new ArrayList<Key<AEMate>>();
	

	@NotPersistent
	private List<AEMate> allMates = new ArrayList<AEMate>();
	
	public AERoom() {
	}
	
	public void refreshAllMates(){
		if(matesInRoom != null){
			Objectify ofy =  RMOfyService.ofy();
			for(Key<AEMate> key:matesInRoom){
				allMates.add(ofy.get(key));
			}
		}
	}
	
	public List<AEMate> getAllMates() {
		return allMates;
	}

	public void setAllMates(List<AEMate> allMates) {
		this.allMates = allMates;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public List<Key<AEMate>> getMatesInRoom() {
		return matesInRoom;
	}

	public void setMatesInRoom(List<Key<AEMate>> matesInRoom) {
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
