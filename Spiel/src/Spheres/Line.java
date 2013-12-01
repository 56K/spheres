package Spheres;

//Library's---------------------------------
import java.awt.*;

//Class--------------------------------------------------------------------------------------------
public class Line {

	//Datenfelder---------------------------------------------
	private int x_start, y_start, x_end, y_end;
	private Color color;

	//Constructor---------------------------------------------
	public Line(int x_s, int y_s, int x_e, int y_e) {

		x_start = x_s;
		y_start = y_s;
		x_end = x_e;
		y_end = y_e;
		color = Color.magenta;
	}
	
	//Methods--------------------------------------------------
	public void draw(Graphics g) {
		
		g.setColor(color);
		g.drawLine(x_start, y_start, x_end, y_end);
	}
	
	public void setEnd(int x_e, int y_e) {
		
		x_end = x_e;
		y_end = y_e;
	} 
	
	public void setColor(Color c) {
		
		color = c;
	}
	//--------------------------------------------------------
}
//--------------------------------------------------------------------------------------------------
//blabla
