package attempt.me;

import android.app.Activity;

public class Choords extends Activity {

	private int leftX;
	private int rightX;
	private int leftY;
	private int rightY;

	public Choords(int leftX, int rightX, int leftY, int rightY) {
		super();
		this.leftX = leftX;
		this.rightX = rightX;
		this.leftY = leftY;
		this.rightY = rightY;

	}
	
	public int getLeftX() {
		return leftX;
	}
	public void setLeftX(int leftX) {
		this.leftX = leftX;
	}
	public int getRightX() {
		return rightX;
	}
	public void setRightX(int rightX) {
		this.rightX = rightX;
	}
	public int getLeftY() {
		return leftY;
	}
	public void setLeftY(int leftY) {
		this.leftY = leftY;
	}
	public int getRightY() {
		return rightY;
	}
	public void setRightY(int rightY) {
		this.rightY = rightY;
	}
	
}
