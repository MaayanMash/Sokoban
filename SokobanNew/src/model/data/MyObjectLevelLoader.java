package model.data;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import commons.Level2D;

public class MyObjectLevelLoader implements levelLoader {

	@Override
	public Level2D loadLevel(InputStream in) throws IOException, ClassNotFoundException {
		
		ObjectInputStream inO = new ObjectInputStream(new BufferedInputStream(in));
		 Level2D l= (Level2D) inO.readObject();
		 inO.close();
		 System.out.println("the ObjFile loaded!");
		return l;
	}

}
