
package model.data;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import commons.Level2D;
import commons.Target;
import commons.TwoPoint;
import commons.position;

public class MyXmlLevelLoader implements levelLoader {

	@Override
	public Level2D loadLevel(InputStream in) throws IOException, ClassNotFoundException {
		XMLDecoder xmlDec = new XMLDecoder(new BufferedInputStream(in));
		Level2D le = (Level2D)xmlDec.readObject();
		System.out.println(le);
		xmlDec.close();
		System.out.println("XML File loaded!");
		return le;
	}

}