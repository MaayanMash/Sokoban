package controller;

import java.util.HashMap;

import javafx.beans.property.StringProperty;
import model.iModel;



public class CommandMove extends CommandA{

	private iModel model;
	private HashMap<String,Runnable> hmMove;
	private StringProperty countSteps;
	
	//C'tor
	public CommandMove(iModel theModel, StringProperty countSteps) {
		this.model=theModel;
		this.countSteps = countSteps;
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
	public void execute() {
		int steps;
		Runnable run=hmMove.get(params);
		if (run==null)
			model.error();
		else{
			run.run();
			steps= model.getSteps();
			this.countSteps.set(""+(steps));
		}
	}

}
	
	
	





