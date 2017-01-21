package commons;

import java.io.Serializable;

public interface level extends Serializable,status{

	public int getCountTargets();
	public int getCountBoxs();
	public int getCountBoxOnTargets();
	public int getCountBoxNotOnTargets();
	public boolean ifSolved ();

}
