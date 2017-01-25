package view;

import commons.Level2D;
import javafx.beans.property.StringProperty;
import javafx.stage.Stage;

public interface iView {
	public void displayLevel(Level2D theLevel);
	public void displayMassege(String massege);
	public void displayExit();
	public void createBindSteps(StringProperty Counter);
	public void setPrimaryStage(Stage primaryStage);
	public void exitPrimaryStage();
	
}
