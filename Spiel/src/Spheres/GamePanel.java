package Spheres;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

public class GamePanel extends JComponent {

	private GameModel gModel;

	public GamePanel(GameModel gModelArgs) {
		super();
		gModel = gModelArgs;

		setPreferredSize(new Dimension(350, 350));
	}

	// ----- hier wir gezeichnet ------
	public void paintComponent(Graphics g) {
		Ball[][] balls = gModel.getBalls();
		for (Ball[] balls2 : balls) {
			for (Ball ball : balls2) {
				ball.draw(g);				
			}
		}
	}

}
