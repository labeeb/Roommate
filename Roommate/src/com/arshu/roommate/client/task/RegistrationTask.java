package com.arshu.roommate.client.task;

import java.io.IOException;

import android.content.Context;
import android.os.AsyncTask;

import com.arshu.roommate.client.appengine.Connector;
import com.arshu.roommate.client.db.entity.CLMate;

public class RegistrationTask extends AsyncTask<Context, Integer, Long> {
	
	private CLMate mate;
	
	public RegistrationTask(CLMate mate) {
		this.mate = mate;
	}
	

	@Override
	protected Long doInBackground(Context... params) {
		try {
			CLMate loginResponse = Connector.registerMate(mate);
			
		
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
