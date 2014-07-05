package com.arshu.roommate.bean;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.arshu.roommate.exception.RMException;
import com.arshu.roommate.util.RMConstants.TaskStatus;
import com.arshu.roommate.util.RMLog;
import com.j256.ormlite.field.DatabaseField;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

public abstract class RMBaseValueObject implements Parcelable {
	
	private static final String ID= "name";
	@DatabaseField(generatedId = true)
	private long id; 
	
	private static final String OBJECT_ID= "objectid";
	@DatabaseField
	private String objectid;
	
	private static final String CLASSNAME= "className";
	@DatabaseField
	private String className;
	
	private static final String UPDATEAT= "updateAt";
	@DatabaseField
	private long updateAt;
	
	
	public boolean isChangeInServer(){
		boolean isChange = true;
		if(null != parseObject && updateAt > 0){
			if(parseObject.getUpdatedAt().getTime() == updateAt){
				isChange = false;
			}
		}
			
		return isChange;
	}
	

	protected ParseObject parseObject;

	public ParseObject getParseObject() {
		return parseObject;
	}

	public void setParseObject(ParseObject parseObject) {
		this.parseObject = parseObject;
	}

	abstract protected void loadChildValues(ParseObject parseObject);

/************************************************************************************************
 	DB
*************************************************************************************************/	
	protected static void loadFromCursor(Cursor cursor,RMBaseValueObject baseValueObject){
		baseValueObject.className = cursor.getString(cursor.getColumnIndex(CLASSNAME));
		baseValueObject.id = cursor.getLong(cursor.getColumnIndex(CLASSNAME));
		baseValueObject.objectid = cursor.getString(cursor.getColumnIndex(OBJECT_ID));
		baseValueObject.updateAt = cursor.getLong(cursor.getColumnIndex(UPDATEAT));
		
	}
	
/************************************************************************************************
 	Parse
*************************************************************************************************/
	final public void loadValuesFromParseobject(ParseObject parseObject) {
		this.parseObject = parseObject;
		try {
			this.parseObject.fetchIfNeeded();
			loadChildValues(parseObject);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public ParseQuery<ParseObject>  get(){
		ParseQuery<ParseObject> query = ParseQuery.getQuery(this.getClass().getSimpleName());
		return query;
	}


	/**
	 * This will create the parse object. This is called when an object is saved
	 * for the first time
	 * 
	 * @return
	 */
	public abstract void createParseObject();
	
	public void saveInBackground(final TaskCompleteCallBack callback) {
		if (parseObject == null) {
			createParseObject();
		}
		if (parseObject == null) {
			callback.onComplete(TaskStatus.FAILURE, null, new RMException("parseObject is null"));
			return;
		}
		parseObject.saveEventually(new SaveCallback() {

			@Override
			public void done(ParseException e) {
				// String objectId = null;
				if (e == null) {

					try {
						parseObject.refresh();
					} catch (ParseException e1) {
						RMLog.unexpected(getClass(), "ParseException on refresh");
						e1.printStackTrace();
					}
					callback.onComplete(TaskStatus.SCUCCESS, parseObject, null);
				} else {
					callback.onComplete(TaskStatus.FAILURE, null, new RMException(e));
				}
			}
		});

	}
/************************************************************************************************
 	Parcelable
*************************************************************************************************/
	

	@Override
	public int describeContents() {
		return 0;
	}

	public void writeParentToParcel(Parcel dest) {
		saveParseObject(dest, parseObject);
	}

	public void readParent(Parcel in) {
		parseObject = readPaseObject(in);
	}

	public void saveParseObject(Parcel dest, ParseObject pObject) {
		if (pObject == null) {
			RMLog.unexpected(RMBaseValueObject.class, "parseObject null while trying to writeParentToParcel");
		}
		dest.writeString(pObject.getObjectId());
		dest.writeString(pObject.getClassName());
		dest.writeLong(pObject.getUpdatedAt().getTime());
	}

	public ParseObject readPaseObject(Parcel in) {
		RMLog.d(RMBaseValueObject.class, "LoggedMate: readPaseObject start");
		objectid = in.readString();
		className = in.readString();
		updateAt= in.readLong();
		
		ParseObject pObject = new ParseObject(className);
		pObject.setObjectId(objectid);
		
		RMLog.d(RMBaseValueObject.class, "LoggedMate: readPaseObject end");
		return pObject;
	}
}
