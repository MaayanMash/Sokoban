package commons;

import java.io.Serializable;

public abstract class TypeA  implements type, Serializable {
	private char c;
	private position pos;
	
	//Def C'tor
	public TypeA() {
		this.c =' ';
		this.pos = null;		
	}
	//C'tor
	public TypeA(char c,position pos) {
		this.c =c;
		this.pos = pos;		
	}
	
	@Override
	public String getType() {
		return (this.getClass().toString()+6);
	}	
	//gets && sets
	public char getC() {
		return c;
	}
	public void setC(char c) {
		this.c = c;
	}
	public position getPos() {
		return pos;
	}
	public void setPos(position pos) {
		this.pos = pos;
	}
	@Override
	public String toString() {
		Character c1 =c;
		return c1.toString();
	}
}
