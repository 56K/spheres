package Spheres;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class UserSettings extends View {

	private static final Logger LOGGER = Logger.getLogger(ViewLogin.class
			.getName());

	private boolean delBCount;
	private JPanel centerPa, centerContPa;
	private JButton changeNameB, changeNameB2, changePWB, changePWB2, delAccB;
	private JLabel changeNameLa, changePWLa, errorLa, rightDelAccLa,
			leftDelAccLa;
	private JTextField changeNametf;
	private JPasswordField changePWf, changePWf2;
	private JRadioButton color1RB, color2RB, color3RB;
	private User user;
	private Spheres spheres;

	public UserSettings(Spheres spheresArgs, User konUser) {
		super(spheresArgs, konUser);
		super.setWhereAmI("Einstellungen");
		super.setBackBVisibility(true);
		user = konUser;
		spheres = spheresArgs;

		generateContent();
	}

	private void generateContent() {
		centerContPa = new JPanel();
		centerPa = new JPanel();
		centerPa.setLayout(new GridLayout(9, 3, 2, 5));
		add(centerContPa, BorderLayout.CENTER);

		// ------------------------------------------------------FARBAUSWAHL
		color1RB = new JRadioButton("FarbSet 1");
		color1RB.setActionCommand("0");
		color1RB.setHorizontalAlignment(JLabel.CENTER);
		color1RB.setSelected(true);
		color1RB.setMnemonic(KeyEvent.VK_1);
		color2RB = new JRadioButton("FarbSet 2");
		color2RB.setActionCommand("1");
		color2RB.setHorizontalAlignment(JLabel.CENTER);
		color2RB.setMnemonic(KeyEvent.VK_2);
		color3RB = new JRadioButton("FarbSet 3");
		color3RB.setActionCommand("2");
		color3RB.setHorizontalAlignment(JLabel.CENTER);
		color3RB.setMnemonic(KeyEvent.VK_3);

		ButtonGroup colorGroup = new ButtonGroup();
		colorGroup.add(color1RB);
		colorGroup.add(color3RB);
		colorGroup.add(color2RB);

		color1RB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				user.setColorChoice(ColorSet.BRIGHT);
			}
		});
		color2RB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				user.setColorChoice(ColorSet.NORMAL);
			}
		});
		color3RB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				user.setColorChoice(ColorSet.DARK);
			}
		});
		centerPa.add(new JLabel(" "));
		centerPa.add(new JLabel(" "));
		centerPa.add(new JLabel(" "));
		centerPa.add(color1RB);
		centerPa.add(color2RB);
		centerPa.add(color3RB);

		// ------------------------------------------------------Namen ändern
		centerPa.add(new JLabel(" "));
		changeNameB = new JButton("Namen ändern");
		changeNameB.setBorder(BorderFactory.createEtchedBorder(Color.LIGHT_GRAY,
				Color.black));
		changeNameB.setFont(new Font("Font.Serif", Font.BOLD, 15));
		changeNameB.setSize(20, 50);
		changeNameB.setBorder(BorderFactory.createEtchedBorder(
				Color.LIGHT_GRAY, Color.black));
		changeNameB.setMnemonic(KeyEvent.VK_N);

		centerPa.add(changeNameB);
		changeNameB.addActionListener(new ActionListener() {
			// Annonyme Listenerklasse für den Loginbutton
			@Override
			public void actionPerformed(ActionEvent e) {
				if (changeNameLa.isVisible()) {
					changeNameLa.setVisible(false);
					changeNametf.setVisible(false);
					changeNameB2.setVisible(false);
				} else {
					changeNameLa.setVisible(true);
					changeNametf.setVisible(true);
					changeNameB2.setVisible(true);
				}
			}
		});
		centerPa.add(new JLabel(" "));
		// ------------------------------------------------------Namen ändern2
		changeNameLa = new JLabel("Neuer Name: ");
		changeNameLa.setVisible(false);
		centerPa.add(changeNameLa);

		changeNametf = new JTextField();
		changeNametf.setFont(new Font("Font.Serif", Font.BOLD, 15));
		changeNametf.setSize(20, 50);
		changeNametf.setBorder(BorderFactory.createEtchedBorder(
				Color.LIGHT_GRAY, Color.black));
		changeNametf.setVisible(false);
		centerPa.add(changeNametf);
		changeNameB2 = new JButton("Ändern");
		changeNameB2.setBorder(BorderFactory.createEtchedBorder(Color.LIGHT_GRAY,
				Color.black));
		changeNameB2.setFont(new Font("Font.Serif", Font.BOLD, 15));
		changeNameB2.setSize(20, 50);
		changeNameB2.setBorder(BorderFactory.createEtchedBorder(
				Color.LIGHT_GRAY, Color.black));
		changeNameB2.setMnemonic(KeyEvent.VK_D);
		changeNameB2.setVisible(false);
		centerPa.add(changeNameB2);
		changeNameB2.addActionListener(new ActionListener() {
			// Annonyme Listenerklasse für den Loginbutton
			@Override
			public void actionPerformed(ActionEvent e) {
				String oldName = user.getName();
				user.setName(changeNametf.getText());
				changeNameLa.setVisible(false);
				changeNametf.setVisible(false);
				changeNameB2.setVisible(false);
				File oldNameF = new File(oldName);
				oldNameF.delete();
			}
		});
		// ------------------------------------------------------Passwort ändern
		centerPa.add(new JLabel(" "));
		changePWB = new JButton("Passwort ändern");
		changePWB.setBorder(BorderFactory.createEtchedBorder(Color.LIGHT_GRAY,
				Color.black));
		changePWB.setFont(new Font("Font.Serif", Font.BOLD, 15));
		changePWB.setSize(20, 50);
		changePWB.setBorder(BorderFactory.createEtchedBorder(Color.LIGHT_GRAY,
				Color.black));
		changePWB.setMnemonic(KeyEvent.VK_P);
		centerPa.add(changePWB);
		changePWB.addActionListener(new ActionListener() {
			// Annonyme Listenerklasse für den Loginbutton
			@Override
			public void actionPerformed(ActionEvent e) {
				if (changePWLa.isVisible()) {
					changePWLa.setVisible(false);
					changePWf.setVisible(false);
					changePWf2.setVisible(false);
					changePWB2.setVisible(false);
				} else {
					changePWLa.setVisible(true);
					changePWf.setVisible(true);
					changePWf2.setVisible(true);
					changePWB2.setVisible(true);
				}
			}
		});
		centerPa.add(new JLabel(" "));
		// ------------------------------------------------------Passwort
		// ändern2

		changePWLa = new JLabel("Neues Passwort: ");
		changePWLa.setVisible(false);
		centerPa.add(changePWLa);

		changePWf = new JPasswordField();
		changePWf.setFont(new Font("Font.Serif", Font.BOLD, 15));
		changePWf.setSize(20, 50);
		changePWf.setBorder(BorderFactory.createEtchedBorder(Color.LIGHT_GRAY,
				Color.black));
		changePWf.setVisible(false);
		centerPa.add(changePWf);
		changePWf2 = new JPasswordField();
		changePWf2.setFont(new Font("Font.Serif", Font.BOLD, 15));
		changePWf2.setSize(20, 50);
		changePWf2.setBorder(BorderFactory.createEtchedBorder(Color.LIGHT_GRAY,
				Color.black));
		changePWf2.setVisible(false);
		centerPa.add(changePWf2);
		centerPa.add(new JLabel());
		// ------------------------------------------------------PUNKT_6
		centerPa.add(new JLabel());
		changePWB2 = new JButton("Ändern");
		changePWB2.setBorder(BorderFactory.createEtchedBorder(Color.LIGHT_GRAY,
				Color.black));
		changePWB2.setFont(new Font("Font.Serif", Font.BOLD, 15));
		changePWB2.setSize(20, 50);
		changePWB2.setBorder(BorderFactory.createEtchedBorder(Color.LIGHT_GRAY,
				Color.black));
		changePWB2.setMnemonic(KeyEvent.VK_R);
		changePWB2.setVisible(false);
		centerPa.add(changePWB2);
		changePWB2.addActionListener(new ActionListener() {
			// Annonyme Listenerklasse für den Loginbutton
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					String newPW1 = new String(changePWf.getPassword());
					String newPW2 = new String(changePWf2.getPassword());
					MessageDigest digest = MessageDigest.getInstance("MD5");
					byte[] result = digest.digest(newPW1.getBytes());
					String hashValue = new String(result);

					if (newPW1.equals(newPW2) && newPW2.equals(newPW1)) {
						user.setPass(hashValue);
						changePWLa.setVisible(false);
						changePWf.setVisible(false);
						changePWf2.setVisible(false);
						changePWB2.setVisible(false);
						errorLa.setForeground(Color.GREEN.darker());
						errorLa.setText("Ändern erfolgreich");
					} else
						errorLa.setForeground(Color.RED.darker());
						errorLa.setText("<html>Passwörter stimmen<br>nicht überein !</html>");

				} catch (NoSuchAlgorithmException e) {
					LOGGER.log(Level.SEVERE, "MD5 Algorithmus nicht gefunden",
							e);
				}
			}
		});
		// ------------------------------------------------------Spieler löschen
		leftDelAccLa = new JLabel();
		leftDelAccLa.setHorizontalAlignment(JLabel.RIGHT);
		centerPa.add(leftDelAccLa);
		delAccB = new JButton("Spieler löschen");
		delAccB.setBorder(BorderFactory.createEtchedBorder(Color.LIGHT_GRAY,
				Color.black));
		delAccB.setFont(new Font("Font.Serif", Font.BOLD, 15));
		delAccB.setSize(20, 50);
		delAccB.setBorder(BorderFactory.createEtchedBorder(Color.LIGHT_GRAY,
				Color.black));
		delAccB.setMnemonic(KeyEvent.VK_N);
		centerPa.add(delAccB);
		delAccB.addActionListener(new ActionListener() {
			// Annonyme Listenerklasse für den Loginbutton
			@Override
			public void actionPerformed(ActionEvent e) {

				if (delBCount == false) {
					errorLa.setForeground(Color.RED);
					errorLa.setText("<html><h4>Bitte bestätigen das Ihr Account "
							+ "gelöscht werden soll.</h4></html>");
					delAccB.setBackground(Color.YELLOW);
					delAccB.setForeground(Color.RED);
					delAccB.setText("Bestätigen");
					leftDelAccLa.setText(">  >  >>  >>  >>> ");
					rightDelAccLa.setText(" <<<  <<  <<  <  <");
				}
				else{
					spheres.navigateTo(new ViewLogin(spheres),
							SlidingPanel.Direction.RIGHT);
					user.delUser();
					
				}

			}
		});
		rightDelAccLa = new JLabel();
		rightDelAccLa.setHorizontalAlignment(JLabel.LEFT);
		rightDelAccLa.setForeground(Color.RED);
		leftDelAccLa.setForeground(Color.RED);
		centerPa.add(rightDelAccLa);
		centerContPa.add(centerPa);
		// ------------------------------------------------------ERROR_Label
		errorLa = new JLabel();
		errorLa.setForeground(Color.red);
		errorLa.setMaximumSize(new Dimension(450, 20));
		errorLa.setHorizontalAlignment(JLabel.CENTER);
		centerContPa.add(errorLa);

		// Ende Menü
	}
}