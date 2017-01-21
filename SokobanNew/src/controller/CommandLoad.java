package controller;


import java.io.IOException;
import java.util.HashMap;

import model.iModel;


public class CommandLoad extends CommandA {
	
	private iModel model;
	private HashMap<String, Runnable> hmLoader;

	//C'tor
	public CommandLoad(iModel theModel) {
		this.model=theModel;
		hmLoader=new HashMap<String,Runnable>();
		initHashMap();
	}
	
	private void initHashMap (){
		hmLoader.put("txt",new Runnable() {
			@Override
			public void run() {
					model.txtLevelLoad(params);
			}
		});
		hmLoader.put("xml",new Runnable() {
			@Override
			public void run() {
					model.xmlLevelLoad(params);
			}
		});
		hmLoader.put("obj",new Runnable() {
			@Override
			public void run() {
					model.objLevelLoad(params);
			}
		});

	}
	
	@Override
	public void execute() {
		String fileType=params.substring(params.length()-3);
		hmLoader.get(fileType).run();	
	}
	




}
