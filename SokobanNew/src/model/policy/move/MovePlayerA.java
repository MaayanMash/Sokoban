package model.policy.move;

import commons.Level2D;
import commons.TwoPoint;
import model.policy.policy;

public abstract class MovePlayerA implements iMovePlayer {
	
	protected policy ThePolicy;
	
	public MovePlayerA(policy ThePolicy) {
		this.ThePolicy=ThePolicy;
	}
	//count how many box we need to move
	private int CountManyMoveable (Level2D theLevel, TwoPoint newPointPlayer){
		int count=0;
		TwoPoint PlayerPoints=(TwoPoint) theLevel.getPlayer().getPos();
		TwoPoint nextPoint=new TwoPoint();
		nextPoint.setX(newPointPlayer.getX());
		nextPoint.setY(newPointPlayer.getY());
		TwoPoint diff=new TwoPoint();
		diff.setX(newPointPlayer.getX()-PlayerPoints.getX());
		diff.setY(newPointPlayer.getY()-PlayerPoints.getY());
		while (theLevel.getMoveables()[nextPoint.getX()][nextPoint.getY()]!=null)
		{
			count++;
			nextPoint.setX(nextPoint.getX()+diff.getX());
			nextPoint.setY(nextPoint.getY()+diff.getY());
		}
		return count;
	}
	
	//if the player moved- return the level, if not- return null
	public Level2D moveEveryWhere(Level2D TheLevel, TwoPoint newPointsPlayer, TwoPoint playerPoints, policy pol) {
		int count=0;
		//check if the player can move 
		if (pol.canMove(TheLevel, TheLevel.getPlayer(),newPointsPlayer))
		{
			TheLevel.addStep();
			count=this.CountManyMoveable(TheLevel, newPointsPlayer);
			TwoPoint diff=new TwoPoint();
			diff.setX(newPointsPlayer.getX()-playerPoints.getX());
			diff.setY(newPointsPlayer.getY()-playerPoints.getY());
			
			TwoPoint LastMove=new TwoPoint();
			LastMove.setX(playerPoints.getX()+diff.getX()*count);
			LastMove.setY(playerPoints.getY()+diff.getY()*count);
			
			for (int i=0; i<=count; i++)
			{
				TwoPoint newPoint=new TwoPoint(LastMove.getX()+diff.getX(), LastMove.getY()+diff.getY());
				//move a moveable to new position
				TheLevel.getMoveables()[LastMove.getX()][LastMove.getY()].setPos(newPoint);
				TheLevel.getMoveables()[newPoint.getX()][newPoint.getY()]=TheLevel.getMoveables()[LastMove.getX()][LastMove.getY()];
				TheLevel.getMoveables()[LastMove.getX()][LastMove.getY()]=null;
				
				LastMove.setX(LastMove.getX()-diff.getX());
				LastMove.setY(LastMove.getY()-diff.getY());
			}
			
		}
		else
			return null;
		
		//check if the level solved
		/*if (TheLevel.ifSolved())
		{
			System.out.println("The Level Solved!!");
			System.out.println("Num of Steps: " + TheLevel.getCountSteps());
		}*/
		
		return TheLevel;
	}
}
