package model.policy;

import commons.Box;
import commons.Level2D;
import commons.Player;
import commons.TwoPoint;
import commons.Wall;
import commons.background;
import commons.type;

public class MySokobanPolicy implements policy {

	public boolean canMove(Level2D Thelevel, type t, TwoPoint points) {
		if (t instanceof background || t==null)
			return false;
		
		//Box move
		if (Box.class.isAssignableFrom(t.getClass())){
			
			//box try move a wall
			if (Thelevel.getMoveables()[points.getX()][points.getY()]== null)
			{
				if (Wall.class.isAssignableFrom(Thelevel.getBackG()[points.getX()][points.getY()].getClass()))
						return false;
				//box try move a path
				else
					return true;
			}
			
			//box try move a box
			else if (Box.class.isAssignableFrom(Thelevel.getMoveables()[points.getX()][points.getY()].getClass()))
				return false;

		}
		
		//Player Move
		if (Player.class.isAssignableFrom(t.getClass())){
			
			 if (Thelevel.getMoveables()[points.getX()][points.getY()]== null)
				{
					//player try move a Wall
					if (Wall.class.isAssignableFrom(Thelevel.getBackG()[points.getX()][points.getY()].getClass()))
							return false;
					else 
						return true;
				}
			//player try move a box
			 else if (Box.class.isAssignableFrom(Thelevel.getMoveables()[points.getX()][points.getY()].getClass())){
				TwoPoint newpoints = new TwoPoint();
				newpoints.setX(2*points.getX()- ((TwoPoint)t.getPos()).getX());
				newpoints.setY(2*points.getY()- ((TwoPoint)t.getPos()).getY());
				return canMove(Thelevel,Thelevel.getMoveables()[points.getX()][points.getY()] , newpoints);
				}
			
		}
		return false;
	}



}
