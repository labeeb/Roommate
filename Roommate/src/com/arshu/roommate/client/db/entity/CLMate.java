package com.arshu.roommate.client.db.entity;

import java.util.ArrayList;
import java.util.List;

import com.arshu.roommate.server.endpoint.rmendpoint.model.AEMate;
import com.arshu.roommate.server.endpoint.rmendpoint.model.AERoom;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class CLMate extends BaseTable{
	
	public static final String MATE_ID = "mateId";
	@DatabaseField(id= true, columnName = MATE_ID)
	private Long mateId;
	
	public static final String USER_NAME = "userName";
	@DatabaseField(columnName = USER_NAME)
	private String userName;

	@DatabaseField
	private String password;
	
	@DatabaseField
	private String emailAddress;
	
	@DatabaseField
	private String description;

	private List<CLRoom> inRooms;
	
	public CLMate() {
		// for ormlite
	}
	public CLMate(AEMate aeMate) {
		this.userName = aeMate.getUserName();
		this.password = aeMate.getPassword();
		this.emailAddress = aeMate.getEmailAddress();
		this.description = aeMate.getDescription();
		this.mateId = aeMate.getMateId();
		setInRooms(aeMate.getInRoomValues());
	}
	
	public AEMate createAEMate() {
		AEMate aeMate = new AEMate();
		aeMate.setUserName(getUserName());
		aeMate.setPassword(getPassword());
		aeMate.setEmailAddress(getEmailAddress());
		aeMate.setDescription(getDescription());
		aeMate.setMateId(getMateId());
		return aeMate;
	}
	
	/**
	 * This will not have value from db, just a value holder only 
	 * @return
	 */
	public List<CLRoom> getInRooms() {
		return inRooms;
	}
	
	
	public void setInRooms(List<AERoom> inRoomValues) {
		this.inRooms = new ArrayList<CLRoom>();
		for(AERoom aeRoom:inRoomValues){
			this.inRooms.add(new CLRoom(aeRoom));
		}
		
	}
	public Long getMateId() {
		return mateId;
	}

	public void setMateId(Long mateId) {
		this.mateId = mateId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
