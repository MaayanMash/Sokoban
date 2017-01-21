package model.data;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;

import commons.level;

public interface levelSaver {
	public void seveLevel (level level, OutputStream out)throws IOException;

}
