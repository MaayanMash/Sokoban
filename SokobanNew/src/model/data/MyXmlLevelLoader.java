package model.data;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import commons.Level2D;

public class MyXmlLevelLoader implements levelLoader {

	@Override
	public Level2D loadLevel(InputStream in) throws IOException, ClassNotFoundException {
		XMLDecoder xmlDec = new XMLDecoder(new BufferedInputStream(in));
		Level2D le = (Level2D)xmlDec.readObject();
		in.close();
		System.out.println("XML File loaded!");
		return le;
	}

}
