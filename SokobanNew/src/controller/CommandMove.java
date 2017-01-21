package controller;

import java.util.HashMap;

import model.iModel;



public class CommandMove extends CommandA{

	private iModel model;
	private HashMap<String,Runnable> hmMove;
	
	//C'tor
	public CommandMove(iModel theModel) {
		this.model=theModel;
		hmMove=new HashMap<String, Runnable>();
		initHashMap();
	}
	
	private void initHashMap() {

		Runnable up= new Runnable() {
			
			@Override
			public void run() {
				model.moveUp();
			}
		};
		hmMove.put("up",up);
		hmMove.put("Up",up);
		Runnable down= new Runnable() {
			
			@Override
			public void run() {
				model.moveDown();
			}
		};
		hmMove.put("down",down);
		hmMove.put("Down",down);
		Runnable left= new Runnable() {
			
			@Override
			public void run() {
				model.moveLeft();
			}
		};
		hmMove.put("left",left);
		hmMove.put("Left",left);
		Runnable right= new Runnable() {
			
			@Override
			public void run() {
				model.moveRight();
			}
		};
		hmMove.put("right",right);
		hmMove.put("Right",right);
	}

	@Override
	public void execute()  {
		hmMove.get(params).run();
	}

}
	
	
	





