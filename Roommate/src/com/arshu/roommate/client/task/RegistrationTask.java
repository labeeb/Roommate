package com.arshu.roommate.client.task;

import java.io.IOException;

import android.content.Context;
import android.os.AsyncTask;

import com.arshu.roommate.client.appengine.Connector;
import com.arshu.roommate.server.endpoint.rmendpoint.model.AEMate;

public class RegistrationTask extends AsyncTask<Context, Integer, Long> {
	
	private AEMate mate;
	
	public RegistrationTask(AEMate mate) {
		this.mate = mate;
	}
	

	@Override
	protected Long doInBackground(Context... params) {
		try {
			AEMate loginResponse = Connector.registerMate(mate);
			
		
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
