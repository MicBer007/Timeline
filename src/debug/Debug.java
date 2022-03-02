package debug;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import objects.Hitbox;

public class Debug {
	
	public static final List<Integer> TIMELINE_YEAR_MARKERS = Arrays.asList(new Integer[] {-6000, -5500, -5000, -4500, -4000, -3500, -3000, -2500, -2000, -1500, -1000, -500, 0, 500, 1000, 1500, 2000});
	
	public static final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;
	
	public static final Color TIMELINE_HEADING_COLOR = Color.BLACK;
	public static final Color TIMELINE_TEXT_COLOR = Color.BLACK;
	public static final Color TIMELINE_BOX_COLOR = Color.WHITE;
	public static final Color TIMELINE_LINE_COLOR = Color.DARK_GRAY;
	public static final Color TIMELINE_DATE_COLOR = Color.BLACK;
	public static final Color TIMELINE_COLOR = Color.BLACK;

	public static final float TIMELINE_HEADING_FONT_SIZE = 1.4f;
	public static final float TIMELINE_DATE_FONT_SIZE = 1.15F;
	public static final float TIMELINE_TEXT_FONT_SIZE = 1f;

	public static final int TIMELINE_TEXT_BORDER_X = 20;
	public static final int TIMELINE_TEXT_BORDER_Y = 16;
	public static final int TIMELINE_TEXT_Y_SIZE = (int) (14 * TIMELINE_TEXT_FONT_SIZE);
	
	public static final int TIMELINE_HEIGHT = -50;
	public static final int TIMELINE_ITEM_BORDER = 15;

	public static final int TIMELINE_DATE_BORDER = 25;
	
	
	public static final int IMAGE_WIDTH = 100;
	public static final int IMAGE_HEIGHT = 100;
	
	public static final float PIXELS_PER_YEAR = 2f;
	
	public static final int X_MAX = (int) (-1980 * PIXELS_PER_YEAR);
	public static final int X_MIN = (int) (6300 * PIXELS_PER_YEAR);
	
	public static final int Y_MAX = (int) (200 * PIXELS_PER_YEAR);
	
	public static final int SCREEN_MARGIN = 75;
	
	
	public static final int CAMERA_START_X = (int) (-100 * PIXELS_PER_YEAR);
	public static final int CAMERA_START_Y = 100;
	
	public static Hitbox getClosedHitbox() {
		return new Hitbox(-75, 0, 150, 150);
	}
	
	public static Hitbox getOpenHitbox() {
		return new Hitbox(-75, 0, 300, 250);
	}

}
