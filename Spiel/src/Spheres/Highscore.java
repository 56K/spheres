package Spheres;

import java.awt.GridLayout;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Highscore extends View {

	private JPanel center;
	private JButton back;
	private User user;
	private Spheres spheres;
	private JLabel timeLa;

	
	
	public Highscore(Spheres spheresArgs, User konUser) {
		super(spheresArgs, konUser);
		this.spheres = super.getSpheres();
		super.setWhereAmI("Shop");
		super.setBackBVisibility(true);
		user = konUser;
		

		center = new JPanel();
		
		center.setLayout(new GridLayout(4,4,5,5));
		center.add(new JLabel());
		center.add(new JLabel("Erreichte Punkte: "));
		center.add(new JLabel(user.getCurrentPoints().toString()));
		center.add(new JLabel());
		
		center.add(new JLabel());
		center.add(new JLabel());
		center.add(new JLabel());
		center.add(new JLabel());
		
		center.add(new JLabel());
		center.add(new JLabel("Zeitspiel"));
		center.add(new JLabel(" Zugspiel "));
		center.add(new JLabel());
		
		center.add(new JLabel());
		
		timeLa = new JLabel();
		int timeHigh[] = user.getTimeHigh();
		for (int i : timeHigh) {
			timeLa.setText(Integer.toString(i));
		}
		
		center.add(new JLabel());
		
		add(center);
	}
	
}

