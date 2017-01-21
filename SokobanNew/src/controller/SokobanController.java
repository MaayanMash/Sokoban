package controller;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import model.iModel;
import view.iView;

public class SokobanController implements Observer {
	
	private GenericController gc;
	private iModel theModel;
	private iView theView;
	private HashMap<String, iCommand> hmCommands;
	
	//Ctor
	public SokobanController(iModel model,iView view) {
		gc=new GenericController();
		this.theModel=model;
		this.theView=view;
		hmCommands= new HashMap<String, iCommand>();
		initHashMap();
		gc.start();
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
		CommandMove cm =new CommandMove(theModel);
		this.hmCommands.put("Move", cm);
		this.hmCommands.put("move", cm);
		CommandExit ce =new CommandExit(theView);
		this.hmCommands.put("exit", ce);
		this.hmCommands.put("Exit", ce);
		CommandDisplayMassege cdm = new CommandDisplayMassege(theView);
		this.hmCommands.put("DisplayMassege", cdm);
		this.hmCommands.put("displayMassege", cdm);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		String[] params={null,null};
		iCommand cm = hmCommands.get(params[0]);
		if (cm==null)
		{
			theView.displayMassege("Command not found");
			return;
		}
		cm.setParams(params[1]);
		gc.insertCommand(cm);	
	}
	


}
