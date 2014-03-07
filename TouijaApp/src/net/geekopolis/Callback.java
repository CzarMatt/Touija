package net.geekopolis;

public interface Callback {

	public void receivedResponse(String result);
	public void receivedError(String message);
}
