package Spheres;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowClosingAdapter extends WindowAdapter {
	private boolean exitSystem;

	public WindowClosingAdapter(boolean exitSystem) {
		this.exitSystem = exitSystem;
	}

	public WindowClosingAdapter() {
		this(false);
	}

	public void windowClosing(WindowEvent event) {
		event.getWindow().setVisible(false);
		event.getWindow().dispose();
		if (exitSystem)
			System.exit(0);
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
	}
}
