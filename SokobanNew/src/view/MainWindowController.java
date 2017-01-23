package view;

import java.io.File;
import java.net.URL;
import java.sql.Struct;
import java.util.ResourceBundle;

import com.sun.javafx.applet.ExperimentalExtensions;

import commons.Level2D;
import commons.position;
import controller.CommandA;

import java.util.Collection;
import java.util.Observable;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainWindowController extends Observable implements Initializable, iView {
	
	/*char [][] sokobanData={
			{'#','#','#','#','#','#','#','#','#','#'},
			{'#','a',' ',' ',' ','@',' ',' ','o','#'},
			{'#',' ',' ',' ',' ','@',' ',' ','o','#'},
			{'#',' ',' ',' ',' ','@',' ',' ','o','#'},
			{'#',' ',' ',' ',' ',' ',' ',' ','$','#'},
			{'#',' ',' ',' ',' ',' ',' ','#','#','#'},
			{'#',' ',' ',' ',' ',' ','#','#','#','#'},
			{'#',' ',' ',' ',' ',' ',' ',' ',' ','#'},
			{'#',' ',' ',' ',' ',' ',' ',' ',' ','#'},
			{'#','#','#','#','#','#','#','#','#','#'},	
	};*/
	
	@FXML
	SokobanDisplayer SokobanDisplayer;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//this.SokobanDisplayer.setSokobanData(sokobanData);
		
		//here you give the focus with click on a mouse
		this.SokobanDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->SokobanDisplayer.requestFocus());
		
		//here we need to do a notify
		this.SokobanDisplayer.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				//int cRow=SokobanDisplayer.getcRow();
				//int cCol=SokobanDisplayer.getcCol();
				 //here we need to do notify and cach it where we want
				String command=null;
				if(event.getCode()==KeyCode.UP)
				{
					command="move up";
					//SokobanDisplayer.setCharcterPos(cRow-1, cCol);
				}
				if(event.getCode()==KeyCode.DOWN)
				{
					//SokobanDisplayer.setCharcterPos(cRow+1, cCol);
					command="move down";
				}
				if(event.getCode()==KeyCode.LEFT)
				{
					command="move left";
					//SokobanDisplayer.setCharcterPos(cRow, cCol-1);
				}
				if(event.getCode()==KeyCode.RIGHT)
				{
					//SokobanDisplayer.setCharcterPos(cRow, cCol+1);
					command="move right";
				}
				setChanged();
				notifyObservers(command);
			}
		});
	}
	
	public void start(){		
		System.out.println("start");
	}
	
	public void openFile(){
		FileChooser fc =new FileChooser();
		fc.setTitle("open sokoban file");
		fc.setInitialDirectory(new File("./resources"));
		fc.getExtensionFilters().addAll(new ExtensionFilter("Text File", "*.txt"), new ExtensionFilter("XML File", "*.xml"), new ExtensionFilter("Object File", "*.obj"));
		
		//fc.setSelectedExtensionFilter(new filter);;
		File chosen = fc.showOpenDialog(null);
		if(chosen != null){
			setChanged();
			notifyObservers("load "+ chosen.getPath());
			setFocus();
			//System.out.println(chosen.getName());
		}

	}
	
	private void setFocus()
	{
		SokobanDisplayer.focusedProperty().addListener(new ChangeListener<Boolean>() 
		{
			@Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) 
            {
                Platform.runLater(new Runnable()
                {
                    public void run() 
                    {
                    	SokobanDisplayer.requestFocus();
                    }
                });                    
            }
        });
	}

	@Override
	public void displayLevel(Level2D theLevel) {
		System.out.println(theLevel);
		this.SokobanDisplayer.setSokobanCol(theLevel.getSizeCol());
		this.SokobanDisplayer.setSokobanRow(theLevel.getSizeRow());
		this.SokobanDisplayer.setSokobanData(theLevel.getBoared());
	}

	@Override
	public void displayMassege(String massege) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayExit() {
		// TODO Auto-generated method stub
		
	}

	
}
