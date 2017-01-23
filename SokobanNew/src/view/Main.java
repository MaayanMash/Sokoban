package view;
	


import controller.SokobanController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.SokobanModel;
import model.iModel;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application 
{
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));				 
			BorderPane root = (BorderPane) loader.load();
			MainWindowController view = loader.getController();
			
			SokobanModel model=new SokobanModel();
			SokobanController sokobanController = new SokobanController(model, view);

			model.addObserver(sokobanController);
			view.addObserver(sokobanController);	
			
			Scene scene = new Scene(root,900,700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}

