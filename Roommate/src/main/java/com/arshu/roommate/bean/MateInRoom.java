package com.arshu.roommate.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.arshu.roommate.util.RMConstants.UserType;
import com.parse.ParseObject;

public class MateInRoom extends RMBaseValueObject {
	
	public static final String MATEOBJECT = "mateObject";
	private ParseObject mateObject; 
	
	public static final String ROOMOBJECT= "roomObject";
	private ParseObject roomObject;
	
	private static final String USERTYPE= "userType";
	private UserType userType; 
	
	public ParseObject getMateObject() {
		return mateObject;
	}

	public ParseObject getRoomObject() {
		return roomObject;
	}

	public MateInRoom(Mate mate, Room room, UserType userType) {
		super();
		this.mateObject = mate.getParseObject();
		this.roomObject = room.getParseObject();
		this.userType = userType;
	}

	public MateInRoom() {}
	
	
	@Override
	public void createParseObject() {
		parseObject = new ParseObject(this.getClass().getSimpleName());
		parseObject.put(MATEOBJECT, mateObject);
		parseObject.put(ROOMOBJECT, roomObject);
		parseObject.put(USERTYPE, userType.getValue());
	}
	
	@Override
	protected void loadChildValues(ParseObject parseObject) {
		this.mateObject = parseObject.getParseObject(MATEOBJECT);
		this.roomObject = parseObject.getParseObject(ROOMOBJECT);
		this.userType = UserType.getUserType( parseObject.getInt(USERTYPE));
	}
	
	
	
	/*
	 * For Parcelable 	
	 */
	

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		super.writeParentToParcel(dest);
		saveParseObject(dest, mateObject);
		saveParseObject(dest, roomObject);
		dest.writeInt(userType.getValue());
	}
	
	private MateInRoom(Parcel in) {
		super.readParent(in);
		mateObject = readPaseObject(in);
		mateObject = readPaseObject(in);
		userType = UserType.getUserType(in.readInt());
	}

	public static final Parcelable.Creator<MateInRoom> CREATOR = new Parcelable.Creator<MateInRoom>() {
		public MateInRoom createFromParcel(Parcel in) {
			return new MateInRoom(in);
		}

		public MateInRoom[] newArray(int size) {
			return new MateInRoom[size];
		}
	};
}
