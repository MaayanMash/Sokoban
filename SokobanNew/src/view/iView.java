package view;

import commons.Level2D;
import javafx.beans.property.StringProperty;

public interface iView {
	public void displayLevel(Level2D theLevel);
	public void displayMassege(String massege);
	public void displayExit();
	
	public void createBindSteps(StringProperty Counter);
}
