package controller;

import controller.generic.GenericController;
import view.iView;

public class CommandExit extends CommandA{

	private GenericController gc;
	
	public CommandExit(GenericController gc) {
		this.gc=gc;
	}
	
	@Override
	public void execute()  {
		this.gc.stop();
	}

}
