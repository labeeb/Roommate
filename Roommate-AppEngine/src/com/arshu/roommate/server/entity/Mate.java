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
public class Mate {
	@Id
	private Long id;
	
	
	private int tableId;
	 
	private String password;
	private String emailAddress;
	private String description;
	
	@ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	private List<Key<Room>> inRooms = new ArrayList<Key<Room>>();
	
	@NotPersistent
	private List<Room> inRoomValues = new ArrayList<Room>();
	
	
	public void loadAllRooms(){
		if(inRooms != null){
			Objectify ofy =  RMOfyService.ofy();
			Room a =ofy.get(inRooms.get(0));
			inRoomValues.add(a);
		}
	}
	
	public List<Room> getInRoomValues() {
		
		return inRoomValues;
	}

	public void setInRoomValues(List<Room> inRoomValues) {
		this.inRoomValues = inRoomValues;
	}

	public Mate() {
	}
	
	public List<Key<Room>> getInRooms() {
		return inRooms;
	}


	public void setInRooms(List<Key<Room>> inRooms) {
		this.inRooms = inRooms;
	}
	

	public Long getId() {
		return id;
	}



	public int getTableId() {
		return tableId;
	}


	public void setTableId(int tableId) {
		this.tableId = tableId;
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
