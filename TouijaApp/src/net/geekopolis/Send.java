package net.geekopolis;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;

import android.net.http.AndroidHttpClient;
import android.util.Log;

public class Send {

	public static final String TAG = Send.class.getName();
	
	public static final String SEND_URL = "http://www.geekopolis.net/touija/send.php";
	
	public static void command(final String value, final Callback callback) {
		ApiCall task = new ApiCall() {

			@Override
			protected HttpResponse doInBackground(Void... input) {
				
				String userAgent = System.getProperty("http.agent");
				AndroidHttpClient client = AndroidHttpClient.newInstance(userAgent);
				StringBuffer url = new StringBuffer(SEND_URL);
				url.append("?command=").append(value);
				HttpGet request = new HttpGet(url.toString());
				try {
					return client.execute(request);
				} catch (ClientProtocolException e) {
					Log.e(TAG, "Unable to send command", e);
				} catch (IOException e) {
					Log.e(TAG, "Unable to send command", e);
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
				finish(response, callback);
			}
		};
		task.execute();
	}
}
