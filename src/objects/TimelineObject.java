package objects;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import debug.Debug;
import rendering.ImageLoader;
import timeline.Timeline;

public class TimelineObject {
	
	private boolean isOpened = false;
	private boolean canOpen = false;
	
	private Hitbox hitbox;
	
	private int yOffset = 0;
	
	private ObjectID ID;
	
	private String heading;
	private int headingX, headingY;

	private String text;
	private List<String> textLines = new ArrayList<String>();
	private int textX, textY;
	
	private int date;
	private int dateX, dateY;
	
	private TimelineCategory category;
	private int categoryX, categoryY;
	
	private Image image;
	private int imageX, imageY;

	public TimelineObject(String heading, int date, TimelineCategory category) {
		this.heading = heading;
		this.date = date;
		this.category = category;
		setDimensions();
	}

	public TimelineObject(String heading, int date, TimelineCategory category, String imagePath) {
		this.heading = heading;
		this.date = date;
		this.category = category;
		this.image = ImageLoader.loadImage(imagePath);
		setDimensions();
	}

	public TimelineObject(String heading, int date, TimelineCategory category, String imagePath, String text, Timeline timeline) {
		this.heading = heading;
		this.date = date;
		this.category = category;
		this.text = text;
		this.canOpen = true;
		this.image = ImageLoader.loadImage(imagePath);
		setDimensions(timeline);
	}

	public void setDimensions() {
		this.hitbox = Debug.getClosedHitbox();
		hitbox.shiftHitbox((int) (date * Debug.PIXELS_PER_YEAR), yOffset);
		
		headingX = hitbox.getX() + hitbox.getWidth() / 2;
		headingY = hitbox.getY() + 24;
		
		dateX = hitbox.getX() + hitbox.getWidth();
		dateY = hitbox.getY() + 40;
		
		categoryX = hitbox.getX() + hitbox.getWidth();
		categoryY = hitbox.getY() + 80;
		
		if(image != null) {
			imageX = hitbox.getX() + (hitbox.getWidth() - Debug.IMAGE_WIDTH) / 2;
			imageY = hitbox.getY() + 38;
		}
	}

	public void setDimensions(Timeline timeline) {
		this.hitbox = Debug.getClosedHitbox();
		hitbox.shiftHitbox((int) (date * Debug.PIXELS_PER_YEAR), yOffset);
		
		headingX = hitbox.getX() + hitbox.getWidth() / 2;
		headingY = hitbox.getY() + 24;
		
		dateX = hitbox.getX() + hitbox.getWidth() + 0;
		dateY = hitbox.getY() + 50;

		categoryX = hitbox.getX() + hitbox.getWidth();
		categoryY = hitbox.getY() + 80;
		
		imageX = hitbox.getX() + (hitbox.getWidth() - Debug.IMAGE_WIDTH) / 2;
		imageY = hitbox.getY() + 38;

		textX = hitbox.getX() + Debug.TIMELINE_TEXT_BORDER_X;
		textY = hitbox.getY() + hitbox.getWidth() + Debug.TIMELINE_TEXT_BORDER_Y;
		calculateTextPositioning(timeline);
	}
	
	private void calculateTextPositioning(Timeline timeline) {
		Font textFont = new Font("Dialog", Font.PLAIN, (int) (12 * Debug.TIMELINE_TEXT_FONT_SIZE));
		FontMetrics metrics = timeline.getFontMetrics(textFont);
		int lineSpace = Debug.getOpenHitbox().getWidth() - (int) (Debug.TIMELINE_TEXT_BORDER_X * 2.9);
		String[] words = text.split(" ");
		String line = "";
		int spaceTaken = 0;
		for(String word: words) {
			int wordSpace = metrics.stringWidth(word + " ");
			spaceTaken += wordSpace;
			if(spaceTaken > lineSpace) {
				textLines.add(line);
				line = "";
				spaceTaken = wordSpace;
			}
			line += word + " ";
		}
		textLines.add(line);
	}


	public ObjectID getID() {
		return ID;
	}
	
	public void setID(ObjectID ID) {
		this.ID = ID;
	}

	public boolean isOpened() {
		return isOpened;
	}

	public void setOpened(boolean isOpened) {
		if(text != null && canOpen) {
			this.isOpened = isOpened;
			if(isOpened) {
				hitbox = Debug.getOpenHitbox();
				hitbox.shiftHitbox((int) (date * Debug.PIXELS_PER_YEAR), yOffset);
			} else {
				hitbox = Debug.getClosedHitbox();
				hitbox.shiftHitbox((int) (date * Debug.PIXELS_PER_YEAR), yOffset);
			}
		}
	}

	public Hitbox getHitbox() {
		return hitbox;
	}

	public int getYOffset() {
		return yOffset;
	}

	public void setYOffset(int yOffset) {
		int yOffsetDifference = yOffset - this.yOffset;
		hitbox.shiftHitbox(0, yOffsetDifference);
		dateY += yOffsetDifference;
		headingY += yOffsetDifference;
		imageY += yOffsetDifference;
		categoryY += yOffsetDifference;
		textY += yOffsetDifference;
		this.yOffset = yOffset;
	}

	public String getHeading() {
		return heading;
	}

	public int getHeadingX() {
		return headingX;
	}

	public int getHeadingY() {
		return headingY;
	}

	public String getText() {
		return text;
	}

	public List<String> getTextInLines() {
		return textLines;
	}

	public int getTextX() {
		return textX;
	}

	public int getTextY() {
		return textY;
	}

	public int getDate() {
		return date;
	}

	public int getDateX() {
		return dateX;
	}

	public int getDateY() {
		return dateY;
	}

	public TimelineCategory getCategory() {
		return category;
	}

	public int getCategoryX() {
		return categoryX;
	}

	public int getCategoryY() {
		return categoryY;
	}

	public Image getImage() {
		return image;
	}

	public int getImageX() {
		return imageX;
	}

	public int getImageY() {
		return imageY;
	}

}
