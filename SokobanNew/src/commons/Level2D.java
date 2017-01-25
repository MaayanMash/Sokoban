package commons;


import java.time.Clock;
import java.util.ArrayList;
import javax.swing.SingleSelectionModel;
import commons.Target;


public class Level2D extends LevelA {
	private background[][] backG;
	private moveAble[][] moveables;
	private int sizeRow;
	private int sizeCol;	
	
	//De'f C'tor
	public Level2D() {
		super();
		this.backG = null;
		this.moveables=null;
		this.sizeRow=-1;
		this.sizeCol=-1;
	}
	//C'tor
	public Level2D(LevelA level,int sizeRow,int sizeCol,background[][] backG,moveAble[][] moveables) {
		super(level);
		this.setSizeRow(sizeRow);
		this.setSizeCol(sizeCol);
		this.backG = backG;
		this.moveables = moveables;
	}
	
	public Level2D(Level2D level)
	{
		super(level);
		this.sizeRow= level.getSizeRow();
		this.sizeCol=level.getSizeCol();
		this.backG=level.getBackG();
		this.moveables=level.getMoveables();
	}
		
	//gets && sets
	public int getSizeRow() {
		return sizeRow;
	}
	public void setSizeRow(int sizeRow) {
		this.sizeRow = sizeRow;
	}
	public int getSizeCol() {
		return sizeCol;
	}
	public void setSizeCol(int sizeCol) {
		this.sizeCol = sizeCol;
	}
	public background[][] getBackG() {
		return backG;
	}
	public void setBackG(background[][] backG) {
		this.backG = backG;
	}
	public moveAble[][] getMoveables() {
		return moveables;
	}
	public void setMoveables(moveAble[][] moveables) {
		this.moveables = moveables;
	}
	
	@Override
	public int getCountBoxOnTargets() {
		int count=0;
		for (Box box : this.getBoxs()) {
			for (Target target : this.geTargets()) {
				if(target.getPos().equalsPos(box.getPos()))
						count++;
			}
		}
		return count;
	}
	
	@Override
	public int getCountBoxNotOnTargets() {
		return this.getCountBoxs()-this.getCountBoxOnTargets(); 
	}

	public position getPosCharacter() {
		return this.player.getPos();
	}
	
	@Override
	public String toString() {
		String str="";
		for (int i=0;i<this.sizeRow;i++){
			for(int j=0;j<this.sizeCol;j++){
				if(this.moveables[i][j] ==null)
					str+=this.backG[i][j].toString();
				else
				{
					if (Target.class.isAssignableFrom(this.backG[i][j].getClass()))
					{
						if (Box.class.isAssignableFrom(this.getMoveables()[i][j].getClass()))
							str+='$';
						else if (Player.class.isAssignableFrom(this.getMoveables()[i][j].getClass()))
							str+='a';
					}
					else
						str+=this.moveables[i][j].toString();
				}
			}
			str+=System.lineSeparator();
		}
		return str;
	}

	public char[][] getBoared(){
		char[][] boared= new char[sizeRow][sizeCol];

		for (int i=0;i<this.sizeRow;i++){
			for(int j=0;j<this.sizeCol;j++){
				if(this.moveables[i][j] ==null)
					boared[i][j]=this.backG[i][j].getC();
				else
				{
					if (Target.class.isAssignableFrom(this.backG[i][j].getClass()))
					{
						if (Box.class.isAssignableFrom(this.getMoveables()[i][j].getClass()))
							boared[i][j]='$';
						else if (Player.class.isAssignableFrom(this.getMoveables()[i][j].getClass()))
							boared[i][j]='a';
					}
					else
						boared[i][j]=this.moveables[i][j].getC();
				}
			}
		}

		return boared;
		
	}
}

