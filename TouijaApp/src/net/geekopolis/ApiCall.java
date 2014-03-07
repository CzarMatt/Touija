package net.geekopolis;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import android.os.AsyncTask;
import android.util.Log;

public abstract class ApiCall extends AsyncTask<Void,Void,HttpResponse> {
	
	public static final String TAG = ApiCall.class.toString();

	public void finish(HttpResponse response, Callback callback) {
		if (response == null) {
			if (callback != null) {
				callback.receivedError("ERROR: No response");
			}
			return;
		}
		HttpEntity entity = response.getEntity();
		if (entity == null) {
			if (callback != null) {
				callback.receivedError("ERROR: No content for response");
			}
			return;
		}
		try {
			InputStream is = entity.getContent();
			if (is == null) {
				if (callback != null) {
					callback.receivedError("ERROR: Unable to read response");
				}
				return;
			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    byte[] buffer = new byte[1];
		    int length = 0;
		    while ((length = is.read(buffer)) != -1) {
		        baos.write(buffer, 0, length);
		    }
		    String results = new String(baos.toByteArray());
		    Log.d(TAG, String.format("Results: %s", results));
		    if (callback != null) {
		    	callback.receivedResponse(results);
		    }
		} catch (IOException e) {
			if (callback != null) {
				callback.receivedError("ERROR: Unable to read response");
			}
			Log.e(TAG, "Unable to read response", e);
		}
	}
}
