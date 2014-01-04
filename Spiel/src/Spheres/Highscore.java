package Spheres;

import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Highscore extends View {

	private JPanel center;
	private JButton back;
	private User user;
	private Spheres spheres;

	
	
	public Highscore(Spheres spheresArgs, User konUser) {
		super(spheresArgs, konUser);
		this.spheres = super.getSpheres();
		super.setWhereAmI("Shop");
		super.setBackBVisibility(true);
		user = konUser;
		

		center = new JPanel();
		
		add(center);
	}
	
}

