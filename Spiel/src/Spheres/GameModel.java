package Spheres;

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import Spheres.GameChangeEvent.EventType;

public class GameModel implements Serializable {

	private Ball[][] balls;
	private int gameMode;
	private User user;
	private long timeLeft;
	private int drawsLeft;
	private List<GameListener> listeners;
	
	// Konstruktor
	public GameModel(int modeArgs, User userArgs) {
		user = userArgs;
		gameMode = modeArgs;
		timeLeft = TimeUnit.SECONDS.toMillis(60);
		drawsLeft = 30;
		balls = new Ball[6][6];
		this.listeners = new ArrayList<>();
	}

	public String getUsername() {
		return user.getName();
	}

	public String getPointsString() {
		Integer i = (Integer) user.getPoints();
		return i.toString();
	}

	public String getCBCount() {
		Integer i = (Integer) user.getCbAnz();
		return i.toString();
	}

	public String getCNCount() {
		Integer i = (Integer) user.getCnAnz();
		return i.toString();
	}

	public String getSSCount() {
		Integer i = (Integer) user.getSsAnz();
		return i.toString();
	}

	public void setTimeLeft(long time) {
		timeLeft = time;
		fireGameEvent(new GameChangeEvent(EventType.TIME_CHANGED, time));
		if(time<=0)
			fireGameEvent(new GameChangeEvent(EventType.GAME_OVER, 0));
	}

	public long getTimeLeft() {
		return timeLeft;
	}

	public void subDraws() {
		setDrawsLeft(getDrawsLeft()-1);
	}

	public void setDrawsLeft(int draws) {
		drawsLeft = draws;
		fireGameEvent(new GameChangeEvent(EventType.DRAWS_CHANGED, draws));
		if(draws<=0)
			fireGameEvent(new GameChangeEvent(EventType.GAME_OVER, 0));
	}

	public int getDrawsLeft() {
		return drawsLeft;
	}

	public int getGameMode() {
		return gameMode;
	}

	public ColorSet getColorSet() {
		return user.getColorChoice();
	}

	// =========Ball-OPerationen=========
	public void addBall(Ball ballArgs) {
		Point there = ballArgs.getPos();
		balls[there.x][there.y] = ballArgs;
	}

	public Ball getBall(int i, int j) {
		return balls[i][j];
	}

	protected Ball[][] getBalls() {
		return balls;
	}

	public void deleteBall(Ball ball) {
		Point pos = ball.getPos();
		balls[pos.x][pos.y] = null;
		for (int i = pos.y - 1; i >= 0; i--) {
			Ball current = balls[pos.x][i];
			current.setPos(new Point(pos.x, i + 1));
			balls[pos.x][i + 1] = current;
		}
		balls[pos.x][0] = new Ball(pos.x, 0, getColorSet().newRandomColor());
		user.addPoints(1);
		fireGameEvent(new GameChangeEvent(EventType.POINTS_CHANGED, user.getCurrentPoints()));
	}

	public void deleteColor(Color ballColor) {
		Ball[][] balls2 = getBalls();
		for (Ball[] balls : balls2) {
			for (Ball ball : balls) {
				if (ball.getBallColor().equals(ballColor))
					deleteBall(ball);
			}
		}
	}
	
	protected void fireGameEvent(GameChangeEvent event) {
		for (GameListener listener : listeners) {
			listener.notify(event);
		}
	}
	
	public void addGameListener(GameListener listener) {
		listeners.add(listener);
		if(user!=null)
			user.addGameListener(listener);
	}
	
	public void removeGameListener(GameListener listener) {
		listeners.add(listener);
		if(user!=null)
			user.removeGameListener(listener);
	}
	
	public User getUser() {
		return user;
	}

	public void useJoker(Joker joker) {
		switch (joker) {
		case BRONSON:
			user.setCbAnz(user.getCbAnz()-1);
			break;
		case SEAGAL:
			user.setSsAnz(user.getSsAnz()-1);
			break;
		case NORRIS:
			user.setCnAnz(user.getCnAnz()-1);
			break;
		default:
			break;
		}
	}
	
	public boolean hasFinished() {
		if(getGameMode()==1)
			return getDrawsLeft()<=0;
		else
			return getTimeLeft()<=0;
	}
}
