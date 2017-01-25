package controller.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SokobanClientHandler extends Observable implements clientHandler {

	private BlockingQueue<String> ToClient;
	private boolean stopSend;
	
	public SokobanClientHandler() {
		this.ToClient=new ArrayBlockingQueue<String>(20);
		this.stopSend=false;
		this.ToClient.clear();
	}
	
	public BlockingQueue<String> getToClient() {
		return this.ToClient;
	}

	public void setToClient(BlockingQueue<String> ToClient) {
		this.ToClient = ToClient;
	}
	
	@Override
	public void handleClient(InputStream inClient, OutputStream outClient) {
	
		try {
			this.ToClient=new ArrayBlockingQueue<String>(20);
			this.ToClient.clear();
			this.stopSend=false;
			
			BufferedReader clientInput=new BufferedReader(new InputStreamReader(inClient));
			PrintWriter serverOutput=new PrintWriter(outClient);
			
			//open a new thread 
			Thread fromClient=createThreadToRead(clientInput, "exit");
			Thread toClient=createThreadToSend(serverOutput);
			
			fromClient.join();
			toClient.join();
			clientInput.close();
			serverOutput.close();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//read from client and notify
	private void readInputs(BufferedReader in, String exit){
		String line;
		boolean flag=false;
		
		try {
			while(!flag){
				
				 line=in.readLine();
				 
				 if(line.equals(exit)){
					flag=true;
					insertToQueue("bye");
					stop();
				}
				else{
					setChanged();
					notifyObservers(line);
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//create thread to read from client
	private Thread createThreadToRead(BufferedReader in, String exitStr){
		
		Thread t=new Thread(new Runnable() {
			
			@Override
			public void run() {
				readInputs(in, exitStr);
			}
		});
		t.start();
		return t;	
	}

	//send to client
	public void sendOutput(PrintWriter out){
		String str;
		while(!this.stopSend){
			try {
				str=getToClient().take();
				out.println(str);
				out.flush();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
	
	//create thread to send to client
	public Thread createThreadToSend(PrintWriter out){
		Thread t=new Thread(new Runnable() {
			
			@Override
			public void run() {
				sendOutput(out);
			}
		});
		t.start();
		return t;
	}
	
	
	//insert the String to queue
	private void insertToQueue(String str){
		try {
			this.ToClient.put(str);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	//send the level (the board)
	public void sendLevel(char[][] Bored){
		
		for(int i=0;i<Bored.length;i++){
			String str=String.valueOf(Bored[i]);
			insertToQueue(str);
		}
		
	}
	
	//stop send to client
	private void stop(){
		this.stopSend=true;
	}
	
}