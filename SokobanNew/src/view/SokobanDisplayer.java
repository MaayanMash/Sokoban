package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SokobanDisplayer extends Canvas{
	
	private char [][] sokobanData;
	private StringProperty wallFileName;
	private int cRow,cCol;
	private StringProperty playerFileName;
	private StringProperty boxFileName;
	private StringProperty boxOnTargetFileName;
	private StringProperty targetFileName;
	private StringProperty pathFileName;
	
	//def Ctor
	public SokobanDisplayer() {
		wallFileName = new SimpleStringProperty();
		playerFileName = new SimpleStringProperty();
		boxFileName = new SimpleStringProperty();
		boxOnTargetFileName = new SimpleStringProperty();
		targetFileName = new SimpleStringProperty();
		pathFileName = new SimpleStringProperty();
		//TODO need to change position's player
		this.cRow=1;
		this.cCol=1;
	}
	
	public void redraw(){
		if(this.sokobanData!=null){
			double W = this.getWidth();
			double H = this.getHeight();
			double w = W/ this.sokobanData[0].length;
			double h = H/ this.sokobanData.length;
			
			GraphicsContext gc = getGraphicsContext2D();
			//Images 
			Image wall = null;
			Image player = null;
			Image box = null;
			Image boxOnTarget = null;
			Image target = null;
			Image path =null;
			
			
			try {
				wall =new Image(new FileInputStream(wallFileName.get()));

				//add now
				player = new Image(new FileInputStream(playerFileName.get()));
				box = new Image(new FileInputStream(boxFileName.get()));
				boxOnTarget = new Image(new FileInputStream(boxOnTargetFileName.get()));
				target = new Image(new FileInputStream(targetFileName.get()));
				path = new Image(new FileInputStream(pathFileName.get()));
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//here we clean the canvas
			gc.clearRect(0, 0, W, H);
			
			//here we drow again
			for (int i = 0; i < sokobanData.length; i++) {
				for (int j = 0; j < sokobanData[i].length; j++) {//here we can change to other to if '#' for wall in our program
					//wall
					if(sokobanData[i][j]=='#'){
						if(wall==null)
							gc.fillRect(j*w, i*h, w, h);//def' fill color is black
						else
							gc.drawImage(wall, j*w, i*h, w, h);
					}
					//path
					else
						gc.drawImage(path, j*w, i*h, w, h);
					//box
					if(sokobanData[i][j]=='@'){
						gc.drawImage(box, j*w, i*h, w, h);
					}
					//box on target
					if(sokobanData[i][j]=='$'){
						gc.drawImage(boxOnTarget, j*w, i*h, w, h);
					}
					//target
					if(sokobanData[i][j]=='o'||sokobanData[i][j]=='O'||sokobanData[i][j]=='a'){
						gc.drawImage(target, j*w, i*h, w, h);
					}
					
					else{
						
					}
				}
			}
			//player
			gc.drawImage(player, cCol*w, cRow*h, w, h);
			
			
		}
		
		
	}

	//set's and get's
	//set's and get's file name
	public String getWallFileName() {
		return wallFileName.get();
	}
	public void setWallFileName(String wallFileName) {
		this.wallFileName.set(wallFileName);
	}
	public String getBoxFileName() {
		return boxFileName.get();
	}
	public void setBoxFileName(String boxFileName) {
		this.boxFileName.set(boxFileName);
	}
	public String getBoxOnTargetFileName() {
		return boxOnTargetFileName.get();
	}
	public void setBoxOnTargetFileName(String boxOnTargetFileName) {
		this.boxOnTargetFileName.set(boxOnTargetFileName);
	}
	public String getTargetFileName() {
		return targetFileName.get();
	}
	public void setTargetFileName(String targetFileName) {
		this.targetFileName.set(targetFileName);
	}
	public void setPlayerFileName(String playerFileName) {
		this.playerFileName.set(playerFileName);
	}
	public String getPlayerFileName() {
		return playerFileName.get();
	}
	public String getPathFileName() {
		return pathFileName.get();
	}

	public void setPathFileName(String pathFileName) {
		this.pathFileName.set(pathFileName);
	}	

	public void setSokobanData(char[][] sokobanData) {
		this.sokobanData = sokobanData;
		this.redraw();
	}

	public int getcRow() {
		return cRow;
	}
	public int getcCol() {
		return cCol;
	}
	
	public void setCharcterPos(int cRow,int cCol){
		this.cRow = cRow;
		this.cCol = cCol;
		redraw();
	}


	
}
