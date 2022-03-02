package rendering;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import camera.Camera;
import debug.Debug;
import debug.Log;
import debug.LogLevel;
import objects.Hitbox;
import objects.TimelineCategory;
import objects.TimelineObject;

public class ObjectRenderer {
	
	@SuppressWarnings("unused")
	private Log log = new Log(this.getClass().getSimpleName(), LogLevel.ERROR);
	
	private int width, height;
	
	public ObjectRenderer(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public void renderBackground(Graphics g) {
		Image image = ImageLoader.loadImage(TimelineCategory.LITERATURE.getImagePath());
		if(image != null) {
			g.drawImage(image, 0, 0, width, height, null);
			return;
		}
		g.setColor(Debug.BACKGROUND_COLOR);
		g.fillRect(0, 0, width, height);
	}
	
	public void renderTimeline(Graphics g, Camera camera) {
		int timelineHeight = Debug.TIMELINE_HEIGHT + camera.getY();
		if(timelineHeight > 0) {
			g.setColor(Color.BLACK);
			g.drawLine(0, timelineHeight, width, timelineHeight);

			for(int date: Debug.TIMELINE_YEAR_MARKERS) {
				int xLocation = (int) (date * Debug.PIXELS_PER_YEAR) + camera.getX();
				if(xLocation > 0 - Debug.TIMELINE_DATE_BORDER && xLocation < width + Debug.TIMELINE_DATE_BORDER) {
					g.drawLine(xLocation, timelineHeight - 5, xLocation, timelineHeight + 5);
					String dateName = Integer.toString(Math.abs(date)) + (date < 0 ? " BC": " AD");
					g.drawString(dateName, xLocation - g.getFontMetrics().stringWidth(dateName) / 2, timelineHeight - 10);
				}
			}
		}
	}
	
	public void renderTimelineObjectWithoutLine(Graphics g, Camera camera, TimelineObject item) {
		int timelineCentre = (int) (item.getDate() * Debug.PIXELS_PER_YEAR) + camera.getX();
		if(timelineCentre > width + Debug.SCREEN_MARGIN || timelineCentre < -Debug.SCREEN_MARGIN) {
			return;
		}
		
		drawTimelineBackground(g, camera, item);
		drawTimelineImage(g, camera, item);
		drawTimelineHeading(g, camera, item);
		
		if(item.isOpened()) {
			drawTimelineDate(g, camera, item);
			drawTimelineCategory(g, camera, item);
			
			if(item.getText() != null) {
				drawTimelineText(g, camera, item);
			}
		}
	}
	
	public void renderTimelineLine(Graphics g, Camera camera, TimelineObject item) {
		int timelineCentre = (int) (item.getDate() * Debug.PIXELS_PER_YEAR) + camera.getX();
		if(timelineCentre > width + Debug.SCREEN_MARGIN || timelineCentre < -Debug.SCREEN_MARGIN) {
			return;
		}
		drawTimelineLine(g, camera, item, timelineCentre);
	}
	
	public void renderTimelineObject(Graphics g, Camera camera, TimelineObject item) {
		int timelineCentre = (int) (item.getDate() * Debug.PIXELS_PER_YEAR) + camera.getX();
		if(timelineCentre > width + Debug.SCREEN_MARGIN || timelineCentre < -Debug.SCREEN_MARGIN) {
			return;
		}
		
		drawTimelineBackground(g, camera, item);
		drawTimelineLine(g, camera, item, timelineCentre);
		drawTimelineImage(g, camera, item);
		drawTimelineHeading(g, camera, item);
		
		if(item.isOpened()) {
			drawTimelineDate(g, camera, item);
			drawTimelineCategory(g, camera, item);
			
			if(item.getText() != null) {
				drawTimelineText(g, camera, item);
			}
		}
	}
	
	private void drawTimelineLine(Graphics g, Camera camera, TimelineObject item, int TimelineCentre) {
		g.setColor(Debug.TIMELINE_LINE_COLOR);
		g.drawLine(TimelineCentre, item.getHitbox().getY() + camera.getY(), TimelineCentre, Debug.TIMELINE_HEIGHT + camera.getY());
	}
	
	private void drawTimelineBackground(Graphics g, Camera camera, TimelineObject item) {
		Hitbox hitbox = item.getHitbox();
		g.setColor(Debug.TIMELINE_BOX_COLOR);
		g.fillRect(hitbox.getX() + camera.getX(), hitbox.getY() + camera.getY(), hitbox.getWidth(), hitbox.getHeight());
		g.setColor(Color.BLACK);
		g.drawRect(hitbox.getX() + camera.getX(), hitbox.getY() + camera.getY(), hitbox.getWidth(), hitbox.getHeight());
	}
	
	private void drawTimelineImage(Graphics g, Camera camera, TimelineObject item) {
		Image image = item.getImage();
		if(image != null) {
			g.drawImage(image, item.getImageX() + camera.getX(), item.getImageY() + camera.getY(), Debug.IMAGE_WIDTH, Debug.IMAGE_HEIGHT, Color.BLUE, null);
		}
	}

	private void drawTimelineHeading(Graphics g, Camera camera, TimelineObject item) {
		g.setColor(Debug.TIMELINE_HEADING_COLOR);
		Font currentFont = g.getFont();
		g.setFont(currentFont.deriveFont(currentFont.getSize() * Debug.TIMELINE_HEADING_FONT_SIZE));
		g.drawString(item.getHeading(), item.getHeadingX() + camera.getX() - (g.getFontMetrics().stringWidth(item.getHeading()) / 2), item.getHeadingY() + camera.getY());
		g.setFont(currentFont);
	}
	
	private void drawTimelineDate(Graphics g, Camera camera, TimelineObject object) {
		g.setColor(Debug.TIMELINE_DATE_COLOR);
		Font currentFont = g.getFont();
		g.setFont(currentFont.deriveFont(currentFont.getSize() * Debug.TIMELINE_DATE_FONT_SIZE));
		g.drawString("Date: " + Math.abs(object.getDate()) + (object.getDate() < 0 ? " BC" : " AD"), object.getDateX() + camera.getX(), object.getDateY() + camera.getY());
		g.setFont(currentFont);
	}
	
	private void drawTimelineCategory(Graphics g, Camera camera, TimelineObject object) {
		g.setColor(Debug.TIMELINE_DATE_COLOR);
		Font currentFont = g.getFont();
		g.setFont(currentFont.deriveFont(currentFont.getSize() * Debug.TIMELINE_DATE_FONT_SIZE));
		g.drawString("Category: " + object.getCategory().getCategoryName(), object.getCategoryX() + camera.getX(), object.getCategoryY() + camera.getY());
		g.setFont(currentFont);
	}
	
	private void drawTimelineText(Graphics g, Camera camera, TimelineObject item) {
		g.setColor(Debug.TIMELINE_TEXT_COLOR);
		Font currentFont = g.getFont();
		g.setFont(currentFont.deriveFont(currentFont.getSize() * Debug.TIMELINE_DATE_FONT_SIZE));
		for(int i = 0; i < item.getTextInLines().size(); i++) {
			String text = item.getTextInLines().get(i);
			g.drawString(text, item.getTextX() + camera.getX(), item.getTextY() + camera.getY() + Debug.TIMELINE_TEXT_Y_SIZE * i);
		}
		g.setFont(currentFont);
	}
	
}
