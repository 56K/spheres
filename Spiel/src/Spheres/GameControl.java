package Spheres;

//Bibliotheken
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameControl {

	// Datenfelder
	private GameView gView;
	private GameModel gModel;
	private GamePanel gPanel;

	// Konstruktor
	public GameControl(GameView gViewArgs, GameModel gModelArgs) {
		gView = gViewArgs;
		gModel = gModelArgs;
		gPanel = gViewArgs.getGamePanel();

		// --------------------------------Action-Listerner_für_die_GameView
		gView.addExitListener(new ExitListener());
		gView.addShopListener(new ShopListener());
		gView.addMenuListener(new MenuListener());

		// --------------------------------Action-Listerner_für_dasGamePanel
		gPanel.addMouseListener(new KlickListener());

		fillGrid();
		deliverValuesGView();
	}

	// ===========_Methoden_zum_steuern_=========
	public void deliverValuesGView() {
		// gView.setTimeDraftsLeftLa();
		gView.setCBB();
		gView.setCNB();
		gView.setSSB();
		gView.setUsername();
		gView.setPoints("0");
		gView.setTimeDraftsLeftLa();
	}

	// ------ befüllt das Grid nach dem Spielstart mit Bällen ----
	public void fillGrid() {
		Ball[][] balls = gModel.getBalls();
		for (int i = 0; i < balls.length; i++) {
			Ball[] balls2 = balls[i];
			for (int j = 0; j < balls2.length; j++) {
				gModel.addBall(new Ball(i, j, gModel.getColorSet().newRandomColor()));
			}
		}
	}

	// Innere Klassen für die ActionListener
	class ExitListener implements ActionListener {

		public void actionPerformed(ActionEvent exit) {
			System.exit(0);
		}

	}

	class ShopListener implements ActionListener {
		public void actionPerformed(ActionEvent shop) {

		}
	}

	class MenuListener implements ActionListener {
		public void actionPerformed(ActionEvent menu) {

		}
	}

	class BronsonListener implements ActionListener {
		public void actionPerformed(ActionEvent bronson) {

		}
	}

	class NorrisListener implements ActionListener {
		public void actionPerformed(ActionEvent norris) {

		}
	}

	class SeagalListener implements ActionListener {
		public void actionPerformed(ActionEvent seagal) {

		}
	}

	class KlickListener extends MouseAdapter {
		public KlickListener() {
			super();
		}

		public void mousePressed(MouseEvent e) {
			
		}
	}
}
