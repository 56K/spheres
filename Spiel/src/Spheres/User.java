package Spheres;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6359032272009837832L;
	private String name, pass;
	private Integer points, cbAnz, ssAnz, cnAnz;
	private ColorSet colorChoice;
	private int[] timeHigh = { 10 }, drawHigh = { 10 };
	

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

	// +++++++++++_Methoden_zum_�ndern_von_Varialblen_++++++++++

	public void addPoints(int add) {
		/**
		 * Methode zum addieren erreichter Punkte
		 */
		points += add;
	}

	public void subPoints(int sub) {
		/**
		 * Methode zum subtrahieren von Punkten (Jokereinkauf)
		 */
		points -= sub;
	}
	
	public void addCB(int anz){
		cbAnz+=anz;
	}
	public void addSS(int anz){
		ssAnz+=anz;
	}
	public void addCN(int anz){
		cnAnz+=anz;
	}
	
	public void setCbAnz(Integer cbAnz) {
		this.cbAnz = cbAnz;
	}
	
	public void setCnAnz(Integer cnAnz) {
		this.cnAnz = cnAnz;
	}
	
	public void setSsAnz(Integer ssAnz) {
		this.ssAnz = ssAnz;
	}

	public boolean checkTimeTopTen(int count) {
		/**
		 * Diese Methode �berpr�ft ob das �bergebene Ergebnis f�r die
		 * pers�nliche Bestenliste reicht.
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
		 * Diese Methode �berpr�ft ob das �bergebene Ergebnis f�r die
		 * pers�nliche Bestenliste reicht.
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
		 * �bergibt den Namen des Users als String
		 */
		return name;
	}

	public void setName(String name) {
		/**
		 * Setzt den Namen des Users auf den �bergebenen String
		 */
		this.name = name;
	}

	public String getPass() {
		/**
		 * Gibt das Passwort verschl�sselt als String zur�ck
		 */
		return pass;
	}

	public void setPass(String pass) {
		/**
		 * Setzt das Passwort auf den �bergebenen String
		 */
		this.pass = pass;
	}

	public int getPoints() {
		/**
		 * Gibt das Punktekonto zur�ck
		 */
		return points;
	}

	public void setPoints(int points) {
		/**
		 * Setzt das Punktekonto auf �bergebenen Wert
		 */
		this.points = points;
	}

	public int[] getTimeHigh() {
		/**
		 * Gibt die 10 besten Spielergebnisse im ZEIT-Modus zur�ck
		 */
		return timeHigh;
	}

	public void setTimeHigh(int[] timeHigh) {
		/**
		 * �berschreibt die Highscoreliste
		 */
		this.timeHigh = timeHigh;
	}

	public int[] getDrawHigh() {
		/**
		 * Gibt die 10 besten Spielergebnisse im ZUG-Modus zur�ck
		 */
		return drawHigh;
	}

	public void setDrawHigh(int[] drawHigh) {
		/**
		 * �berschreibt die Highscoreliste
		 */
		this.drawHigh = drawHigh;
	}
	
	public int getCbAnz() {
		/**
		 * Gibt die Anzahl der Charles Bronson Joker zur�ck
		 */
		return cbAnz;
	}

	public int getSsAnz() {
		/**
		 * Gibt die Anzahl der Steven Seagal Joker zur�ck
		 */
		return ssAnz;
	}

	public int getCnAnz() {
		/**
		 * Gibt die Anzahl der Chuck Norris Joker zur�ck
		 */
		return cnAnz;
	}
	
	public String getCbAnzS() {
		/**
		 * Gibt die Anzahl der Charles Bronson Joker als String zur�ck
		 */
		return cbAnz.toString();
	}

	public String getSsAnzS() {
		/**
		 * Gibt die Anzahl der Steven Seagal Joker als String zur�ck
		 */
		return ssAnz.toString();
	}

	public String getCnAnzS() {
		/**
		 * Gibt die Anzahl der Chuck Norris Joker als String zur�ck
		 */
		return cnAnz.toString();
	}

	public ColorSet getColorChoice() {
		/**
		 * Gibt die Farbwahl der Kugeln als Interger zur�ck
		 */
		return colorChoice;
	}

	public void setColorChoice(ColorSet colorChoice) {
		/******
		 * Setzt die Farbwahl als Integer 		 
		 * */
		this.colorChoice = colorChoice;
	}
}
