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
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JComponent;
import javax.swing.JFrame;

import Spheres.SlidingPanel.Direction;

public class Spheres extends WindowAdapter {

	private static final Logger LOGGER = Logger.getLogger(Spheres.class.getName());
	private SlidingPanel content;
	private GameView gView;
	private GameModel gModel;
	private GameControl gControl;
	private JFrame frame;

	public Spheres() {
		ViewLogin logView = new ViewLogin(this);

		content = new SlidingPanel();
		content.add(logView);

		frame = new JFrame("SphereS");
		frame.setLocation(350, 250);
		frame.setSize(500, 500);
		frame.getContentPane().add(content);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.addWindowListener(this);
	}

	public static void main(String[] args) {
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
		User user = gModel.getUser();
		if(user!=null)
			saveUser(user);
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
}
