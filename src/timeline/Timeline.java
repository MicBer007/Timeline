package timeline;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import debug.Log;
import debug.LogLevel;
import inputs.InputManager;
import objects.ObjectManager;
import objects.TimelineCategory;
import objects.TimelineObject;

public class Timeline extends Canvas implements Runnable {
	
	private Log log = new Log(this.getClass().getSimpleName(), LogLevel.ERROR);
	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	
	private Thread thread;
	private boolean running = false;
	
	private ObjectManager objectManager;
	
	private InputManager inputManager;
	
	public Timeline() {
		objectManager = new ObjectManager(WIDTH, HEIGHT, null);

		objectManager.addTimelineItem(new TimelineObject("Berlinghiero", 1175, TimelineCategory.ART, "res/MadonnaAndChild.jpg", "Berlinghiero was a painter who lived at the dawn of the renaissance.", this));
		objectManager.addTimelineItem(new TimelineObject("Jan van Eyck", 1395, TimelineCategory.ART, "res/JanVanEyck.jpg", "Jan van Eyck was a painter, who is commonly regarded to be the father of oil paint.", this));
		objectManager.addTimelineItem(new TimelineObject("Beowulf", 700, TimelineCategory.LITERATURE, "res/Beowulf.jpg", "Beowulf is an ancient epic poem that cronicles the life of Beowulf, a heroic swedish king.", this));
		objectManager.addTimelineItem(new TimelineObject("Cantebury Tales", 1387, TimelineCategory.LITERATURE, "res/CanteburyTales.jpg", "The Canterbury Tales is a collection of stories written by Geoffrey Chaucer between 1387 and 1400.", this));
		objectManager.addTimelineItem(new TimelineObject("Pride and Prejudice", 1813, TimelineCategory.LITERATURE, "res/PrideAndPredjudice.jpg", "Pride and Prejudice is a romantic novel penned by Jane Austen, who had a deep understanding of human nature.", this));
		objectManager.addTimelineItem(new TimelineObject("Sir Gawain", 1400, TimelineCategory.LITERATURE, "res/SirGawainAndTheGreenKnight.jpg", "Sir Gawain and the Green Knight is a medieval romantic novel that displays the medieval knight's code of chivalry.", this));
		
		objectManager.sortObjects();
		
		inputManager = new InputManager(this);
		
		new Window(WIDTH, HEIGHT, "game", this, inputManager.getMouseListener());
	}

	public ObjectManager getObjectManager() {
		return objectManager;
	}

	public InputManager getInputManager() {
		return inputManager;
	}

	public synchronized void start() {
		thread = new Thread();
		thread.start();
		running = true;
		run();
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int FPS = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running) {
				render();
			}
			FPS++;
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				log.info("FPS: " + FPS, 10);
				FPS = 0;
			}
		}
		stop();
	}
	
	private void tick() {
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		objectManager.renderBackground(g);
		objectManager.renderTimeline(g);
		objectManager.renderTimelineLine(g);
		g.dispose();
		bs.show();
	}
  
}