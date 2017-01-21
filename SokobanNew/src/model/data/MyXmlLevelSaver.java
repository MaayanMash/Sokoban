package model.data;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import commons.level;

public class MyXmlLevelSaver implements levelSaver {

	@Override
	public void seveLevel(level level, OutputStream out) throws IOException {
		XMLEncoder xmlEnc = new XMLEncoder(new BufferedOutputStream(out));
		xmlEnc.writeObject(level);
		xmlEnc.close();
		System.out.println("XML Level saved!");
		

	}

}
