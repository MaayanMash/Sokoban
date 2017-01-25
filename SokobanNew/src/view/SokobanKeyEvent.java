package view;

import javafx.scene.input.KeyCode;

public class SokobanKeyEvent {
	private KeyCode up;
	private KeyCode down;
	private KeyCode left;
	private KeyCode right;
	
	public SokobanKeyEvent() {
		this.up=KeyCode.UP;
		this.down=KeyCode.DOWN;
		this.left=KeyCode.LEFT;
		this.right=KeyCode.RIGHT;
	}

	public KeyCode getUp() {
		return up;
	}

	public void setUp(KeyCode up) {
		this.up = up;
	}

	public KeyCode getDown() {
		return down;
	}

	public void setDown(KeyCode down) {
		this.down = down;
	}

	public KeyCode getLeft() {
		return left;
	}

	public void setLeft(KeyCode left) {
		this.left = left;
	}

	public KeyCode getRight() {
		return right;
	}

	public void setRight(KeyCode right) {
		this.right = right;
	}
	


}
