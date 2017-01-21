package commons;

import java.io.Serializable;

public interface position  extends Serializable{
	public position getPosition();
	public boolean equalsPos(position pos);
}
