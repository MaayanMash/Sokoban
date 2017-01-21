package model.policy.move;

import commons.Level2D;
import commons.TwoPoint;
import model.policy.policy;

public interface iMovePlayer {
	public Level2D movePlayer (Level2D TheLevel);
	public Level2D moveEveryWhere(Level2D TheLevel, TwoPoint newPointsPlayer, TwoPoint playerPoints, policy pol);
}
