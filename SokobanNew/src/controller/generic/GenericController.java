package controller.generic;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class GenericController {
	
	private BlockingQueue<iCommand> commandQueue;
	private boolean stop;
	
	public GenericController() {
		this.commandQueue= new ArrayBlockingQueue<>(10);
		this.stop=false;
	}

	public void start(){

		new Thread(new Runnable() {
			@Override
			public void run() {
				while(!stop){
					try {
						iCommand cm=commandQueue.poll(1, TimeUnit.SECONDS);
						if(cm!=null)
							cm.execute();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	public void stop(){
		this.stop=true;
	}
	public void insertCommand(iCommand c){
		try {
			commandQueue.put(c);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

}
