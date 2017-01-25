package controller;

import controller.server.SokobanClientHandler;
import model.iModel;

public class CommandDisplayCli extends CommandA {
	
	private SokobanClientHandler theClientHandler;
	private iModel theModel;
	
	public CommandDisplayCli(iModel model, SokobanClientHandler ClientHandler) {
		this.theClientHandler=ClientHandler;
		this.theModel=model;
	}

	@Override
	public void execute() {
		this.theClientHandler.sendLevel(this.theModel.getCurrentLevel().getBoared());
	}

}
