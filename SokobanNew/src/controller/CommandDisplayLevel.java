package controller;

import model.iModel;

import view.iView;


public class CommandDisplayLevel extends CommandA {
	
	private iView view;
	private iModel model;
	
	public CommandDisplayLevel(iView theView, iModel theModel) {
		this.model=theModel;
		this.view=theView;
	}

	@Override
	public void execute() {
		this.view.displayLevel(this.model.getCurrentLevel());
	}

	
	

}
