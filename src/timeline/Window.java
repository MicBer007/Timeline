package timeline;

import java.awt.Dimension;

import javax.swing.JFrame;

import inputs.MouseInputListener;

public class Window {

	public Window(int height, int width, String title, Timeline timeline, MouseInputListener mouseListener) {
		JFrame window = new JFrame(title);
		Dimension windowDimension = new Dimension(height, width);
		window.setPreferredSize(windowDimension);
		window.setMinimumSize(windowDimension);
		window.setMaximumSize(windowDimension);
		
		addListeners(timeline, mouseListener);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.add(timeline);
		window.setVisible(true);
		timeline.start();
	}
	
	public void addListeners(Timeline timeline, MouseInputListener mouseListener) {
		timeline.addMouseListener(mouseListener);
		timeline.addMouseMotionListener(mouseListener);
	}

}
