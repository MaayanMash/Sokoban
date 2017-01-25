package controller;

import controller.server.MyServer;
import controller.server.SokobanClientHandler;
import controller.server.clientHandler;
import view.iView;

public class CommandDisplayMassege extends CommandA {

	private iView view;
	private SokobanClientHandler ch;
	
	//C'tor
	public CommandDisplayMassege(iView theView,SokobanClientHandler ch) {
		this.view = theView;
		this.ch=ch;
	}
	@Override
	public void execute() {
		view.displayMassege(params);
		if (ch!=null){
			ch.insertToQueue(params);
			System.out.println("vv");
		}
			
	}

}
