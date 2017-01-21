package model.data;

import java.io.IOException;
import java.io.InputStream;

import commons.Level2D;

public interface levelLoader {
	/**
	 * A:The level loader responsible for loading the level and class level responsible for displaying the level
	 * B:if we want to add a data to class level, we can extend class level and add it. 
	 * In addition, we need to add a new class that extend MyTxtLevelLoader and add what you need 
	 * (anther file type you don't need to do anything).
	 * B+C:because if in the future we want to load a level from another file type; 
	 * we can to implement the interface levelLoader and we don't have a need to change the class Level.
	 * D- Because we need to open the file and read from a file/write into a file.
	 * @param in - inputstream
	 * @return level
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Level2D loadLevel(InputStream in)throws IOException, ClassNotFoundException;
}
