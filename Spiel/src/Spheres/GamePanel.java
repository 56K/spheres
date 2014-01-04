package Spheres;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

import javax.swing.JComponent;

public class GamePanel extends JComponent {

	private GameModel gModel;
	private Path2D mousePath;

	public GamePanel(GameModel gModelArgs) {
		super();
		gModel = gModelArgs;

		setPreferredSize(new Dimension(350, 350));
	}

	// ----- hier wir gezeichnet ------
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Ball[][] balls = gModel.getBalls();
		for (Ball[] balls2 : balls) {
			for (Ball ball : balls2) {
				if(ball!=null)
					ball.draw(g2d);				
			}
		}
		
		if(mousePath!=null) {
			g2d.setColor(Color.BLACK);
			g2d.draw(mousePath);
			Point2D currentPoint = mousePath.getCurrentPoint();
			g.drawLine((int)currentPoint.getX(), (int)currentPoint.getY(), getMousePosition().x, getMousePosition().y);
		}
		
	}

	public void setMousePath(Path2D mousePath) {
		this.mousePath =mousePath;
		
	}

}
