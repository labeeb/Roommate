package com.arshu.roommate.task;

import java.io.IOException;

import android.content.Context;
import android.os.AsyncTask;

import com.arshu.roommate.appengine.Connector;
import com.arshu.roommate.server.endpoint.rmendpoint.model.Mate;

public class RegistrationTask extends AsyncTask<Context, Integer, Long> {
	
	private Mate mate;
	
	public RegistrationTask(Mate mate) {
		this.mate = mate;
	}
	

	@Override
	protected Long doInBackground(Context... params) {
		try {
			Mate loginResponse = Connector.registerMate(mate);
			
		
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
