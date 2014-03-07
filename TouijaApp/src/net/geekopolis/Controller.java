package net.geekopolis;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Controller extends Activity implements Callback {

	public static final String TAG = Controller.class.toString();
	@SuppressWarnings("unused")
	private Callback messagesCallback;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.controller);
		
		messagesCallback = new Callback() {

			@Override
			public void receivedResponse(String result) {
				JSONObject json;
				try {
					json = new JSONObject(result);
					String message = json.getString("message");
					TextView messageText = (TextView) findViewById(R.id.textView2);
					messageText.append(message);
				} catch (JSONException e) {
					Log.i("REMOVE", String.format("Error = " + e));
					// e.printStackTrace();
				}
			}

			@Override
			public void receivedError(String message) {
				// TODO Auto-generated method stub
			}
		};
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.controller, menu);

		return true;
	}

	public void sendLeft(View view) {
		Send.command("l", this);
	}

	public void sendForward(View view) {
		Send.command("f", this);
	}

	public void sendRight(View view) {
		Send.command("r", this);
	}

	public void sendStay(View view) {
		Send.command("s", this);
	}

	@Override
	public void receivedResponse(String response) {
		Toast.makeText(this, response, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void receivedError(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

}