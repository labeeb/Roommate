package com.arshu.roommate.bean;

import java.sql.SQLException;
import java.util.List;

import android.R.integer;
import android.accounts.Account;
import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.arshu.roommate.db.DatabaseHelper;
import com.j256.ormlite.android.AndroidDatabaseResults;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.table.DatabaseTable;
import com.parse.ParseObject;

@DatabaseTable
public class Room extends RMBaseValueObject {
	
	private static final String NAME= "name";
	@DatabaseField
	private String name; 
	
	private static final String DESCRIPTION= "description";
	@DatabaseField
	private String description;
	
	private static final String CREATEDMATEOBJECT= "createdMateObject";
	private ParseObject createdMateObject;
	
	private static final String TYPE= "type";
	@DatabaseField
	private int type; 
	
	public Room(String name, String description,ParseObject createdMateObject) {
		super();
		this.name = name;
		this.description = description;
		this.type = 0;
		this.createdMateObject = createdMateObject;
	}
	
	public Room() {}
	
	@Override
	public String toString() {
		return name;
	}
/************************************************************************************************
 	DB
*************************************************************************************************/
	public void save(Context context) throws SQLException{
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		Dao<Room, Integer> dao = databaseHelper.getRoomDao();
		dao.create(this);
		
	}
	
	
	public static Room loadFromCursor(Cursor cursor){
		if(cursor == null){
			return null;
		}
		Room room = new Room();
		RMBaseValueObject.loadFromCursor(cursor, room);
		room.description = cursor.getString(cursor.getColumnIndex(DESCRIPTION));
		room.name =  cursor.getString(cursor.getColumnIndex(NAME));
		room.type =  cursor.getInt(cursor.getColumnIndex(TYPE));
		// cursor.getString(cursor.getColumnIndex(DESCRIPTION));
		return room;
	}
	
	public static Cursor getCursorForRoomList(Context context) throws SQLException {
		Cursor cursor = null;
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		Dao<Room, Integer> dao = databaseHelper.getRoomDao();
		QueryBuilder<Room, Integer> qb = dao.queryBuilder();
		CloseableIterator<Room> iterator = dao.iterator(qb.prepare());
		try {
			AndroidDatabaseResults results = (AndroidDatabaseResults) iterator.getRawResults();
			cursor = results.getRawCursor();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return cursor;
	}
	
/************************************************************************************************
  Parse 
*************************************************************************************************/
	
	@Override
	public void createParseObject() {
		parseObject = new ParseObject(this.getClass().getSimpleName());
		parseObject.put(NAME, name);
		parseObject.put(DESCRIPTION, description);
		parseObject.put(CREATEDMATEOBJECT, createdMateObject);
		parseObject.put(TYPE, type);
	}
	
	@Override
	protected void loadChildValues(ParseObject parseObject) {
		this.name = parseObject.getString(NAME);
		this.description = parseObject.getString(DESCRIPTION);
		this.createdMateObject = parseObject.getParseObject(CREATEDMATEOBJECT);
		this.type = parseObject.getInt(TYPE);
	}
	
/************************************************************************************************
 Parcelable 
*************************************************************************************************/

	

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		super.writeParentToParcel(dest);
		dest.writeString(name);
		dest.writeString(description);
		saveParseObject(dest, createdMateObject);
		dest.writeInt(type);
	}
	private Room(Parcel in) {
		super.readParent(in);
		name = in.readString();
		description = in.readString();
		createdMateObject = readPaseObject(in);
		type = in.readInt();
	}

	public static final Parcelable.Creator<Room> CREATOR = new Parcelable.Creator<Room>() {
		public Room createFromParcel(Parcel in) {
			return new Room(in);
		}

		public Room[] newArray(int size) {
			return new Room[size];
		}
	};

	
}
