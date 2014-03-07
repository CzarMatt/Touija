//package net.geekopolis;
//
//import java.io.IOException;
//
//import org.apache.http.HttpResponse;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.methods.HttpGet;
//
//import android.net.http.AndroidHttpClient;
//import android.util.Log;
//
//public class Commands {
//
//	public static final String TAG = Commands.class.getName();
//	
//	public static final String COMMANDS_URL = "http://www.geekopolis.net/touija/commands.php";
//	
//	public static void get(final String value, final Callback callback) {
//		ApiCall task = new ApiCall() {
//
//			@Override
//			protected HttpResponse doInBackground(Void... input) {
//				
//				String userAgent = System.getProperty("http.agent");
//				AndroidHttpClient client = AndroidHttpClient.newInstance(userAgent);
//				StringBuffer url = new StringBuffer(COMMANDS_URL);
//				HttpGet request = new HttpGet(url.toString());
//				try {
//					return client.execute(request);
//				} catch (ClientProtocolException e) {
//					Log.e(TAG, "Unable to get commands", e);
//				} catch (IOException e) {
//					Log.e(TAG, "Unable to get commands", e);
//				} finally {
//					if (client != null) {
//						client.close();
//					}
//				}
//				return null;
//			}
//
//			@Override
//			protected void onPostExecute(HttpResponse response) {
//				super.onPostExecute(response);
//				finish(response, callback);
//			}
//		};
//		task.execute();
//	}
//}
