package commons;

import java.io.Serializable;

public interface status extends Serializable{
	public status getStatus();
	public int getCountSteps();
	public double getGrade();
	public void addStep();
	//maybe update grade
	
	
}
