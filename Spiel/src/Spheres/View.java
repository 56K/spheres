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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class View extends JPanel {

	private static final Logger LOGGER = Logger.getLogger(ViewLogin.class
			.getName());
	private JLabel userLa, titelLa, east, west;
	private JButton beendenB, abmeldenB, backB;
	private JPanel header;
	protected JPanel footer;
	private User user;

	private Spheres spheres;
	private JComponent backAddress;

	public View(Spheres spheresArgs, User konUser) {
		this(spheresArgs,konUser,null);
	}

	public View(Spheres spheresArgs, User konUser, JComponent backAddress) {
		this.spheres = spheresArgs;
		this.user = konUser;
		this.backAddress = backAddress;
		generateContent();
	}

	
	private void generateContent() {
		
		// ##################____geerbtes_Panel_mit_Ojekten_befüllen___#####
		setBackground(Color.green.darker());
		setLayout(new BorderLayout(10, 20));
		// ===================================================> Ost und West
		east = new JLabel(" ");
		add(east, BorderLayout.EAST);
		west = new JLabel(" ");
		add(west, BorderLayout.WEST);
		// ===================================================> HEADER

		header = new JPanel();
		header.setLayout(new GridLayout(1, 2, 0, 20));
		add(header, BorderLayout.NORTH);
		userLa = new JLabel("<html>Hallo <b>" + user.getName()
				+ "</b><br>Sie befinden sich hier:</html>");
		userLa.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
		userLa.setHorizontalAlignment(JLabel.RIGHT);
		header.add(userLa);
		titelLa = new JLabel();
		titelLa.setHorizontalAlignment(JLabel.CENTER);
		titelLa.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		header.add(titelLa);

		/* ************************************************
		 * 
		 * !!!!! !!! Panel center muss selbst erstellt werden ! ! o
		 * 
		 * *************************************************
		 */

		// footer anlegen befüllen
		footer = new JPanel();
		footer.setLayout(new GridLayout(1, 4, 10, 5));
		add(footer, BorderLayout.SOUTH);
		beendenB = new JButton("EXIT");
		beendenB.setBorder(BorderFactory.createEtchedBorder(Color.LIGHT_GRAY,
				Color.black));
		beendenB.setSize(new Dimension(10, 20));
		beendenB.setAlignmentX(LEFT_ALIGNMENT);
		beendenB.setMnemonic(KeyEvent.VK_E);
		footer.add(beendenB);
		beendenB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				spheres.saveUser(user);
				spheres.exit();
			}
		});

		abmeldenB = new JButton("Logout");
		abmeldenB.setBorder(BorderFactory.createEtchedBorder(Color.LIGHT_GRAY,
				Color.black));
		abmeldenB.setSize(new Dimension(10, 20));
		abmeldenB.setAlignmentX(CENTER_ALIGNMENT);
		abmeldenB.setMnemonic(KeyEvent.VK_L);
		footer.add(abmeldenB);
		abmeldenB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				spheres.navigateTo(new ViewLogin(spheres),
						SlidingPanel.Direction.RIGHT);
				spheres.saveUser(user);
			}
		});

		backB = new JButton("Back");
		backB.setBorder(BorderFactory.createEtchedBorder(Color.LIGHT_GRAY,
				Color.black));
		backB.setSize(new Dimension(10, 20));
		backB.setAlignmentX(LEFT_ALIGNMENT);
		backB.setMnemonic(KeyEvent.VK_B);
		footer.add(backB);
		backB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(backAddress!=null)
					spheres.navigateTo(backAddress, SlidingPanel.Direction.RIGHT);
				else
					spheres.navigateTo(new Menu(spheres, user),
							SlidingPanel.Direction.RIGHT);
				spheres.saveUser(user);
			}
		});
	}

	// ================_setters_and_getters_===============
	public Spheres getSpheres() {
		return this.spheres;
	}

	public void setSpheres(Spheres spheres) {
		this.spheres = spheres;
	}

	public void setWhereAmI(String here) {
		titelLa.setText("<html><h3>" + here + "</h3></html>");
	}

	public void setHeaderVisibility(boolean bool) {
		header.setVisible(bool);
	}

	public void setBackBVisibility(boolean bool) {
		backB.setVisible(bool);
	}

}
