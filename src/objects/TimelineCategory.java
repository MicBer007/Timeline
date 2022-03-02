package objects;

import java.util.Arrays;
import java.util.List;

public enum TimelineCategory {
	
	LITERATURE, ART, PHILOSOPHY, MUSIC, POLITICS, INVENTIONS;
	
	private List<String> names = Arrays.asList(new String[] {"Literature", "Art", "Philosophy", "Music", "Politics", "Inventions"});
	
	private List<String> imagePaths = Arrays.asList(new String[] {"Literature_background (2).jpg", "Art", "Philosophy", "Music", "Politics", "Inventions"});
	
	public String getCategoryName() {
		return names.get(ordinal());
	}
	
	public String getImagePath() {
		return "res/background/" + imagePaths.get(ordinal());
	}

}
