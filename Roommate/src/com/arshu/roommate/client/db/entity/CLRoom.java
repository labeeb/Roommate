package com.arshu.roommate.client.db.entity;

import com.arshu.roommate.server.endpoint.rmendpoint.model.AERoom;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class CLRoom  extends BaseTable{
	
	@DatabaseField
	private Long _id;//For simple cursor loader 
	


	public static final String ROOM_ID = "roomId";
	@DatabaseField( id= true,columnName = ROOM_ID)//
	private Long roomId;
	
	@DatabaseField
	private String name;
	
	@DatabaseField
	private String description;
	
	@DatabaseField
	private String ownerMateId;

	public CLRoom() {
		// for ormlite
	}
	
	public CLRoom(AERoom aeRoom) {
		setDescription(aeRoom.getDescription());
		setName(aeRoom.getName());
		setOwnerMateId(aeRoom.getOwnerMateId());
		setRoomId(aeRoom.getRoomId());
	}
	
	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
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
