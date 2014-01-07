package Spheres;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.UIManager;

import Spheres.SlidingPanel.Direction;

public class Spheres extends WindowAdapter {

	private static final Logger LOGGER = Logger.getLogger(Spheres.class
			.getName());
	private SlidingPanel content;
	private GameView gView;
	private GameModel gModel;
	private GameControl gControl;
	private JFrame frame;

	public Spheres() {
		frame = new JFrame("SphereS");
		ViewLogin logView = new ViewLogin(this);

		content = new SlidingPanel();
		content.add(logView);

		frame.setLocation(350, 250);
		frame.setSize(500, 500);
		frame.getContentPane().add(content);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.addWindowListener(this);

	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			// Just print stacktrace here since it's an example.
			ex.printStackTrace();
		}
		new Spheres();

	}

	public void navigateTo(JComponent component) {
		content.add(component);
	}

	public void navigateTo(JComponent component, Direction dir) {
		content.add(component, dir);
	}

	public void startGame(int mode, User user) {
		gModel = new GameModel(mode, user);
		gView = new GameView(gModel);
		gControl = new GameControl(gView, gModel, this);
		content.addAnimationListener(gControl);
		navigateTo(gView);
	}

	// ================== Innere Klassen ========================
	public boolean createUser(String user, String pw) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
			byte[] result = digest.digest(pw.getBytes());
			String hashValue = new String(result);
			User benutzer = loadProperties(user);
			if (benutzer != null) {
				return false;
			}
			newUserFile(user, hashValue);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.log(Level.SEVERE, "MD5 Algorithmus nicht gefunden", e);
		}
		return true;
	}

	boolean authenticate(String user, String pw) {
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] result = digest.digest(pw.getBytes());
			String hashValue = new String(result);
			User benutzer = loadProperties(user);
			if (benutzer == null)
				return false;
			String passwordHash = benutzer.getPass();
			return hashValue.equals(passwordHash);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.log(Level.SEVERE, "MD5 Digester nicht gefunden");
		}
		return false;
	}

	private void newUserFile(String user, String pw) {
		FileOutputStream fos = null;
		File userData = new File(user);
		ObjectOutputStream oos = null;
		User benutzer = new User(user, pw);
		try {
			fos = new FileOutputStream(userData);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(benutzer);

		} catch (IOException e) {
			LOGGER.log(Level.SEVERE,
					"Properties konnten nicht gespeichert werden", e);
		} finally {
			if (oos != null)
				try {
					oos.close();
				} catch (IOException e) {
					LOGGER.log(Level.WARNING,
							"Properties konnten nicht geschlossen werden", e);
				}
		}
	}

	public User loadProperties(String username) {
		User benutzer = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		File userName = new File(username);
		if (userName.exists()) {
			try {
				fis = new FileInputStream(userName);
				ois = new ObjectInputStream(fis);
				benutzer = (User) ois.readObject();
				return benutzer;
			} catch (FileNotFoundException e) {
				LOGGER.log(Level.WARNING,
						" File username konnte nicht gefunden", e);
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE,
						"user.properties konnten nicht geladen werden", e);
			} catch (ClassNotFoundException e) {
				LOGGER.log(Level.SEVERE,
						"Class User konnte nicht geladen werden", e);
			} finally {
				if (ois != null)
					try {
						ois.close();
					} catch (IOException e) {
						LOGGER.log(
								Level.WARNING,
								"ObjectInputStream konnten nicht geschlossen werden",
								e);
					}
			}
		}
		return null;
	}

	public void exit() {
		frame.setVisible(false);
		frame.dispose();
		if (gModel != null) {
			User user = gModel.getUser();
			if (user != null) {
				saveUser(user);
				saveHighscore(user);
			}
		}
	}

	private void saveHighscore(User user) {
		SortedSet<HighscoreEntry> timeHighscore = loadTimeHighscore();
		SortedSet<HighscoreEntry> drawHighscore = loadDrawHighscore();
		int[] drawHigh = user.getDrawHigh();
		for (int i : drawHigh) {
			drawHighscore.add(new HighscoreEntry(i, user.getName()));
		}

		int[] timeHigh = user.getTimeHigh();
		for (int i : timeHigh) {
			timeHighscore.add(new HighscoreEntry(i, user.getName()));
		}

		// alle ausser den besten 10 entfernen
		while (timeHighscore.size() > 10)
			timeHighscore.remove(timeHighscore.last());
		while (drawHighscore.size() > 10)
			drawHighscore.remove(drawHighscore.last());

		String timeString = convertToString(timeHighscore);
		String drawString = convertToString(drawHighscore);
		File file = new File("highscore.properties");
		Properties properties = new Properties();
		properties.put("timehighscore", timeString);
		properties.put("drawhighscore", drawString);
		try {
			FileOutputStream out = new FileOutputStream(file);
			properties.store(out, null);
			out.close();
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Konnte Highscore Datei nicht speichern",
					e);
		}

	}

	private String convertToString(SortedSet<HighscoreEntry> timeHighscore) {
		StringBuilder builder = new StringBuilder();
		for (HighscoreEntry highscoreEntry : timeHighscore) {
			builder.append(highscoreEntry.getPoints());
			builder.append("|");
			builder.append(highscoreEntry.getUser());
			builder.append(";");
		}
		if (builder.length() > 0)
			builder.setLength(builder.length() - 1);
		return builder.toString();
	}

	public SortedSet<HighscoreEntry> loadTimeHighscore() {
		File file = new File("highscore.properties");
		Properties properties = new Properties();
		if (file.exists()) {
			try {
				FileInputStream in = new FileInputStream(file);
				properties.load(in);
				in.close();
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, "Konnte Highscore Datei nicht laden",
						e);
			}
		}
		SortedSet<HighscoreEntry> highscore = new TreeSet<>();
		Object object = properties.get("timehighscore");
		if (object instanceof String) {
			String highscores = (String) object;
			String[] entries = highscores.split(";");
			for (String entry : entries) {
				String[] result = entry.split("\\|");
				HighscoreEntry highscoreEntry = new HighscoreEntry(
						Integer.parseInt(result[0]), result[1]);
				highscore.add(highscoreEntry);
			}
		}
		return highscore;
	}

	public SortedSet<HighscoreEntry> loadDrawHighscore() {
		File file = new File("highscore.properties");
		Properties properties = new Properties();
		if (file.exists()) {
			try {
				FileInputStream in = new FileInputStream(file);
				properties.load(in);
				in.close();
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, "Konnte Highscore Datei nicht laden",
						e);
			}
		}
		SortedSet<HighscoreEntry> highscore = new TreeSet<>();
		Object object = properties.get("drawhighscore");
		if (object instanceof String) {
			String highscores = (String) object;
			String[] entries = highscores.split(";");
			for (String entry : entries) {
				String[] result = entry.split("\\|");
				HighscoreEntry highscoreEntry = new HighscoreEntry(
						Integer.parseInt(result[0]), result[1]);
				highscore.add(highscoreEntry);
			}
		}
		return highscore;
	}

	public void saveUser(User user) {
		ObjectOutputStream oos = null;
		FileOutputStream fos = null;
		File userName = null;
		try {
			userName = new File(user.getName());
			fos = new FileOutputStream(userName);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(user);
		} catch (IOException e1) {
			LOGGER.log(Level.SEVERE, "User konnten nicht gespeichert werden",
					e1);
		} finally {
			if (oos != null)
				try {
					oos.close();
				} catch (IOException e1) {
					LOGGER.log(Level.WARNING,
							"User konnten nicht geschlossen werden", e1);
				}
		}
	}

	@Override
	public void windowClosing(WindowEvent event) {
		exit();
	}

	public JFrame getFrame() {
		return frame;
	}
}
