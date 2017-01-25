package commons;

import java.io.Serializable;

public class StatusLevel implements status, Serializable{
	private int countSteps;
	private double grade; //need to think if we need and how calc
	
	
	//defilt C'tor
	public StatusLevel() {
		this.countSteps =0;
		this.grade=0;
	}	
	//C'tor
	public StatusLevel(int countSteps,double grade, int time) {
		this.setCountSteps(countSteps);
		this.setGrade(grade);
	}
	//copy c'tor
	public StatusLevel(StatusLevel status){
		this.countSteps=status.countSteps;
		this.grade=status.grade;
	}
	
	@Override
	public status getStatus() {
		return this;
	}
	@Override
	public void addStep() {
		this.countSteps+=1;		
	}
	//set's && get's
	@Override
	public int getCountSteps() {
		return this.countSteps;
	}
	@Override
	public double getGrade() {
		return this.grade;
	}
	public void setCountSteps(int countSteps) {
		if(countSteps>=0)
			this.countSteps = countSteps;
		else 
			this.countSteps=0;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	



	
	
}
