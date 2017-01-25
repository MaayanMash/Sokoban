package controller.server;

import java.io.InputStream;
import java.io.OutputStream;


public interface clientHandler {
	public void handleClient(InputStream inFromUser, OutputStream outToClient);

}
