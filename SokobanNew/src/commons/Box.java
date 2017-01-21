package commons;

public class Box extends TypeA implements moveAble {
	
	//def C'tor
	public Box() {
		super('@',null);			
	}
	//C'tor
	public Box(position pos) {
		super('@',pos);			
	}	
	
}
