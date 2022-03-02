package objects;

public class Object {
	
	private ObjectID ID;
	
	private float x, y;
	
	public Object(ObjectID ID, float x, float y) {
		this.x = x;
		this.y = y;
		this.ID = ID;
	}

	public ObjectID getID() {
		return ID;
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

}
