package commons;

import java.io.Serializable;
import java.lang.annotation.Target;
import java.util.ArrayList;

public abstract class LevelA implements level{
	private status st;
	protected Player player; 
	private ArrayList<Box> boxs;
	private ArrayList<commons.Target> targets;
	 
	//Def C'tor maybe we have problem here
	public LevelA() {
		this.st = new StatusLevel();
		this.setPlayer(null);
		this.boxs = new ArrayList<Box>();
		this.targets = new ArrayList<commons.Target>();
	}	
	//C'tor
	public LevelA(status st,Player player, ArrayList<Box> boxs,ArrayList<commons.Target> targets) {
		this.st = st;
		this.setPlayer(player);
		this.boxs = boxs ;
		this.targets = targets;
	}
	
	//copy c'tor
	public LevelA (LevelA level){
		this.st=level.st;
		this.player=level.player;
		this.boxs=level.boxs;
		this.targets=level.targets;
	}

	//gets && sets
	@Override
	public status getStatus() {
		return this.st;
	}
	public ArrayList<Box> getBoxs(){
		return this.boxs;
	}
	public ArrayList<commons.Target> geTargets(){
		return this.targets;
	}	
	public void setStatus(status st) {
		this.st=st;
	}
	public void setBoxs (ArrayList<Box> boxs){
		this.boxs=boxs;
	}
	public void setTargets (ArrayList<commons.Target> targets){
		this.targets=targets;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	
	@Override
	public void addStep() {
		this.st.addStep();	
	}
	
	
	@Override
	public int getCountSteps() {
		return this.st.getCountSteps();
	}
	@Override
	public double getGrade() {
		return this.st.getGrade();
	}

	@Override
	public int getCountTargets() {
		return this.targets.size();
	}
	@Override
	public int getCountBoxs(){
		return this.boxs.size();
	}
	
	@Override
	public boolean ifSolved() {
		if (this.getCountBoxOnTargets()==this.getCountTargets())
			return true;
		return false;
	}



}
