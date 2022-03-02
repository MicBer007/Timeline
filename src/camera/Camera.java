package camera;

import debug.Debug;

public class Camera {
	
	private int x, y;
	
	public Camera() {
		x = -Debug.CAMERA_START_X;
		y = Debug.CAMERA_START_Y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		if(x < Debug.X_MAX) {
			this.x = Debug.X_MAX;
		}
		if(x > Debug.X_MIN) {
			this.x = Debug.X_MIN;
		}
	}

	public void changeX(int x) {
		this.x += x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		if(y > Debug.Y_MAX) {
			this.y = Debug.Y_MAX;
		}
	}

	public void changeY(int y) {
		this.y += y;
	}

}
