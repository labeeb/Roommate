package com.arshu.roommate.client.appengine;

import java.io.IOException;

import android.content.Context;
import android.os.AsyncTask;

import com.arshu.roommate.client.CloudEndpointUtils;
import com.arshu.roommate.client.db.entity.CLMate;
import com.arshu.roommate.server.endpoint.rmendpoint.Rmendpoint;
import com.arshu.roommate.server.endpoint.rmendpoint.model.AEMate;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson2.JacksonFactory;

public class Connector {

	public static CLMate registerMate(CLMate mate) throws IOException {
		//Rmendpoint.Builder 
		
		Rmendpoint.Builder  endpointBuilder = new Rmendpoint.Builder(
				AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
				new HttpRequestInitializer() {
					public void initialize(HttpRequest httpRequest) {
					}
				});
		Rmendpoint  endpoint = CloudEndpointUtils.updateBuilder(
				endpointBuilder).build();

		AEMate result = endpoint.registerMate(mate.createAEMate()).execute();
		return new CLMate(result);
	}
	
	public static CLMate doLogin(String userName,String password) throws IOException {
		//Rmendpoint.Builder 
		
		Rmendpoint.Builder  endpointBuilder = new Rmendpoint.Builder(
				AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
				new HttpRequestInitializer() {
					public void initialize(HttpRequest httpRequest) {
					}
				});
		Rmendpoint  endpoint = CloudEndpointUtils.updateBuilder(
				endpointBuilder).build();

		AEMate content = new AEMate();
		content.setUserName(userName);
		content.setPassword(password);
		AEMate result = endpoint.doLogin(content ).execute();
		return new CLMate(result);
	}

	public class EndpointsTask extends AsyncTask<Context, Integer, Long> {
		protected Long doInBackground(Context... contexts) {

			return (long) 0;
		}
	}
}
