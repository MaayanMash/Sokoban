package model.policy;

import commons.Level2D;
import commons.TwoPoint;
import commons.type;

public interface policy {
	public boolean canMove(Level2D Thelevel, type t, TwoPoint points);


}
