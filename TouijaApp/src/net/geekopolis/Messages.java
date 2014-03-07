package net.geekopolis;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONObject;

import android.net.http.AndroidHttpClient;
import android.util.Log;

public class Messages {

	public static final String TAG = Messages.class.getName();
	
	public static final String MESSAGES_URL = "http://www.geekopolis.net/touija/messages.php";
	
	public static void command(final String value, final Callback callback) {
		ApiCall task = new ApiCall() {

			@Override
			protected HttpResponse doInBackground(Void... input) {
				
				String userAgent = System.getProperty("http.agent");
				AndroidHttpClient client = AndroidHttpClient.newInstance(userAgent);
				StringBuffer url = new StringBuffer(MESSAGES_URL);
				
				HttpGet request = new HttpGet(url.toString());
				try {
					return client.execute(request);
				} catch (ClientProtocolException e) {
					Log.e(TAG, "Unable to get messages", e);
				} catch (IOException e) {
					Log.e(TAG, "Unable to get messages", e);
				} finally {
					if (client != null) {
						client.close();
					}
				}
				return null;
			}

			@Override
			protected void onPostExecute(HttpResponse response) {
				super.onPostExecute(response);
			}
		};
		task.execute();
	}
}
