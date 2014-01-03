package Spheres;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

public class GamePanel extends JComponent {

	private GameModel gModel;

	public GamePanel(GameModel gModelArgs) {
		super();
		gModel = gModelArgs;
		
		setPreferredSize(new Dimension(350,350));
	}

	// ----- hier wir gezeichnet ------
	public void paintComponent(Graphics g) {
		Ball ball;
		for (int i = 0; i < 36; i++) {
			ball = gModel.getBall(i);
			ball.draw(g);
		}
	}

}
