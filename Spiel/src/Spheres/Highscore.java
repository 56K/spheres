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
		center.add(new JLabel());
		String timeScore = createScoreLabel(user.getTimeHigh());
		center.add(new JLabel(timeScore));

		center.add(new JLabel());
		center.add(new JLabel(" Zugspiel "));
		
		center.add(new JLabel(createScoreLabel(user.getDrawHigh())));
		center.add(new JLabel());
		timeLa = new JLabel();
		int timeHigh[] = user.getTimeHigh();
		for (int i : timeHigh) {
			timeLa.setText(Integer.toString(i));
		}
		
		center.add(new JLabel());
		
		add(center);
	}



	private String createScoreLabel(int[] scores) {
		StringBuilder builder = new StringBuilder();
		for (int i : scores) {
			builder.append(i);
			builder.append(", ");
		}
		if(builder.length()>2)
			builder.setLength(builder.length()-2);
//		StringBuilder builder = new StringBuilder("<html><table>");
//		int place = 1;
//		for (int score : scores) {
//			builder.append("<tr><td>");
//			builder.append(place++);
//			builder.append("</td/><td>");
//			builder.append(score);
//			builder.append("</td></tr>");
//		}
//		builder.append("</table></html>");
		return builder.toString();
	}
	
}

