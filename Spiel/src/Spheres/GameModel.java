package Spheres;

import java.io.Serializable;

public class GameModel implements Serializable {

	private Ball[] balls;
	private int gameMode;
	private User user;

	public GameModel(int modeArgs, User userArgs) {
		user = userArgs;
		gameMode = modeArgs;

	}
}
