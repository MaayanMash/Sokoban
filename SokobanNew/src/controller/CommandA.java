package controller;

import controller.generic.iCommand;

public abstract class CommandA implements iCommand {
	
	protected String params;

	@Override
	public abstract void execute() throws Exception;

	@Override
	public void setParams(String params) {
		this.params=params;
	}

}
