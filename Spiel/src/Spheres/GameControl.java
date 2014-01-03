package Spheres;

//Bibliotheken
import java.awt.Graphics;
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
		fillGrid();
	}
	
	//===========_Methoden_zum_steuern_=========
	public void deliverValuesGView(){
		//gView.setTimeDraftsLeftLa();
		gView.setCBB();
		gView.setCNB();
		gView.setSSB();
		gView.setNameBrd("Spieler");
		gView.setUsername();
		gView.setPointsBrd("Punkte");
		gView.setPoints("0");
		gView.setTimeDraftsLeftLa();
	}
	
	//------ befüllt das Grid nach dem Spielstart mit Bällen ----
	public void fillGrid(){
		for (int i = 0; i<36;i++){
			gModel.addBall(new Ball(i,gModel.getGameMode()));
			
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
}
