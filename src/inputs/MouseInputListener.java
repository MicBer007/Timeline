package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import camera.Camera;
import debug.Log;
import debug.LogLevel;
import timeline.Timeline;

public class MouseInputListener implements MouseListener, MouseMotionListener {
	
	private Log log = new Log(this.getClass().getSimpleName(), LogLevel.ERROR);
	
	private int PressedX, PressedY;
	
	private int mousePositionX, mousePositionY;
	
	private int mousePositionRelativeX, mousePositionRelativeY;
	
	private Timeline timeline;
	
	public MouseInputListener(Timeline timeline) {
		this.timeline = timeline;
	}
	
	
	public int getMousePositionX() {
		return mousePositionX;
	}

	public int getMousePositionY() {
		return mousePositionY;
	}

	public int getMousePositionRelativeX() {
		return mousePositionRelativeX;
	}

	public int getMousePositionRelativeY() {
		return mousePositionRelativeY;
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		log.info("mouse clicked!", 0);
		timeline.getObjectManager().mouseClickEvent(this);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		log.info("mouse pressed!", 0);
		PressedX = e.getX() - timeline.getObjectManager().getCamera().getX();
		PressedY = e.getY() - timeline.getObjectManager().getCamera().getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		log.info("mouse released!", 0);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		log.info("mouse entered!", 0);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		log.info("mouse exited!", 0);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		log.info("mouse dragged!", 0);
		calculateMousePosition(e);
		
		Camera camera = timeline.getObjectManager().getCamera();
		int xDiff = e.getX() - PressedX;
		int yDiff = e.getY() - PressedY;
		camera.setX(xDiff);
		camera.setY(yDiff);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		log.info("mouse moved!", 0);
		calculateMousePosition(e);
	}
	
	public void calculateMousePosition(MouseEvent e) {
		mousePositionX = e.getX();
		mousePositionY = e.getY();
		
		Camera camera = timeline.getObjectManager().getCamera();
		mousePositionRelativeX = e.getX() - camera.getX();
		mousePositionRelativeY = e.getY() - camera.getY();
		
		log.info("Relative mouse position, X: " + mousePositionRelativeX + ", Y: " + 
				mousePositionRelativeY + ", Mouse position, X: " + mousePositionX + ", Y:" + mousePositionY, 1);
	}

}
