package model.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import commons.Box;
import commons.Level2D;
import commons.Path;
import commons.Player;
import commons.Target;
import commons.TwoPoint;
import commons.background;
import commons.moveAble;
import commons.type;

public class MyTextLevelLoader implements levelLoader {

	@Override
	public Level2D loadLevel(InputStream in) throws IOException {
		Level2D l2d = new Level2D();
		String line;
		int sizeR=0;
		int sizeC=0;
		//TwoPoint tp = new TwoPoint();
		ArrayList<String> arrLine = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		
		
			TypeFactory typeF = new TypeFactory();
			while((line = reader.readLine())!=null)
			{
				arrLine.add(line);
				sizeR++;
				if(line.length()>sizeC)
					sizeC=line.length();
			}
			creat2Matrix(l2d,arrLine,sizeR,sizeC);
			
			for(int i=0;i<arrLine.size();i++)
			{
				for(int j=0;j<arrLine.get(i).length();j++){
					TwoPoint tp = new TwoPoint();
					tp.setX(i);
					tp.setY(j);
					type t= typeF.creatType(arrLine.get(i).charAt(j),tp);
					if(t==null){
						l2d.getMoveables()[i][j] = null;
						l2d.getBackG()[i][j] = new Path(tp);
					}
					else if(t instanceof background){
						l2d.getBackG()[i][j] = (background)t;
						l2d.getMoveables()[i][j] = null;
						if(Target.class.isAssignableFrom(l2d.getBackG()[i][j].getClass())){
							l2d.geTargets().add((Target)l2d.getBackG()[i][j]);
						}
					}
					else if(t instanceof moveAble){
						l2d.getMoveables()[i][j] =  (moveAble)t;
						l2d.getBackG()[i][j] = new Path(tp);
						
						if(Box.class.isAssignableFrom(l2d.getMoveables()[i][j].getClass())){
							l2d.getBoxs().add((Box)l2d.getMoveables()[i][j]);
						}
						else if(Player.class.isAssignableFrom(l2d.getMoveables()[i][j].getClass())){
							l2d.setPlayer((Player)l2d.getMoveables()[i][j]);
						}
						//check if player on target(a)/box on target($)
						if (arrLine.get(i).charAt(j)=='a'||(arrLine.get(i).charAt(j)=='$')){
							l2d.getBackG()[i][j] = new Target(tp);
							l2d.geTargets().add((Target)l2d.getBackG()[i][j]);
						}
					}
				}
			}
			
		System.out.println("file txt loaded");
		return l2d;
	}
	
	private void creat2Matrix(Level2D l2d, ArrayList<String> arrLine,int sizeR,int sizeC) {
		l2d.setSizeCol(sizeC);
		l2d.setSizeRow(sizeR);
		background backG [][] = new background[sizeR][sizeC];
		l2d.setBackG(backG);
		moveAble moveables [][] = new moveAble[sizeR][sizeC];
		l2d.setMoveables(moveables);
		
	}

}
