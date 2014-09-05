package com.arshu.roommate.server.entity;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.NotPersistent;
import javax.persistence.Id;

import com.arshu.roommate.RMOfyService;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.annotation.Entity;


@Entity
public class AEMate {
	@Id
	private Long mateId;
	public static final String ID = "mateId";
	
	private String userName;
	public static final String USER_NAME = "userName";
	
	private String password;
	private String emailAddress;
	private String description;
	
	@ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	private List<Key<AERoom>> inRooms = new ArrayList<Key<AERoom>>();
	
	@NotPersistent
	private List<AERoom> inRoomValues = new ArrayList<AERoom>();

	public AEMate() {
	}
	
	public void loadAllRooms(){
		if(inRooms != null){
			Objectify ofy =  RMOfyService.ofy();
			for(Key<AERoom> key:inRooms){
				inRoomValues.add(ofy.get(key));
			}
		}
	}
	
	public List<AERoom> getInRoomValues() {
		return inRoomValues;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public static String getId() {
		return ID;
	}

	public void setInRoomValues(List<AERoom> inRoomValues) {
		this.inRoomValues = inRoomValues;
	}
	
	public List<Key<AERoom>> getInRooms() {
		return inRooms;
	}

	public void setInRooms(List<Key<AERoom>> inRooms) {
		this.inRooms = inRooms;
	}
	
	public Long getMateId() {
		return mateId;
	}

	public void setMateId(Long mateId) {
		this.mateId = mateId;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
