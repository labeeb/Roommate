package com.arshu.roommate.client.db;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.arshu.roommate.client.util.RMClientConstants;
import com.arshu.roommate.client.util.RMLog;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class RMDBHelper extends OrmLiteSqliteOpenHelper {

	private static String tag = "FBADataDBHelper";

	private static final int DATABASE_VERSION = 1;

	private static final AtomicInteger USAGE_COUNTER = new AtomicInteger(0);

	private static RMDBHelper helper = null;

	RMDBHelper(Context context) {

		super(context, RMClientConstants.SQLITE_FILE_NAME, null,
				DATABASE_VERSION);

	}

	/**
	 * Get the helper, possibly constructing it if necessary. For each call to
	 * this method, there should be 1 and only 1 call to {@link #close()}.
	 */
	public static synchronized RMDBHelper getHelper(Context context) {
		if (helper == null) {

			helper = new RMDBHelper(context);
		}
		USAGE_COUNTER.incrementAndGet();
		return helper;// new FBADataDBHelper(context);
	}

	/**
	 * Close the database connections and clear any cached DAOs. For each call
	 * to {@link #getHelper(Context)}, there should be 1 and only 1 call to this
	 * method. If there were 3 calls to {@link #getHelper(Context)} then on the
	 * 3rd call to this method, the helper and the underlying database
	 * connections will be closed.
	 */
	@Override
	public void close() {
		if (USAGE_COUNTER.decrementAndGet() == 0) {
			RMDBUtil.clearDaoObjects();
			super.close();
		}
	}

	/**
	 * This is called when the database is first created. Usually you should
	 * call createTable statements here to create the tables that will store
	 * your data.
	 */
	@Override
	public void onCreate(SQLiteDatabase arg0, ConnectionSource arg1) {
		RMLog.d(getClass(), "onCreate called");

		try {

			List<Class<?>> dbEntities = RMDBUtil.getAllTables();
			for (Class<?> entityClass : dbEntities) {
				TableUtils
						.createTableIfNotExists(connectionSource, entityClass);
			}
		} catch (SQLException e) {
			RMLog.e(getClass(), "Can't create database");
			throw new RuntimeException(e);
		}

	}

	public void dropEveryTable() throws SQLException {
		List<Class<?>> dbEntities = RMDBUtil.getAllTables();
		for (Class<?> entityClass : dbEntities) {
			TableUtils.dropTable(connectionSource, entityClass, true);
		}
		
	}

	public void clearEveryValues() {

	}

	/**
	 * This is called when your application is upgraded and it has a higher
	 * version number. This allows you to adjust the various data to match the
	 * new version number.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int oldVersion, int newVersion) {
		dropAndRecreateEveryTable(db);
	}

	private void dropAndRecreateEveryTable(SQLiteDatabase db) {
		try {
			dropEveryTable();
		} catch (SQLException e) {
			RMLog.d(getClass(),
					"SQLException dropEveryTable (caller onUpgrade ) ");
			e.printStackTrace();
		}
		onCreate(db, connectionSource);
	}

	public boolean clearTables() {
		boolean status = false;
		// TableUtils.clearTable(connectionSource,<class>);
		status = true; // use try catch to decide on status.
		return status;
	}

}
