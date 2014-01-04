package Spheres;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Shop extends View {

	private JPanel center;
	private JButton cbBuyB, ssBuyB, cnBuyB;
	private JLabel cbPriceLa, ssPriceLa, cnPriceLa, cbCountLa, ssCountLa,
			cnCountLa, cbNameLa, ssNameLa, cnNameLa, fbLa;
	private User user;
	private Integer cbPrice, cbCount, cnCount, ssPrice, ssCount, cnPrice,
			points;
	public Shop(Spheres spheresArgs, User konUser) {
		this(spheresArgs,konUser,null);
	}

	public Shop(Spheres spheresArgs, User konUser, JComponent backAddress) {
		super(spheresArgs, konUser, backAddress);
		super.setWhereAmI("Shop");
		super.setBackBVisibility(true);
		this.user=konUser;

		// zum vorführen Werte insetzen
		cbPrice = 500;
		ssPrice = 2000;
		cnPrice = 1000;
		cbCount = user.getCbAnz();
		ssCount = user.getSsAnz();
		cnCount = user.getCnAnz();
		points = user.getPoints();

		center = new JPanel();
		center.setLayout(new GridLayout(5, 3, 1, 5));
		center.setBorder(BorderFactory.createTitledBorder("Guthaben: "
				+ points.toString()));
		add(center, BorderLayout.CENTER);

		// ------------ centerPanel befüllen
		cbNameLa = new JLabel("<html><h3>Charles Bronson</h3></html>");
		cbNameLa.setHorizontalAlignment(JLabel.CENTER);
		center.add(cbNameLa);
		ssNameLa = new JLabel("<html><h3>Steven Seagal</h3></html>");
		ssNameLa.setHorizontalAlignment(JLabel.CENTER);
		center.add(ssNameLa);

		cnNameLa = new JLabel("<html><h3>Chuck Norris</h3></html>");
		cnNameLa.setHorizontalAlignment(JLabel.CENTER);
		center.add(cnNameLa);

		cbPriceLa = new JLabel(cbPrice.toString());
		cbPriceLa.setBorder(BorderFactory.createTitledBorder("Kosten"));
		cbPriceLa.setHorizontalAlignment(JLabel.CENTER);
		center.add(cbPriceLa);
		ssPriceLa = new JLabel(ssPrice.toString());
		ssPriceLa.setBorder(BorderFactory.createTitledBorder("Kosten"));
		ssPriceLa.setHorizontalAlignment(JLabel.CENTER);
		center.add(ssPriceLa);
		cnPriceLa = new JLabel(cnPrice.toString());
		cnPriceLa.setBorder(BorderFactory.createTitledBorder("Kosten"));
		cnPriceLa.setHorizontalAlignment(JLabel.CENTER);
		center.add(cnPriceLa);

		cbCountLa = new JLabel("<html><h3>" + cbCount.toString()
				+ "</h3></html>");
		cbCountLa.setBorder(BorderFactory.createTitledBorder("Am Halfter"));
		cbCountLa.setHorizontalAlignment(JLabel.CENTER);
		center.add(cbCountLa);

		ssCountLa = new JLabel("<html><h3>" + ssCount.toString()
				+ "</h3></html>");
		ssCountLa.setBorder(BorderFactory.createTitledBorder("In der dunklen Tasche"));
		ssCountLa.setHorizontalAlignment(JLabel.CENTER);
		center.add(ssCountLa);

		cnCountLa = new JLabel("<html><h3>" + cnCount.toString()
				+ "</h3></html>");
		cnCountLa.setBorder(BorderFactory.createTitledBorder("schuldige Gefallen"));
		cnCountLa.setHorizontalAlignment(JLabel.CENTER);
		center.add(cnCountLa);

		cbBuyB = new JButton("her damit");
		cbBuyB.setName("cbBuyB");
		center.add(cbBuyB);
		ssBuyB = new JButton("haben will");
		ssBuyB.setName("ssBuyB");
		center.add(ssBuyB);
		cnBuyB = new JButton("MEHR !");
		cnBuyB.setName("cnBuyB");
		center.add(cnBuyB);

		fbLa = new JLabel();
		fbLa.setHorizontalAlignment(JLabel.CENTER);
		center.add(new JLabel());
		center.add(fbLa);

		cbBuyB.addActionListener(new ActionListener() {
			// Annonyme Listenerklasse für den Loginbutton
			@Override 
			public void actionPerformed(ActionEvent e) {
				if (points - cbPrice >= 0) {
					user.addCB(10);
					cbCountLa.setText(user.getCbAnzS());
					user.subPoints(cbPrice);
					center.setBorder(BorderFactory
							.createTitledBorder("Guthaben: "
									+ user.getPoints()));
					fbLa.setForeground(Color.GREEN);
					fbLa.setText("Charles Bronson gekauft");
				}
				else{
					fbLa.setForeground(Color.RED);
					fbLa.setText("Nicht genügend Punkte !");
				}
			}
		});
		
		ssBuyB.addActionListener(new ActionListener() {
			// Annonyme Listenerklasse für den Loginbutton
			@Override
			public void actionPerformed(ActionEvent e) {
				if (user.getPoints() - ssPrice >= 0) {
					user.addSS(5);
					ssCountLa.setText(user.getSsAnzS());
					user.subPoints(ssPrice);;
					center.setBorder(BorderFactory
							.createTitledBorder("Guthaben: "
									+ user.getPoints()));
					fbLa.setForeground(Color.GREEN);
					fbLa.setText("Steven Seagal gekauft");;
				}
				else{
					fbLa.setForeground(Color.RED);
					fbLa.setText("Nicht genügend Punkte !");
				}
			}
		});
		
		cnBuyB.addActionListener(new ActionListener() {
			// Annonyme Listenerklasse für den Loginbutton
			@Override
			public void actionPerformed(ActionEvent e) {
				if (user.getPoints() - cnPrice >= 0) {
					user.addCN(5);
					cnCountLa.setText(user.getCnAnzS());
					user.subPoints(cnPrice);
					center.setBorder(BorderFactory
							.createTitledBorder("Guthaben: "
									+ user.getPoints()));
					fbLa.setForeground(Color.green);
					fbLa.setText("Chuck Norris gekauft");;
				}
				else{
					fbLa.setForeground(Color.RED);
					fbLa.setText("Nicht genügend Punkte !");
				}
			}
		});
	}
}
