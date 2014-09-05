package com.arshu.roommate.client.db.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class CLMate extends BaseTable{
	
	public static final String MATE_ID = "mateId";
	@DatabaseField(columnName = MATE_ID)
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

	public CLMate() {
		// for ormlite
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
