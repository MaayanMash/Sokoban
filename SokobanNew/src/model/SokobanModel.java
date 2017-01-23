package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Observable;

import commons.Level2D;
import model.data.MyObjectLevelLoader;
import model.data.MyObjectLevelSaver;
import model.data.MyTextLevelLoader;
import model.data.MyTextLevelSaver;
import model.data.MyXmlLevelLoader;
import model.data.MyXmlLevelSaver;
import model.policy.MySokobanPolicy;
import model.policy.policy;
import model.policy.move.MoveDown;
import model.policy.move.MoveLeft;
import model.policy.move.MoveRight;
import model.policy.move.MoveUp;

public class SokobanModel extends Observable implements iModel{
	
	private Level2D TheLevel;
	private policy ThePolicy;
	

	//Def' cot'
	public SokobanModel() {
		TheLevel=null;
		ThePolicy=new MySokobanPolicy();
	}
	//cot'
	public SokobanModel(policy pol) {
			TheLevel=null;
			ThePolicy=pol;
		}

	@Override
	public Level2D getCurrentLevel() {
		return this.TheLevel;
	}

	@Override
	public void moveUp() {
		if(this.TheLevel==null)
		{
			this.setChanged();
			this.notifyObservers("DisplayMassege the Levle is empty pleas load first");
			return;
		}
		
		//if the level changed
		if(new MoveUp(ThePolicy).movePlayer(TheLevel)!=null){
			this.setChanged();
			notifyObservers("DisplayLevel");
		}
	}
	@Override
	public void moveDown() {
		if(this.TheLevel==null)
		{
			this.setChanged();
			this.notifyObservers("DisplayMassege the Levle is empty pleas load first");
			return;
		}
		
		//if the level changed
		if(new MoveDown(ThePolicy).movePlayer(TheLevel)!=null){
			this.setChanged();
			notifyObservers("DisplayLevel");
		}
		
	}
	@Override
	public void moveRight() {

		if(this.TheLevel==null)
		{
			this.setChanged();
			this.notifyObservers("DisplayMassege the Levle is empty pleas load first");
			return;
		}
		
		//if the level changed
		if(new MoveRight(ThePolicy).movePlayer(TheLevel)!=null){
			this.setChanged();
			notifyObservers("DisplayLevel");
		}
	}
	@Override
	public void moveLeft() {
		if(this.TheLevel==null)
		{
			this.setChanged();
			this.notifyObservers("DisplayMassege the Levle is empty pleas load first");
			return;
		}
		
		//if the level changed
		if(new MoveLeft(ThePolicy).movePlayer(TheLevel)!=null){
			this.setChanged();
			notifyObservers("DisplayLevel");
		}
		//check if the level solved
		if (TheLevel.ifSolved())
		{
			this.setChanged();
			notifyObservers("DisplayMassege The Level Solved!!");
		}
	}

	@Override
	public void txtLevelLoad(String path)   {
		InputStream in;
		try {
			in = new FileInputStream(new File(path));
		} catch (FileNotFoundException e) {
			this.setChanged();
			this.notifyObservers("DisplayMassege The file not found");
			return;
		}
		MyTextLevelLoader txtL=new MyTextLevelLoader();
		try {
			TheLevel=txtL.loadLevel(in);
		} catch (IOException e) {
			this.setChanged();
			this.notifyObservers("DisplayMassege Wrong file input");
			return;
		}
		this.setChanged();
		notifyObservers("displayLevel");
	}
	@Override
	public void objLevelLoad(String path)  {
		InputStream in;
		try {
			in = new FileInputStream(new File(path));
		} catch (FileNotFoundException e) {
			this.setChanged();
			this.notifyObservers("DisplayMassege The file not found");
			return;
		}
		MyObjectLevelLoader objL=new MyObjectLevelLoader();
		try {
			TheLevel=objL.loadLevel(in);
		} catch (IOException | ClassNotFoundException e) {
			this.setChanged();
			this.notifyObservers("DisplayMassege Wrong file input");
			return;
		} 
		this.setChanged();
		notifyObservers("displayLevel");
		
	}
	@Override
	public void xmlLevelLoad(String path) {
		InputStream in;
		try {
			in = new FileInputStream(new File(path));
		} catch (FileNotFoundException e) {
			this.setChanged();
			this.notifyObservers("DisplayMassege The file not found");
			return;
		}
		MyXmlLevelLoader xmlL=new MyXmlLevelLoader();
		try {
			TheLevel=xmlL.loadLevel(in);
		} catch (IOException | ClassNotFoundException e) {
			this.setChanged();
			this.notifyObservers("DisplayMassege Wrong file input");
			return;
		}
		this.setChanged();
		notifyObservers("displayLevel");
		
	}
	
	@Override
	public void txtLevelSave(String path) {
		OutputStream out;
		try {
			out= new FileOutputStream(path);
		} catch (FileNotFoundException e) {
			this.setChanged();
			this.notifyObservers("DisplayMassege Wrong file name");
			return;
		}
		MyTextLevelSaver txtS =new MyTextLevelSaver();
		try {
			txtS.seveLevel(TheLevel, out);
		} catch (IOException e) {
			this.setChanged();
			this.notifyObservers("DisplayMassege The save failed");
			return;
		}

		this.setChanged();
		notifyObservers("DisplayMassege The level saved");
		
		
	}
	@Override
	public void objLevelSave(String path) {
		OutputStream out;
		try {
			out= new FileOutputStream(path);
		} catch (FileNotFoundException e) {
			this.setChanged();
			this.notifyObservers("DisplayMassege Wrong file name");
			return;
		}
		MyObjectLevelSaver objS =new MyObjectLevelSaver();
		try {
			objS.seveLevel(TheLevel, out);
		} catch (IOException e) {
			this.setChanged();
			this.notifyObservers("DisplayMassege The save failed");
			return;
		}

		this.setChanged();
		notifyObservers("DisplayMassege The level saved");
		
		
	}	
	@Override
	public void xmlLevelSave(String path) {
		OutputStream out;
		try {
			out= new FileOutputStream(path);
		} catch (FileNotFoundException e) {
			this.setChanged();
			this.notifyObservers("DisplayMassege Wrong file name");
			return;
		}
		MyXmlLevelSaver xmlS =new MyXmlLevelSaver();
		try {
			xmlS.seveLevel(TheLevel, out);
		} catch (IOException e) {
			this.setChanged();
			this.notifyObservers("DisplayMassege The save failed");
			return;
		}

		this.setChanged();
		notifyObservers("DisplayMassege The level saved");
		
		
	}
	@Override
	public int getSteps() {
		return TheLevel.getCountSteps();
		
	}



}
