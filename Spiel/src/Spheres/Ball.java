package Spheres;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball extends Random implements Comparable {

	private Color ballColor;
	private int pos, oldPos, newPos, diameter, ghostDiameter, xCoord, yCoord,
			xDist, yDist;
	private boolean isChoosen, hasRNeigh, hasLNeigh, hasDNeigh, hasUNeigh;

	public Ball(int position, int colorSet) {
		pos = position;
		diameter = 30;
		ghostDiameter = 40;
		xDist = 50;
		yDist = 50;
		xCoord = posToXCoord(pos);
		yCoord = posToYCoord(pos);
		oldPos = newPos = pos;
		setBallColor(colorChooser(colorSet));
		
	}

	// =======_Koordinatenumrechnung_============
	private int posToXCoord(int pos) {
		return ((pos / 6) * yDist)+25;
	}

	private int posToYCoord(int pos) {
		return ((pos % 6) * xDist)+25;
	}

	// ============_Farbbestimmung des Balls_==============
	private Color colorChooser(int colorSet) {
		int x = nextInt(5);
		switch (x) {
		case 0:
			if (colorSet == 1)
				return Color.BLACK;
			else if (colorSet == 2)
				return Color.GRAY.brighter();
			else
				return Color.gray;
		case 1:
			if (colorSet == 1)
				return Color.ORANGE;
			else if (colorSet == 2)
				return Color.PINK;
			else
				return Color.YELLOW;
		case 2:
			if (colorSet == 1)
				return Color.blue.darker();
			else if (colorSet == 2)
				return Color.blue.brighter();
			else
				return Color.blue;
		case 3:
			if (colorSet == 1)
				return Color.red.darker();
			else if (colorSet == 2)
				return Color.red.brighter();
			else
				return Color.red;
		case 4:
			if (colorSet == 1)
				return Color.pink.darker();
			else if (colorSet == 2)
				return Color.cyan.brighter();
			else
				return Color.cyan.darker();
		}
		return Color.MAGENTA;
	}

	// ====================_setters_and_getters_===============
	public Color getBallColor() {
		return ballColor;
	}

	private void setBallColor(Color ballColor) {
		this.ballColor = ballColor;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public int getOldPos() {
		return oldPos;
	}

	public void setOldPos(int oldPos) {
		this.oldPos = oldPos;
	}

	public int getNewPos() {
		return newPos;
	}

	public void setNewPos(int newPos) {
		this.newPos = newPos;
	}

	public int getxDist() {
		return xDist;
	}

	public void setxDist(int xDist) {
		this.xDist = xDist;
	}

	public int getyDist() {
		return yDist;
	}

	public void setyDist(int yDist) {
		this.yDist = yDist;
	}

	public boolean isChoosen() {
		return isChoosen;
	}

	public void setChoosen(boolean isChoosen) {
		this.isChoosen = isChoosen;
	}

	public boolean isHasRNeigh() {
		return hasRNeigh;
	}

	public void setHasRNeig(boolean hasRNeigh) {
		this.hasRNeigh = hasRNeigh;
	}

	public boolean isHasLNeigh() {
		return hasLNeigh;
	}

	public void setHasLNeigh(boolean hasLNeigh) {
		this.hasLNeigh = hasLNeigh;
	}

	public boolean isHasDNeigh() {
		return hasDNeigh;
	}

	public void setHasDNeig(boolean hasDNeigh) {
		this.hasDNeigh = hasDNeigh;
	}

	public boolean isHasUNeigh() {
		return hasUNeigh;
	}

	public void setHasUNeigh(boolean hasUNeigh) {
		this.hasUNeigh = hasUNeigh;
	}

	/***********************************************************************************
	 * Vergleicht Farben (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	
	
	//==============_Die_paintComponent-Methode_===========
	public void draw(Graphics g){
		g.setColor(ballColor);
		g.drawOval(xCoord,yCoord,diameter,diameter);
		g.fillOval(xCoord,yCoord,diameter,diameter);
	}
	
	
	
	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}