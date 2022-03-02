package objects;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import camera.Camera;
import debug.Debug;
import debug.Log;
import debug.LogLevel;
import inputs.MouseInputListener;
import rendering.ObjectRenderer;

public class ObjectManager {
	
	private Log log = new Log(this.getClass().getSimpleName(), LogLevel.ERROR);
	
	private ObjectRenderer renderer;
	
	private Camera camera;
	
	private List<TimelineObject> timelineItems = new ArrayList<TimelineObject>();
	
	private List<TimelineObject> itemsToRender = new ArrayList<TimelineObject>();
	
	private TimelineObject openedTimelineObject;
	
	private TimelineCategory category;
	
	public ObjectManager(int width, int height, TimelineCategory category) {
		renderer = new ObjectRenderer(width, height);
		this.category = category;
		camera = new Camera();
	}
	
	public Camera getCamera() {
		return camera;
	}
	
	//object management
	public void addTimelineItem(TimelineObject item) {
		item.setID(generateUniqueObjectID());
		timelineItems.add(item);
	}
	
	public void removeTimelineItem(ObjectID ID) {
		for(TimelineObject item: timelineItems) {
			if(item.getID() == ID) {
				timelineItems.remove(item);
			}
		}
	}
	
	public ObjectID generateUniqueObjectID() {
		return new ObjectID(timelineItems.size() + 1);
	}
	
	public void sortObjects() {
		itemsToRender.clear();
		if(category == null) {
			itemsToRender = timelineItems;
		} else {
			for(TimelineObject object: timelineItems) {
				if(object.getCategory() == category) {
					itemsToRender.add(object);
				}
			}
		}
		
		//sort
		itemsToRender.sort((TimelineObject a, TimelineObject b) -> Integer.compare(a.getDate(), b.getDate()));
		
		determineObjectYValues();
	}
	
	public void determineObjectYValues() {
		if(itemsToRender.size() == 0) {
			return;
		}
		for(int i = 0; i < itemsToRender.size(); i++) {
			itemsToRender.get(i).setYOffset(0);
			TimelineObject object = itemsToRender.get(i);
			if(i > 0) {
				TimelineObject objectBehind = itemsToRender.get(i - 1);
				if(object.getDate() - objectBehind.getDate() < Debug.getClosedHitbox().getWidth()) {
					object.setYOffset(objectBehind.getYOffset() + 200);
				}
			}
		}
	}
	
	public TimelineCategory getCategory() {
		return category;
	}

	public void setCategory(TimelineCategory category) {
		this.category = category;
	}

	
	//hitbox calculations
	public boolean isInObjectHitbox(TimelineObject timelineItem, int x, int y) {
		Hitbox hitbox = timelineItem.getHitbox();
		if(hitbox.getX() < x && hitbox.getX2() > x) {
			if(hitbox.getY() < y && hitbox.getY2() > y) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isMouseOnObject(TimelineObject timelineItem, MouseInputListener listener) {
		return isInObjectHitbox(timelineItem, listener.getMousePositionRelativeX(), listener.getMousePositionRelativeY());
	}
	
	
	//passed events
	public void mouseClickEvent(MouseInputListener listener) {
		boolean objectClicked = false;
		for(TimelineObject timelineItem: timelineItems) {
			if(isMouseOnObject(timelineItem, listener)) {
				log.info("Timeline object with ID: " + timelineItem.getID().getID() + " has been clicked!", 10);
				timelineItem.setOpened(!timelineItem.isOpened());
				openedTimelineObject = timelineItem;
				objectClicked = true;
			} else {
				if(timelineItem.isOpened()) {
					timelineItem.setOpened(false);
				}
			}
		}
		if(!objectClicked) {
			openedTimelineObject  = null;
		}
	}
	
	
	//Rendering
	public void renderTimeline(Graphics g) {
		for(TimelineObject object: itemsToRender) {
			renderer.renderTimelineLine(g, camera, object);
		}
		for(TimelineObject object: itemsToRender) {
			renderer.renderTimelineObjectWithoutLine(g, camera, object);
		}
		if(openedTimelineObject != null) {
			renderer.renderTimelineObjectWithoutLine(g, camera, openedTimelineObject);
		}
	}
	
	public void renderTimelineObject(Graphics g, TimelineObject object) {
		renderer.renderTimelineObject(g, camera, object);
	}
	
	public void renderBackground(Graphics g) {
		renderer.renderBackground(g);
	}
	
	public void renderTimelineLine(Graphics g) {
		renderer.renderTimeline(g, camera);
	}

}
