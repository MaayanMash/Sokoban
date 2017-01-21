package model.policy.move;

import commons.Level2D;
import commons.TwoPoint;
import model.policy.policy;

public class MoveUp extends MovePlayerA{

	public MoveUp(policy ThePolicy) {
		super(ThePolicy);
	}
	
	@Override
	public Level2D movePlayer(Level2D TheLevel) {
		TwoPoint newPoints= new TwoPoint();
		TwoPoint playerPoints = (TwoPoint)TheLevel.getPlayer().getPos();
		newPoints.setX(playerPoints.getX()-1);
		newPoints.setY(playerPoints.getY());
		
		return this.moveEveryWhere(TheLevel, newPoints, playerPoints, ThePolicy);

	}

}
