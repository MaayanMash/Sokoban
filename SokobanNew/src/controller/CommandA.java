package controller;

import controller.generic.iCommand;

public abstract class CommandA implements iCommand {
	
	protected String params;

	@Override
	public abstract void execute() ;

	@Override
	public void setParams(String params) {
		this.params=params;
	}

}
