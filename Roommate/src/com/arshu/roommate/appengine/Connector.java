package com.arshu.roommate.appengine;

import java.io.IOException;

import android.content.Context;
import android.os.AsyncTask;

import com.arshu.roommate.CloudEndpointUtils;
import com.arshu.roommate.server.endpoint.rmendpoint.Rmendpoint;
import com.arshu.roommate.server.endpoint.rmendpoint.model.Mate;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson2.JacksonFactory;

public class Connector {

	public static Mate registerMate(Mate mate) throws IOException {
		//Rmendpoint.Builder 
		
		Rmendpoint.Builder  endpointBuilder = new Rmendpoint.Builder(
				AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
				new HttpRequestInitializer() {
					public void initialize(HttpRequest httpRequest) {
					}
				});
		Rmendpoint  endpoint = CloudEndpointUtils.updateBuilder(
				endpointBuilder).build();

		Mate result = endpoint.registerMate(mate).execute();
		return result;
	}

	public class EndpointsTask extends AsyncTask<Context, Integer, Long> {
		protected Long doInBackground(Context... contexts) {

			return (long) 0;
		}
	}
}
