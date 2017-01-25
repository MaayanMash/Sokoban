package controller;

import controller.generic.GenericController;
import controller.server.MyServer;
import view.iView;

public class CommandExit extends CommandA{

	private GenericController gc;
	private MyServer theServer;
	
	public CommandExit(GenericController gc,MyServer server) {
		this.gc=gc;
	}
	
	@Override
	public void execute()  {
		this.gc.stop();
		if (theServer!=null)
			this.theServer.stop();
	}

}
