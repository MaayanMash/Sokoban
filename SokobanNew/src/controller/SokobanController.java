package controller;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.text.SimpleAttributeSet;

import controller.generic.GenericController;
import controller.generic.iCommand;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.iModel;
import view.iView;

public class SokobanController implements Observer {
	
	private GenericController gc;
	private iModel theModel;
	private iView theView;
	private HashMap<String, iCommand> hmCommands;
	private StringProperty countSteps;
	
	//Ctor
	public SokobanController(iModel model,iView view) {
		gc=new GenericController();
		this.theModel=model;
		this.theView=view;
		this.countSteps=new SimpleStringProperty();
		hmCommands= new HashMap<String, iCommand>();
		initHashMap();
		gc.start();
		this.theView.createBindSteps(this.countSteps);
	}
	
	private void initHashMap(){
		CommandLoad cl = new CommandLoad(theModel);
		this.hmCommands.put("load",cl );
		this.hmCommands.put("Load",cl );
		CommandSave cs =new CommandSave(theModel);
		this.hmCommands.put("Save", cs);
		this.hmCommands.put("save", cs);
		CommandDisplayLevel cd = new CommandDisplayLevel(theView, theModel);
		this.hmCommands.put("DisplayLevel", cd);
		this.hmCommands.put("displayLevel", cd);
		CommandMove cm =new CommandMove(theModel, countSteps);
		this.hmCommands.put("Move", cm);
		this.hmCommands.put("move", cm);
		CommandExit ce =new CommandExit(gc);
		this.hmCommands.put("exit", ce);
		this.hmCommands.put("Exit", ce);
		CommandDisplayMassege cdm = new CommandDisplayMassege(theView);
		this.hmCommands.put("DisplayMassege", cdm);
		this.hmCommands.put("displayMassege", cdm);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		String[] input = ((String)arg).split(" ",2);
		String commandName = input[0];
		String params = null;	
		if(input.length > 1)
			params = input[1];
		
		iCommand cm = hmCommands.get(commandName);
		if (cm==null)
		{
			theView.displayMassege("Command not found");
			return;
		}
		cm.setParams(params);
		gc.insertCommand(cm);	
	}
	


}
