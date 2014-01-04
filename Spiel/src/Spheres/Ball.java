package Spheres;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public class Ball extends Random implements Comparable {

	private Color ballColor;
	private int oldPos, newPos, diameter, ghostDiameter, xCoord, yCoord, xDist,
			yDist;
	private boolean isChoosen, hasRNeigh, hasLNeigh, hasDNeigh, hasUNeigh;
	private int xPos;
	private int yPos;

	public Ball(int xPosition, int yPosition, Color color) {
		xPos = xPosition;
		yPos = yPosition;
		diameter = 30;
		ghostDiameter = 40;
		xDist = 50;
		yDist = 50;
		xCoord = posToXCoord(xPos);
		yCoord = posToYCoord(yPos);
		setBallColor(color);

	}

	// =======_Koordinatenumrechnung_============
	private int posToXCoord(int pos) {
		return (pos * xDist) + 25;
	}

	private int posToYCoord(int pos) {
		return (pos * yDist) + 25;
	}

	// ====================_setters_and_getters_===============
	public Color getBallColor() {
		return ballColor;
	}

	private void setBallColor(Color ballColor) {
		this.ballColor = ballColor;
	}

	public Point getPos() {
		return new Point(xPos, yPos);
	}

	public void setPos(Point pos) {
		xPos = pos.x;
		yPos = pos.y;
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

	// ==============_Die_paintComponent-Methode_===========
	public void draw(Graphics g) {
		g.setColor(ballColor);
		g.drawOval(xCoord, yCoord, diameter, diameter);
		g.fillOval(xCoord, yCoord, diameter, diameter);
	}

	/***********************************************************************************
	 * Vergleicht Farben (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}