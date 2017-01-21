package model.data;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import commons.level;

public class MyObjectLevelSaver implements levelSaver {

	@Override
	public void seveLevel(level level, OutputStream out) throws IOException {
		ObjectOutputStream outObj = new ObjectOutputStream(new BufferedOutputStream(out));
		outObj.writeObject(level);
		outObj.close();
		System.out.println("object file saved!");
	}

}
