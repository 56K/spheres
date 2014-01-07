package Spheres;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class Menu extends View {

	// --------Variablendeklaration----------------------------
	private JButton startB, userSetB, highB, shopB, settB, helpB;
	private JSlider slide;
	private JLabel zeitLa, zugLa;
	private JPanel center;
	private User user;
	private Spheres spheres;

	// ------ Konstruktor
	public Menu(Spheres spheresArgs, User konUser) {
		super(spheresArgs, konUser);
		this.spheres = super.getSpheres();
		this.user = konUser;
		super.setWhereAmI("Hauptmenü");
		super.setBackBVisibility(false);
		center = new JPanel();
		center.setLayout(new GridLayout(8, 3, 2, 10));
		super.add(center, BorderLayout.CENTER);

		// ------------ centerPanel befüllen
		// ------------------------------------------------------Slider
		zeitLa = new JLabel("Zeitmodus ");
		zeitLa.setHorizontalAlignment(JLabel.RIGHT);
		center.add(zeitLa);

		slide = new JSlider(0, 1);
		center.add(slide);

		zugLa = new JLabel(" Zugmodus");
		zugLa.setHorizontalAlignment(JLabel.LEFT);
		center.add(zugLa);
		// ------------------------------------------------------Spiel starten
		center.add(new JLabel(" "));
		startB = new JButton("Spiel starten");
		startB.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		startB.setBorder(BorderFactory.createEtchedBorder(Color.LIGHT_GRAY,
				Color.black));
		startB.setMnemonic(KeyEvent.VK_S);
		center.add(startB);
		startB.addActionListener(new ActionListener() {
			// Annonyme Listenerklasse für den Loginbutton
			@Override
			public void actionPerformed(ActionEvent e) {
				spheres.startGame(slide.getValue(),user);
			}
		});
		center.add(new JLabel(" "));
		// ------------------------------------------------------FREI
		center.add(new JLabel(" "));
		center.add(new JLabel(" "));
		center.add(new JLabel(" "));
		// ------------------------------------------------------Highscore
		center.add(new JLabel(" "));
		highB = new JButton("Highscore");
		highB.setFont(new Font("Font.Serif", Font.BOLD, 15));
		highB.setSize(20, 50);
		highB.setBorder(BorderFactory.createEtchedBorder(Color.LIGHT_GRAY,
				Color.black));
		highB.setMnemonic(KeyEvent.VK_R);
		center.add(highB);
		highB.addActionListener(new ActionListener() {
			// Annonyme Listenerklasse für den Loginbutton
			@Override
			public void actionPerformed(ActionEvent e) {
				spheres.navigateTo(new GlobalHighscore(spheres, user, spheres.loadTimeHighscore(), spheres.loadDrawHighscore()));
			}
		});
		center.add(new JLabel(" "));
		// ------------------------------------------------------Shop
		center.add(new JLabel(" "));
		shopB = new JButton("Shop");
		shopB.setFont(new Font("Font.Serif", Font.BOLD, 15));
		shopB.setBorder(BorderFactory.createEtchedBorder(Color.LIGHT_GRAY,
				Color.black));
		shopB.setMnemonic(KeyEvent.VK_O);
		center.add(shopB);
		shopB.addActionListener(new ActionListener() {
			// Annonyme Listenerklasse für den Loginbutton
			@Override
			public void actionPerformed(ActionEvent e) {
				spheres.navigateTo(new Shop(spheres, user));
			}
		});
		center.add(new JLabel(" "));
		// ------------------------------------------------------Einstellungen
		center.add(new JLabel(" "));
		userSetB = new JButton("Einstellungen");
		userSetB.setFont(new Font("Font.Serif", Font.BOLD, 15));
		userSetB.setBorder(BorderFactory.createEtchedBorder(Color.LIGHT_GRAY,
				Color.black));
		center.add(userSetB);
		userSetB.setMnemonic(KeyEvent.VK_U);
		userSetB.addActionListener(new ActionListener() {
			// Annonyme Listenerklasse für den Loginbutton
			@Override
			public void actionPerformed(ActionEvent e) {
				spheres.navigateTo(new UserSettings(spheres, user));
			}
		});
		center.add(new JLabel(" "));
		// ------------------------------------------------------Hilfe
		center.add(new JLabel(" "));
		helpB = new JButton("Hilfe");
		helpB.setFont(new Font("Font.Serif", Font.BOLD, 15));
		helpB.setBorder(BorderFactory.createEtchedBorder(Color.LIGHT_GRAY,
				Color.black));
		helpB.setMnemonic(KeyEvent.VK_H);
		center.add(helpB);
		helpB.addActionListener(new ActionListener() {
			// Annonyme Listenerklasse für den Loginbutton
			@Override
			public void actionPerformed(ActionEvent e) {
				// spheres.navigateTo(new JLabel("Hilfe"));
				user.setPoints(20000);
			}
		});
		center.add(new JLabel(" "));

	}
}
