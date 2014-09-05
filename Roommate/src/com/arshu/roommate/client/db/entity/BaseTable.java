package com.arshu.roommate.client.db.entity;

import com.j256.ormlite.field.DatabaseField;

public class BaseTable {
	
	public static final String ROW_ID ="rowId";
	@DatabaseField(generatedId = true, columnName=ROW_ID)
	protected long rowId;

}
