package objects;

public class Hitbox {
	
	private float x, y;
	
	private int width, height;
	
	private float x2, y2;
	
	public Hitbox(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.x2 = x + width;
		this.y2 = y + height;
	}
	
	
	public void shiftHitbox(int x, int y) {
		this.x += x;
		this.y += y;
		this.x2 += x;
		this.y2 += y;
	}
	
	public boolean intersectsWith(Hitbox hitbox) {
		if(hitbox.getX() < x2 && hitbox.getX2() > x) {
			if(hitbox.getY() < y2 && hitbox.getY2() > y) {
				return true;
			}
		}
		return false;
	}

	
	public int getX() {
		return (int) x;
	}
	
	public float getPreciseX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public int getY() {
		return (int) y;
	}
	
	public float getPreciseY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
		this.x2 += width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
		this.y2 += height;
	}

	public int getX2() {
		return (int) x2;
	}

	public float getPreciseX2() {
		return x2;
	}

	public void setX2(float x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return (int) y2;
	}

	public float getPreciseY2() {
		return y2;
	}

	public void setY2(float y2) {
		this.y2 = y2;
	}

}
