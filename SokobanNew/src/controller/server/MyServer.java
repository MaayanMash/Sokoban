package controller.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MyServer {
	
	private int port;
	private clientHandler clientHandler;
	private volatile boolean stop; 
	
	public MyServer(int port, clientHandler clientHandler) {
		
		this.port = port;
		this.clientHandler = clientHandler;
		this.stop = false;
	}
	
	public void runServer() throws Exception{
		
		ServerSocket server = new ServerSocket(this.port);
		server.setSoTimeout(1000);
		
		//Waiting to the next client
		while(!stop)
		{
			try
			{
				//Blocking calls from clients
				Socket aClient = server.accept();
				System.out.println("Client is connected!");
				InputStream inFromUser = aClient.getInputStream();
				OutputStream outToClient = aClient.getOutputStream();
			
				this.clientHandler.handleClient(inFromUser, outToClient);
		
				inFromUser.close();
				outToClient.close();
				aClient.close();
			}
			catch (SocketTimeoutException e) 
			{
				//System.out.println("ERROR: Timeout.");
			}
		}
		server.close();
	}

	public void start()
	{
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					runServer();
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
	}
	
	public void stop()
	{
		this.stop = true;
	}


}
