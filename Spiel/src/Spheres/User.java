package Spheres;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Spheres.GameChangeEvent.EventType;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6359032272009837832L;
	private String name, pass;
	private Integer points, cbAnz, ssAnz, cnAnz;
	private transient int currentPoints;
	private ColorSet colorChoice;
	private int[] timeHigh = { 10 }, drawHigh = { 10 };
	private transient List<GameListener> listeners;

	public User(String username, String pw) {
		name = username;
		pass = pw;
		points = 0;
		timeHigh[0] = 0;
		drawHigh[0] = 0;
		cbAnz=0;
		ssAnz=0;
		cnAnz=0;
		colorChoice=ColorSet.NORMAL;
	}

	// +++++++++++_Methoden_zum_ändern_von_Varialblen_++++++++++

	public void addPoints(int add) {
		/**
		 * Methode zum addieren erreichter Punkte
		 */
		points += add;
		currentPoints += add;
	}

	public void subPoints(int sub) {
		/**
		 * Methode zum subtrahieren von Punkten (Jokereinkauf)
		 */
		points -= sub;
	}
	
	public void addCB(int anz){
		setCbAnz(cbAnz+anz);
	}
	public void addSS(int anz){
		setSsAnz(ssAnz+anz);
	}
	public void addCN(int anz){
		setCnAnz(cnAnz+anz);
	}
	
	public void setCbAnz(Integer cbAnz) {
		this.cbAnz = cbAnz;
		fireGameEvent(new GameChangeEvent(EventType.BRONSON_CHANGED, cbAnz));
	}
	
	public void setCnAnz(Integer cnAnz) {
		this.cnAnz = cnAnz;
		fireGameEvent(new GameChangeEvent(EventType.NORRIS_CHANGED, cnAnz));
	}
	
	public void setSsAnz(Integer ssAnz) {
		this.ssAnz = ssAnz;
		fireGameEvent(new GameChangeEvent(EventType.SEAGAL_CHANGED, ssAnz));
	}

	public boolean checkTimeTopTen(int count) {
		/**
		 * Diese Methode überprüft ob das übergebene Ergebnis für die
		 * persönliche Bestenliste reicht.
		 */
		if (count > timeHigh[timeHigh.length - 1])
			return true;

		return false;
	}

	public void writeTimeTopTen(int count) {
		for (int i = 0; i < timeHigh.length; i++) {
			if (count > timeHigh[i]) {
				int h = timeHigh[i];
				timeHigh[i] = count;
				count = h;
			}
		}
	}

	public boolean checkDrawTopTen(int count) {
		/**
		 * Diese Methode überprüft ob das übergebene Ergebnis für die
		 * persönliche Bestenliste reicht.
		 */
		if (count > drawHigh[drawHigh.length - 1])
			return true;

		return false;
	}

	public void writeDrawTopTen(int count) {
		for (int i = 0; i < drawHigh.length; i++) {
			if (count > drawHigh[i]) {
				int h = drawHigh[i];
				drawHigh[i] = count;
				count = h;
			}
		}
	}

	public void delUser(){
		
	}
	
	// +++++++++++++++++++_getters-and_settrs_++++++++++++++++++
	public String getName() {
		/**
		 * Übergibt den Namen des Users als String
		 */
		return name;
	}

	public void setName(String name) {
		/**
		 * Setzt den Namen des Users auf den übergebenen String
		 */
		this.name = name;
	}

	public String getPass() {
		/**
		 * Gibt das Passwort verschlüsselt als String zurück
		 */
		return pass;
	}

	public void setPass(String pass) {
		/**
		 * Setzt das Passwort auf den übergebenen String
		 */
		this.pass = pass;
	}

	public int getPoints() {
		/**
		 * Gibt das Punktekonto zurück
		 */
		return points;
	}

	public void setPoints(int points) {
		/**
		 * Setzt das Punktekonto auf übergebenen Wert
		 */
		this.points = points;
	}
	
	public Integer getCurrentPoints() {
		return currentPoints;
	}
	
	public void setCurrentPoints(int currentPoints) {
		this.currentPoints = currentPoints;
	}

	public int[] getTimeHigh() {
		/**
		 * Gibt die 10 besten Spielergebnisse im ZEIT-Modus zurück
		 */
		return timeHigh;
	}

	public void setTimeHigh(int[] timeHigh) {
		/**
		 * Überschreibt die Highscoreliste
		 */
		this.timeHigh = timeHigh;
	}

	public int[] getDrawHigh() {
		/**
		 * Gibt die 10 besten Spielergebnisse im ZUG-Modus zurück
		 */
		return drawHigh;
	}

	public void setDrawHigh(int[] drawHigh) {
		/**
		 * Überschreibt die Highscoreliste
		 */
		this.drawHigh = drawHigh;
	}
	
	public int getCbAnz() {
		/**
		 * Gibt die Anzahl der Charles Bronson Joker zurück
		 */
		return cbAnz;
	}

	public int getSsAnz() {
		/**
		 * Gibt die Anzahl der Steven Seagal Joker zurück
		 */
		return ssAnz;
	}

	public int getCnAnz() {
		/**
		 * Gibt die Anzahl der Chuck Norris Joker zurück
		 */
		return cnAnz;
	}
	
	public String getCbAnzS() {
		/**
		 * Gibt die Anzahl der Charles Bronson Joker als String zurück
		 */
		return cbAnz.toString();
	}

	public String getSsAnzS() {
		/**
		 * Gibt die Anzahl der Steven Seagal Joker als String zurück
		 */
		return ssAnz.toString();
	}

	public String getCnAnzS() {
		/**
		 * Gibt die Anzahl der Chuck Norris Joker als String zurück
		 */
		return cnAnz.toString();
	}

	public ColorSet getColorChoice() {
		/**
		 * Gibt die Farbwahl der Kugeln als Interger zurück
		 */
		return colorChoice;
	}

	public void setColorChoice(ColorSet colorChoice) {
		/******
		 * Setzt die Farbwahl als Integer 		 
		 * */
		this.colorChoice = colorChoice;
	}
	
	protected void fireGameEvent(GameChangeEvent event) {
		for (GameListener listener : listeners) {
			listener.notify(event);
		}
	}
	
	public void addGameListener(GameListener listener) {
		if(listeners==null)
			listeners = new ArrayList<>();
		listeners.add(listener);
	}
	
	public void removeGameListener(GameListener listener) {
		listeners.add(listener);
	}	
}
