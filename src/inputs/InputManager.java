package inputs;

import timeline.Timeline;

public class InputManager {
	
	private MouseInputListener mouseListener;
	
	public InputManager(Timeline timeline) {
		mouseListener = new MouseInputListener(timeline);
	}

	public MouseInputListener getMouseListener() {
		return mouseListener;
	}

}
