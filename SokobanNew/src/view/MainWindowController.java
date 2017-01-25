package view;


import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import commons.Level2D;
import java.util.Observable;
import java.util.Optional;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.stage.FileChooser.ExtensionFilter;


public class MainWindowController extends Observable implements Initializable, iView {
	
	@FXML private SokobanDisplayer SokobanDisplayer;
	private Stage primaryStage;
	private SokobanKeyEvent myKey;
	
	//timer
	@FXML private Text SokobanTimer;
	private int countTime;
	private StringProperty CounterTime;
	private Timer timer;
	private boolean time;
	
	//steps
	@FXML private Text SokobanSteps;
	
	//music
	private String musicString;
	private MediaPlayer player;
	private boolean music;
	
	//Ctor'
	public MainWindowController() {
		this.CounterTime=new SimpleStringProperty();
		this.musicString=new File("./resources/music/Game Music.mp3").toURI().toString();
		this.player=new MediaPlayer(new javafx.scene.media.Media(musicString));
		this.music=false;
		this.music=false;
		this.myKey = initKeyEvent("./resources/Settings/Sokoban Key.xml");
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setFocus();
		
		//here you give the focus with click on a mouse
		this.SokobanDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->SokobanDisplayer.requestFocus());
		
		this.SokobanDisplayer.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				String command=null;
				if(event.getCode()==myKey.getUp())
					command="move up";
				if(event.getCode()==myKey.getDown())
					command="move down";
				if(event.getCode()==myKey.getLeft())
					command="move left";
				if(event.getCode()==myKey.getRight())
					command="move right";
				setChanged();
				notifyObservers(command);
			}
		});
	}
	public void setPrimaryStage(Stage primaryStage){
		this.primaryStage=primaryStage;
		exitPrimaryStage();
	}
	
	@Override
	public void createBindSteps(StringProperty Counter){
		this.SokobanSteps.textProperty().bind(Counter);
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
	public void ifsolved(Level2D theLevel){
		if (theLevel.ifSolved())
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					Alert alert =new Alert(AlertType.INFORMATION);
					alert.setHeaderText("The level solved!!");
					alert.setContentText("Time: "+ countTime+"\nSteps: "+ theLevel.getCountSteps());
					alert.show();
					stopTimer();
				}
			});
	}
	
	public void startTimer() {
		this.time=true;
		this.countTime=0;
		this.timer=new Timer();
		SokobanTimer.textProperty().bind(this.CounterTime);
		this.timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				CounterTime.set(" "+(++countTime));
			}
		}, 0, 1000);
		
	}
	public void stopTimer(){
		if(this.timer!=null)
			this.timer.cancel();
	}
	public void continueTime(){
		this.timer=new Timer();
		SokobanTimer.textProperty().bind(this.CounterTime);
		this.timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				CounterTime.set(" "+(++countTime));
			}
		}, countTime, 1000);
	}

	public void startStopMusic(){
		if (this.music)
		{
			this.player.pause();
			this.music=false;
		}
		else
		{
			this.player.play();
			this.music=true;
		}
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
			stopTimer();
			startTimer();
			startStopMusic();
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
	@Override
	public void displayLevel(Level2D theLevel) {
		this.SokobanDisplayer.setSokobanCol(theLevel.getSizeCol());
		this.SokobanDisplayer.setSokobanRow(theLevel.getSizeRow());
		this.SokobanDisplayer.setSokobanData(theLevel.getBoared());
		//need to change position to twopoint
		//this.SokobanDisplayer.setcCol(theLevel.getPosCharacter().getY());
		//this.SokobanDisplayer.setcRow(theLevel.getPosCharacter().getX());
		
		ifsolved(theLevel);
	}
	@Override
	public void displayExit() {
		stopTimer();
		if (this.music)
			startStopMusic();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit");
		alert.setHeaderText("Are you sure you want to exit?");
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == ButtonType.OK){
		    setChanged();
		    notifyObservers("exit");
		    Platform.exit();
		} else {
			if (time)
				continueTime();
			this.startStopMusic();
		    alert.close();
		}
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
	public void exitPrimaryStage(){
		this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				setChanged();
				notifyObservers("exit");
				
			}
			
		});
	}
	
	private SokobanKeyEvent initKeyEvent(String path){
 		XMLDecoder xmlDecoder;
 		SokobanKeyEvent sokobanKey = null;
 		
 		try {
			xmlDecoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(new File(path))));
	 		sokobanKey = (SokobanKeyEvent) xmlDecoder.readObject();
	 		xmlDecoder.close();

		} 
 		catch (FileNotFoundException e) {
			e.printStackTrace();
			setChanged();
			notifyObservers("DisplayMassege Wrong file KeyEvent input ");
		}
 		
 		return sokobanKey;
	}
 	



	
}
