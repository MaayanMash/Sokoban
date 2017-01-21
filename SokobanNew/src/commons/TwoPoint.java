package commons;


public class TwoPoint  implements position{
	private int x;
	private int y;
	
	
	//def C'tor
	public TwoPoint() {
		x=-1;
		x=-1;
		}
	//C'tor
	public TwoPoint(int x,int y) {
		this.x=x;
		this.y=y;
		}
	
	
	//@Override
	public position getPosition() {
		return this;
	}

	//get's && set's
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	
	public boolean equalsPos(position pos) {
		TwoPoint tpos=(TwoPoint)pos;
		if ((this.x==tpos.x)&&(this.y==tpos.y))
			return true;
		return false;
	}


}
