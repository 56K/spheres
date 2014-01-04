package Spheres;


public class Animator extends Thread {

	private GameModel model;
	private int sleeptime = 20;
	private GamePanel panel;
	private boolean pause;
	private long lastTime;
	
	public Animator(GameModel model, GamePanel panel) {
		this.model = model;
		this.panel = panel;
		setDaemon(true);
	}

	@Override
	public void run() {
		lastTime = System.currentTimeMillis();
		while (true) {
			if(!pause)
			{
				long currentTime = System.currentTimeMillis();
				model.setTimeLeft(model.getTimeLeft() - (currentTime - lastTime));
				lastTime = currentTime;
				Ball[][] balls = model.getBalls();
				for (Ball[] balls2 : balls) {
					for (Ball ball : balls2) {
						if(ball!=null)
							ball.fallIfNecessary(5);
					}
				}
				panel.repaint();				
			}
			if (model.hasFinished())
				break;
			try {
				Thread.sleep(sleeptime);
			} catch (InterruptedException e) {
				break;
			}

		}
	}

	public void pause(boolean pause) {
		this.pause = pause;
		lastTime = System.currentTimeMillis();
		
	}
}
