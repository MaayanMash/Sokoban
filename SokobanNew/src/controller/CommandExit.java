package controller;

import view.iView;

public class CommandExit extends CommandA{

	private iView view;
	
	public CommandExit(iView theView) {
		this.view=theView;
	}
	
	@Override
	public void execute()  {
		this.view.displayExit();
		
	}

}
