package commons;

import java.io.Serializable;

public interface type extends Serializable{
	public String getType();
	public char getC();
	public position getPos();
	public void setPos(position pos);
}
