package com.arshu.roommate.client.loader;

import java.sql.SQLException;

import com.arshu.roommate.client.db.RMDBHelper;
import com.arshu.roommate.client.db.RMDBUtil;
import com.arshu.roommate.client.db.entity.CLRoom;
import com.j256.ormlite.android.AndroidCompiledStatement;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.StatementBuilder.StatementType;

import android.content.Context;
import android.database.Cursor;

public class RoomCursorLoader extends SimpleCursorLoader  {
	private RMDBHelper mHelper;

	public RoomCursorLoader(Context context) {
		super(context);
		mHelper = RMDBHelper.getHelper(context);
	}

	@Override
	public Cursor loadInBackground() {
		Cursor cursor = null;
		QueryBuilder<CLRoom, Integer> qb;
		PreparedQuery<CLRoom> query;
		try {
			qb = RMDBUtil.getRoomDao(mHelper).queryBuilder();
			query = qb.prepare();
			AndroidCompiledStatement compiledStatement = (AndroidCompiledStatement) query
					.compile(mHelper.getConnectionSource()
							.getReadOnlyConnection(), StatementType.SELECT);
			cursor = compiledStatement.getCursor();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (cursor != null) {
			cursor.getCount();
		}
		
		return cursor;
	}
}
