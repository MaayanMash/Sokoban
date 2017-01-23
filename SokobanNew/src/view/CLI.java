package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import commons.Level2D;
import controller.CommandDisplayLevel;
import controller.CommandExit;
import controller.CommandLoad;
import controller.CommandMove;
import controller.CommandSave;
import controller.generic.iCommand;


public class CLI {
	
	Level2D theLevel;
	private HashMap<String, iCommand> hmCommands;
	//try
	private HashMap<String , iCommand> hmCommands2;
	
	
	//Def C'tor
	public CLI() {
		/*hmCommands= new HashMap<String, command>();
		CommandLoad cl = new CommandLoad();
		this.hmCommands.put("load",cl );
		this.hmCommands.put("Load",cl );
		CommandSave cs =new CommandSave();
		this.hmCommands.put("Save", cs);
		this.hmCommands.put("save", cs);
		CommandDisplay cd = new CommandDisplay();
		this.hmCommands.put("Display", cd);
		this.hmCommands.put("display", cd);
		CommandMove cm =new CommandMove();
		this.hmCommands.put("Move", cm);
		this.hmCommands.put("move", cm);
		CommandExit ce =new CommandExit();
		this.hmCommands.put("exit", ce);
		this.hmCommands.put("Exit", ce);
		*/
		hmCommands2= new HashMap<String, iCommand>();
		this.hmCommands2.put("laod",new CommandLoad(str));
	}
	
	public void start(){
		this.printMenu();
		Scanner scan = new Scanner(System.in);
		String str;
		String [] arrStr;
		while(true){
			str = scan.nextLine();
			arrStr = str.split(" ");
			iCommand c = hmCommands.get(arrStr[0]);
	        if (c==null)
				System.out.println("worng input please Enter again");
			else{
				try {
					if(c instanceof commandString){
						//input like 'load '
						commandString cs = (commandString)c;
						cs.SetString(arrStr[1]);
						theLevel=cs.execute(theLevel);
						} 					
					else {
						theLevel=c.execute(theLevel);
						if (theLevel== null){
							System.out.println("exit");
							break;
							}
						}
					}				
				catch (ClassNotFoundException | IOException e) {
						System.out.println("Error");
						//e.printStackTrace();
					}
			}
			
			
		}
	}

	
	private void printMenu(){
		System.out.println("Wellcome");
		System.out.print("Maayan Mash 204249536");
		System.out.println(" , Tal Arbiv 307907253");
		System.out.println("To load level - please write 'load path'");
		System.out.println("To save level - please write 'save path'");
		System.out.println("To display level - please write 'display'");
		System.out.println("To move - please write 'move up/down/right/left'");
		System.out.println("To exit - please write 'exit'");
	}
}
