package Spheres;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.Random;

public class Ball extends Random {

	private Color ballColor;
	private int oldPos, newPos, diameter, ghostDiameter, xCoord, yCoord, xDist,
			yDist;
	private int targetY;
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
		targetY = posToYCoord(yPos);
		yCoord = 0;
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
		xCoord = posToXCoord(xPos);
		targetY = posToYCoord(yPos);
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

	protected boolean isHasDNeigh() {
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
	public void draw(Graphics2D g) {
		g.setColor(ballColor);
		if(isChoosen)
		{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
			int extendedDiameter = ghostDiameter - diameter;
			g.fillOval(xCoord-extendedDiameter/2, yCoord-extendedDiameter/2, ghostDiameter, ghostDiameter);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		}
		g.fillOval(xCoord, yCoord, diameter, diameter);
		if(isChoosen) {
			g.setColor(Color.BLACK);
			g.drawOval(xCoord, yCoord, diameter, diameter);			
		}
	}

	/***********************************************************************************
	 * Vergleicht Farben (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */

	
	public boolean contains(Point point) {
		Double boundingBox = new Rectangle2D.Double(xCoord, yCoord, ghostDiameter, ghostDiameter);
		return boundingBox.contains(point);
	}
	
	public Point getCenter() {
		return new Point(xCoord+diameter/2, yCoord+diameter/2);
	}
	
	public void fallIfNecessary(int increment) {
		if(targetY!=yCoord)
		{
			yCoord += increment;
			targetY = Math.max(targetY, yCoord);
		}
	}
}