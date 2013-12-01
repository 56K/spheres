package Spheres;

import java.io.Serializable;
import java.sql.Time;

public class GameModel implements Serializable {

	private Ball[] balls;
	private int gameMode;
	private User user;
	private Time timeLeft;
	private int drawsLeft;

	public GameModel(int modeArgs, User userArgs) {
		user = userArgs;
		gameMode = modeArgs;
	}
	
	public String getUsername(){
		return user.getName();
	}
	
	public String getPointsString(){
		Integer i = (Integer) user.getPoints();
		return i.toString();
	}
	
	public String getCBCount(){
		Integer i = (Integer) user.getCbAnz();
		return i.toString();
	}
	public String getCNCount(){
		Integer i = (Integer) user.getCnAnz();
		return i.toString();
	}
	public String getSSCount(){
		Integer i = (Integer) user.getSsAnz();
		return i.toString();
	}
	public void setTimeLeft(Time time){
		timeLeft=time;
	}
	public String getTimeLeft(){
		String time = timeLeft.toString();
		return time;
	}
	public void setDrawsLeft(int draws){
		drawsLeft=draws;
	}
	public String getDrawsLeft(){
		Integer draws = (Integer) drawsLeft;
		return draws.toString();
	}
	public int getGameMode(){
		return gameMode;
	}
}
