package view;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Observable;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

public class MainWindowController extends Observable implements Initializable {
	
	char [][] sokobanData={
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
			
	};
	
	
	@FXML
	SokobanDisplayer SokobanDisplayer;
	


	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.SokobanDisplayer.setSokobanData(sokobanData);
		
		//here you give the focus with click on a mouse
		this.SokobanDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->SokobanDisplayer.requestFocus());
		
		//here we need to do a notify
		this.SokobanDisplayer.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				int cRow=SokobanDisplayer.getcRow();
				int cCol=SokobanDisplayer.getcCol();
				 //here we need to do notify and cach it where we want
				if(event.getCode()==KeyCode.UP)
					SokobanDisplayer.setCharcterPos(cRow-1, cCol);
				if(event.getCode()==KeyCode.DOWN)
					SokobanDisplayer.setCharcterPos(cRow+1, cCol);
				if(event.getCode()==KeyCode.LEFT)
					SokobanDisplayer.setCharcterPos(cRow, cCol-1);
				if(event.getCode()==KeyCode.RIGHT)
					SokobanDisplayer.setCharcterPos(cRow, cCol+1);
					
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
		//fc.setSelectedExtensionFilter(new filter);;
		File chosen = fc.showOpenDialog(null);
		if(chosen != null){
			System.out.println(chosen.getName());
		}

	}

	
}
