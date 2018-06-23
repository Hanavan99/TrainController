package controller.net;

public abstract class DataConnection {

	public DataConnection(String arg0) {
		
	}
	
	public abstract void start();
	
	public abstract void sendCommand(int command);
	
	public abstract void stop();
	
}
