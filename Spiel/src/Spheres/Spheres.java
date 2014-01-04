package Spheres;

import javax.swing.JComponent;
import javax.swing.JFrame;

import Spheres.SlidingPanel.Direction;

public class Spheres extends WindowClosingAdapter {

	private SlidingPanel content;
	private GameView gView;
	private GameModel gModel;
	private GameControl gControl;

	public Spheres() {
		ViewLogin logView = new ViewLogin(this);

		content = new SlidingPanel();
		content.add(logView);

		JFrame frame = new JFrame("SphereS");
		frame.setLocation(350, 250);
		frame.setSize(500, 500);
		frame.getContentPane().add(content);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.addWindowListener(new WindowClosingAdapter());
	}

	public static void main(String[] args) {
		new Spheres();

	}

	public void navigateTo(JComponent component) {
		content.add(component);
	}

	public void navigateTo(JComponent component, Direction dir) {
		content.add(component, dir);
	}

	public void startGame(int mode, User user) {
		gModel = new GameModel(mode, user);
		gView = new GameView(gModel);
		gControl = new GameControl(gView, gModel);
		content.addAnimationListener(gControl);
		navigateTo(gView);
	}
}
