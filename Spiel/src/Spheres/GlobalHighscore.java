package Spheres;

import java.awt.GridLayout;
import java.util.Collection;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class GlobalHighscore extends View {	
	
	private JPanel center;
	
	public GlobalHighscore(Spheres spheres, User konUser, Collection<HighscoreEntry> timehigh, Collection<HighscoreEntry> drawHigh) {
		super(spheres, konUser);
		super.setWhereAmI("Highscore");
		super.setBackBVisibility(true);
		
		center = new JPanel();
		center.setLayout(new GridLayout(2, 1));
		JTextPane timePane = new JTextPane();
		timePane.setContentType("text/html");
		timePane.setText(createScoreLabel(timehigh, "Time Highscore"));
		center.add(timePane);
		
		JTextPane drawPane = new JTextPane();
		drawPane.setContentType("text/html"); 
		drawPane.setText(createScoreLabel(timehigh, "Draw Highscore"));
		center.add(drawPane);
		add(center);
	}


	private String createScoreLabel(Collection<HighscoreEntry> entries, String caption) {
		
		StringBuilder builder = new StringBuilder("<html><h2>");
		builder.append(caption);
		builder.append("</h2><table>");
		int place = 1;
		for (HighscoreEntry score : entries) 
		{
			builder.append("<tr><td><b>");
			builder.append(place++);
			builder.append(". </b>");
			builder.append("</td/><td>");
			builder.append(score.getUser());
			builder.append("</td/><td>");
			builder.append(score.getPoints());
			builder.append("</td></tr>");
		}
		builder.append("</table></html>");
		return builder.toString();
	}
	
}

