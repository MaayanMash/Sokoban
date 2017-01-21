package controller;

import java.util.HashMap;

import model.iModel;

public class CommandSave extends CommandA {

	private HashMap<String,Runnable> hmSave;
	private iModel model;

	//C'tor
	public CommandSave(iModel theModel) {
		this.model=theModel;
		hmSave= new HashMap<String,Runnable>();
		initHashMap();
	}
	
	private void initHashMap (){
		hmSave.put("txt",new Runnable() {
			@Override
			public void run() {
						model.txtLevelSave(params);
			}
		});
		hmSave.put("xml",new Runnable() {
			@Override
			public void run() {
					model.xmlLevelSave(params);
			}
		});
		hmSave.put("obj",new Runnable() {
			@Override
			public void run() {
					model.objLevelSave(params);
			}
		});

	}
	
	
	@Override
	public void execute() {
		String fileType=params.substring(params.length()-3);
		hmSave.get(fileType).run();
	
	}
}
