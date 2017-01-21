package model.data;

import java.util.HashMap;

import commons.Box;
import commons.Path;
import commons.Player;
import commons.Target;
import commons.TwoPoint;
import commons.Wall;
import commons.type;

public class TypeFactory {
	
	private HashMap<Character, CreatorType> typeCreator;
	
	public TypeFactory() {
		typeCreator = new HashMap<Character, CreatorType>();
		typeCreator.put(' ',new PathCreator());
		typeCreator.put('#',new WallCreator());
		typeCreator.put('o',new TargetCreator());
		typeCreator.put('O',new TargetCreator());
		BoxCreator bc=new BoxCreator();
		typeCreator.put('@',bc);
		typeCreator.put('$',bc); //box on target
		PlayerCreator pc= new PlayerCreator();
		typeCreator.put('A',pc);
		typeCreator.put('a',pc); //player on target
	}
	private class PathCreator implements CreatorType{
		@Override
		public type createType(TwoPoint pos) {
			return new Path(pos);
		}
	
	}
	private class WallCreator implements CreatorType{
		@Override
		public type createType(TwoPoint pos) {
			return new Wall(pos);
		}
	
	}
	private class TargetCreator implements CreatorType{
		@Override
		public type createType(TwoPoint pos) {
			return new Target(pos);
		}
	
	}
	private class BoxCreator implements CreatorType{
		@Override
		public type createType(TwoPoint pos) {
			return new Box(pos);
		}	
	}
	private class PlayerCreator implements CreatorType{
		@Override
		public type createType(TwoPoint pos) {
			return new Player(pos);
		}	
	}
	
	
	public type creatType(Character c,TwoPoint pos) {
		CreatorType ct= typeCreator.get(c);
		TwoPoint tp = new TwoPoint(pos.getX(),pos.getY());
		if(ct!= null)return ct.createType(tp);
		return null;
	}

}
