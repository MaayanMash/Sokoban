package model.policy.move;

import commons.Level2D;
import commons.TwoPoint;
import model.policy.policy;

public class MoveLeft extends MovePlayerA {

	public MoveLeft(policy ThePolicy) {
		super(ThePolicy);
	}
	
	@Override
	public Level2D movePlayer(Level2D TheLevel) {
		TwoPoint newPoints= new TwoPoint();
		TwoPoint playerPoints = (TwoPoint)TheLevel.getPlayer().getPos();
		newPoints.setX(playerPoints.getX());
		newPoints.setY(playerPoints.getY()-1);
		
		return this.moveEveryWhere(TheLevel, newPoints, playerPoints, ThePolicy);
	}

}
