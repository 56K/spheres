package Spheres;

//Bibliotheken
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameControl {

	// Datenfelder
	private GameView gView;
	private GameModel gModel;

	// Konstruktor
	public GameControl(GameView gViewArgs, GameModel gModelArgs) {
		gView = gViewArgs;
		gModel = gModelArgs;

		// --------------------------------Action-Listerner_für_die_GameView
		gView.addExitListener(new ExitListener());
		gView.addShopListener(new ShopListener());
		gView.addMenuListener(new MenuListener());
		
		deliverValuesGView();
	}
	
	//===========_Methoden_zum_steuern_=========
	public void deliverValuesGView(){
		//gView.setTimeDraftsLeftLa();
		gView.setCBB();
		gView.setCNB();
		gView.setSSB();
		gView.setNameBrd("Spieler");
		//gView.setUsername();
		gView.setPointsBrd("Punkte");
		gView.setPoints();
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
}
