package view;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import commons.Level2D;
import java.util.Observable;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Popup;
import javafx.stage.PopupBuilder;

public class MainWindowController extends Observable implements Initializable, iView {
	
	@FXML private SokobanDisplayer SokobanDisplayer;
	private Popup msg;
	
	//timer
	@FXML private Text SokobanTimer;
	private int count;
	private StringProperty CounterTime;
	private Timer timer;
	
	//steps
	@FXML private Text SokobanSteps;
	
	public MainWindowController() {
		this.CounterTime=new SimpleStringProperty();
	}
	
	@Override
	public void createBindSteps(StringProperty Counter){
		this.SokobanSteps.textProperty().bind(Counter);
	}
	
	public void startTimer() {
		this.count=0;
		this.timer=new Timer();
		SokobanTimer.textProperty().bind(this.CounterTime);
		this.timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				CounterTime.set(" "+(++count));
			}
		}, 0, 1000);
		
	}
	
	public void stopTimer(){
		if(this.timer!=null)
			this.timer.cancel();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
		fc.setTitle("open level");
		fc.setInitialDirectory(new File("./resources"));
		fc.getExtensionFilters().addAll(new ExtensionFilter("Text File", "*.txt"), new ExtensionFilter("XML File", "*.xml"), new ExtensionFilter("Object File", "*.obj"));
		
		File chosen = fc.showOpenDialog(null);
		if(chosen != null){
			setChanged();
			notifyObservers("load "+ chosen.getPath());
			setFocus();
			stopTimer();
			startTimer();
		}

	}
	
	public void saveFile(){
		FileChooser fc =new FileChooser();
		fc.setTitle("save level");
		fc.setInitialDirectory(new File("./resources"));
		fc.getExtensionFilters().addAll(new ExtensionFilter("Text File", "*.txt"), new ExtensionFilter("XML File", "*.xml"), new ExtensionFilter("Object File", "*.obj"));
		
		File chosen = fc.showSaveDialog(null);
		if(chosen != null){
			setChanged();
			notifyObservers("save " + chosen.getPath());
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
		this.SokobanDisplayer.setSokobanCol(theLevel.getSizeCol());
		this.SokobanDisplayer.setSokobanRow(theLevel.getSizeRow());
		this.SokobanDisplayer.setSokobanData(theLevel.getBoared());
		System.out.println("col: " + theLevel.getSizeCol());
		System.out.println("row: " + theLevel.getSizeRow());
	}

	//@Override
	/*
	public void displayMassege(String massege) {
		msg = PopupBuilder.create().content(contentNode).width(50).height(100).autoFix(true).build();
		pop.show(stage);
		// TODO Auto-generated method stub
		
	}*/

	@Override
	public void displayExit() {
		// TODO Auto-generated method stub	
	}
	public StringProperty getCounter() {
		return CounterTime;
	}

	@Override
	public void displayMassege(String massege) {
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				Alert alart =new Alert(AlertType.INFORMATION);
				alart.setHeaderText(massege);
				alart.show();
			}
		});
		
	}




	
}
