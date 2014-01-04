package Spheres;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class GameView extends JPanel implements GameListener {

	private GameModel gModel;
	private GamePanel gPanel;
	private JPanel southPa, eastPa, westPa;
	private JLabel pointsLa, nameLa, timeDrawsLeftLa, northLa;
	private JButton cbB, ssB, cnB, menuB, shopB, beendenB;
	private TitledBorder cbBrd, ssBrd, cnBrd, pointsBrd, nameBrd,
			timeDrawsLeftBrd;

	// Konstruktor
	public GameView(GameModel gModelArgs) {
		super();
		gModel = gModelArgs;
		gPanel = new GamePanel(gModel);
		gPanel.setBackground(Color.GRAY.brighter());
		cbBrd = BorderFactory.createTitledBorder("");
		cbBrd.setTitleJustification(TitledBorder.LEFT);
		ssBrd = BorderFactory.createTitledBorder("");
		ssBrd.setTitleJustification(TitledBorder.CENTER);
		cnBrd = BorderFactory.createTitledBorder("");
		cnBrd.setTitleJustification(TitledBorder.RIGHT);
		pointsBrd = BorderFactory.createTitledBorder("Punkte");
		nameBrd = BorderFactory.createTitledBorder("Name");
		timeDrawsLeftBrd = BorderFactory.createTitledBorder("Übrig");

		setLayout(new BorderLayout(5, 5));
		setBackground(Color.green.darker());
		southPa = createFooter();
		southPa.setPreferredSize(new Dimension(450, 30));
		add(southPa, BorderLayout.SOUTH);
		// --Das GamePanel einbinden
		add(gPanel, BorderLayout.CENTER);
		// ----------------------------------east-Panel_für_die_Joker
		eastPa = new JPanel();
		eastPa.setPreferredSize(new Dimension(75, 375));
		eastPa.setLayout(new GridLayout(7, 1, 10, 10));
		eastPa.setBackground(Color.gray.darker());
		eastPa.add(new JLabel("- Joker -"));
		// ................Bronson-Button
		cbB = new JButton("Bronson");
		cbB.setSize(new Dimension(100, 50));
		cbB.setBorder(cbBrd);
		cbB.setBackground(Color.red.brighter());
		eastPa.add(cbB);

		eastPa.add(new JLabel());
		// .................Seagal-button
		ssB = new JButton("Seagal");
		ssB.setPreferredSize(new Dimension(100, 50));
		ssB.setBorder(ssBrd);
		ssB.setBackground(Color.cyan);
		eastPa.add(ssB);
		eastPa.add(new JLabel());
		// .................Norris-button
		cnB = new JButton("Norris");
		cnB.setSize(new Dimension(100, 50));
		cnB.setBorder(cnBrd);
		cnB.setBackground(Color.yellow);
		eastPa.add(cnB);
		eastPa.add(new JLabel());
		add(eastPa, BorderLayout.EAST);

		// -------------------------------------north-Panel
		northLa = new JLabel("<html><h1>S P H E R E S</h1></html>");
		northLa.setHorizontalAlignment(JLabel.CENTER);
		northLa.setPreferredSize(new Dimension(450, 75));
		add(northLa, BorderLayout.NORTH);

		// -------------------------------------west-Panel
		westPa = new JPanel();
		westPa.setLayout(new GridLayout(7, 1, 10, 10));
		westPa.setBackground(Color.gray.darker());
		westPa.setPreferredSize(new Dimension(75, 375));

		westPa.add(new JLabel("-  Infos  -"));
		// ------------------------Name-Label
		nameLa = new JLabel();
		nameLa.setBorder(nameBrd);
		nameLa.setSize(new Dimension(80, 20));
		westPa.add(nameLa);
		westPa.add(new JLabel());
		// ------------------------Punktestand-Label
		pointsLa = new JLabel();
		pointsLa.setBorder(pointsBrd);
		westPa.add(pointsLa);
		westPa.add(new JLabel());
		// ------------------------Zeit / Züge-Label
		timeDrawsLeftLa = new JLabel();
		if(gModel.getGameMode()==1)
		 timeDrawsLeftLa.setText(Long.toString(gModel.getDrawsLeft()));
		else
			timeDrawsLeftLa.setText(SimpleDateFormat.getTimeInstance().format(gModel.getTimeLeft()));
		timeDrawsLeftLa.setBorder(timeDrawsLeftBrd);
		westPa.add(timeDrawsLeftLa);

		westPa.add(new JLabel());
		add(westPa, BorderLayout.WEST);

		repaint();
	}

	// ==================_footer_anlegen_=========
	public JPanel createFooter() {
		JPanel footer = new JPanel();
		footer.setLayout(new GridLayout(1, 4, 10, 10));
		footer.setPreferredSize(new Dimension(450, 40));
		add(footer, BorderLayout.SOUTH);
		beendenB = new JButton("EXIT");
		beendenB.setBorder(BorderFactory.createEtchedBorder(Color.LIGHT_GRAY,
				Color.black));
		beendenB.setSize(new Dimension(10, 20));
		beendenB.setAlignmentX(LEFT_ALIGNMENT);
		beendenB.setMnemonic(KeyEvent.VK_E);
		beendenB.setPreferredSize(new Dimension(100, 30));
		footer.add(beendenB);

		/*
		 * beendenB.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) {
		 * //saveUser(user); System.exit(0); } });
		 */

		shopB = new JButton("Shop");
		shopB.setBorder(BorderFactory.createEtchedBorder(Color.LIGHT_GRAY,
				Color.black));
		shopB.setSize(new Dimension(10, 20));
		shopB.setAlignmentX(CENTER_ALIGNMENT);
		shopB.setMnemonic(KeyEvent.VK_S);
		shopB.setPreferredSize(new Dimension(100, 30));
		footer.add(shopB);
		/*
		 * abmeldenB.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) {
		 * spheres.navigateTo(new ViewLogin(spheres),
		 * SlidingPanel.Direction.RIGHT); saveUser(user); } });
		 */

		menuB = new JButton("Menu");
		menuB.setBorder(BorderFactory.createEtchedBorder(Color.LIGHT_GRAY,
				Color.black));
		menuB.setSize(new Dimension(10, 20));
		menuB.setAlignmentX(LEFT_ALIGNMENT);
		menuB.setMnemonic(KeyEvent.VK_M);
		menuB.setPreferredSize(new Dimension(100, 30));
		footer.add(menuB);
		/*
		 * backB.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) {
		 * spheres.navigateTo(new Menu(spheres, user),
		 * SlidingPanel.Direction.RIGHT); //saveUser(user); } });
		 */
		return footer;
	}

	public void setUsername() {
		nameLa.setText(gModel.getUsername());
	}

	public void setPoints(String points) {
		pointsLa.setText(points);
	}

	public void setCBB() {
		cbBrd.setTitle(gModel.getCBCount());
	}

	public void setSSB() {
		ssBrd.setTitle(gModel.getSSCount());
	}

	public void setCNB() {
		cnBrd.setTitle(gModel.getCNCount());
	}

	public void setNameBrd(String txt) {
		nameBrd.setTitle(txt);
	}

	public void setPointsBrd(String txt) {
		nameBrd.setTitle(txt);
	}

	/*
	 * public void setPointsBrd() {
	 * pointsBrd.setTitle(gModel.getPointsString()); }
	 */

	public void setTimeDrawsLeftBrd() {
		timeDrawsLeftBrd.setTitle("Rest");
	}

	public void addExitListener(ActionListener exit) {
		beendenB.addActionListener(exit);
	}

	public void addShopListener(ActionListener shop) {
		shopB.addActionListener(shop);
	}

	public void addMenuListener(ActionListener menu) {
		menuB.addActionListener(menu);
	}

	public void addBronsonListener(ActionListener bronson) {
		cbB.addActionListener(bronson);
	}

	public void addNorrisListener(ActionListener norris) {
		cnB.addActionListener(norris);
	}

	public void addSeagalListener(ActionListener seagal) {
		ssB.addActionListener(seagal);
	}

	public GamePanel getGamePanel() {
		return gPanel;
	}

	@Override
	public void notify(GameChangeEvent event) {
		switch (event.getType()) {
		case DRAWS_CHANGED:
			if (gModel.getGameMode() == 1)
				timeDrawsLeftLa.setText(Long.toString(event.getNewValue()));
			break;
		case TIME_CHANGED:
			if (gModel.getGameMode() == 1)
				timeDrawsLeftLa.setText(SimpleDateFormat.getTimeInstance().format(new Date(event.getNewValue())));
			break;
		case POINTS_CHANGED:
			pointsLa.setText(Long.toString(event.getNewValue()));
			break;
		case BRONSON_CHANGED:
			cbBrd.setTitle(Long.toString(event.getNewValue()));
			break;
		case NORRIS_CHANGED:
			cnBrd.setTitle(Long.toString(event.getNewValue()));
			break;
		case SEAGAL_CHANGED:
			ssBrd.setTitle(Long.toString(event.getNewValue()));
			break;			
		default:
			break;
		}

	}
}
