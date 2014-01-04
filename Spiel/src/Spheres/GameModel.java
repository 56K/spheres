package Spheres;

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;
import java.sql.Time;

public class GameModel implements Serializable {

	private Ball[][] balls;
	private int gameMode;
	private User user;
	private Time timeLeft;
	private int drawsLeft;

	// Konstruktor
	public GameModel(int modeArgs, User userArgs) {
		user = userArgs;
		gameMode = modeArgs;
		timeLeft = new Time(60000);
		drawsLeft = 30;
		balls = new Ball[6][6];
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

	public void setTimeLeft(Time time) {
		timeLeft = time;
	}

	public String getTimeLeft() {
		String time = timeLeft.toString();
		return time;
	}

	public void setDrawsLeft(int draws) {
		drawsLeft = draws;
	}

	public String getDrawsLeft() {
		Integer draws = (Integer) drawsLeft;
		return draws.toString();
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
}
