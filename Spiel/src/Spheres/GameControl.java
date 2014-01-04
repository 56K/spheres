package Spheres;

//Bibliotheken
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameControl {

	// Datenfelder
	private GameView gameview;
	private GameModel gamemodel;

	// Konstruktor
	public GameControl(GameView g_view, GameModel g_model) {
		gameview = g_view;
		gamemodel = g_model;

		gameview.addExitListener(new ExitListener());
		gameview.addShopListener(new ShopListener());
		gameview.addMenuListener(new MenuListener());
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
}
