package Spheres;

//Bibliotheken
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Path2D;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;

public class GameControl implements SlidingPanel.AnimtationListener {

	// Datenfelder
	private GameView gView;
	private GameModel gModel;
	private GamePanel gPanel;
	private Path2D mousePath;
	private Deque<Ball> selectedBalls;
	private Joker activeJoker;
	private Animator animator;

	// Konstruktor
	public GameControl(GameView gViewArgs, GameModel gModelArgs) {
		gView = gViewArgs;
		gModel = gModelArgs;
		gPanel = gViewArgs.getGamePanel();

		// --------------------------------Action-Listerner_für_die_GameView
		gView.addExitListener(new ExitListener());
		gView.addShopListener(new ShopListener());
		gView.addMenuListener(new MenuListener());
		gModel.addGameListener(gView);

		gView.addBronsonListener(new BronsonListener());
		gView.addSeagalListener(new SeagalListener());
		gView.addNorrisListener(new NorrisListener());
		// --------------------------------Action-Listerner_für_dasGamePanel
		DragListener dragListener = new DragListener();
		gPanel.addMouseMotionListener(dragListener);
		gPanel.addMouseListener(dragListener);

		fillGrid();
		deliverValuesGView();
		selectedBalls = new ArrayDeque<>();
	}

	// ===========_Methoden_zum_steuern_=========
	public void deliverValuesGView() {
		// gView.setTimeDraftsLeftLa();
		gView.setCBB();
		gView.setCNB();
		gView.setSSB();
		gView.setUsername();
		gView.setPoints("0");
	}

	// ------ befüllt das Grid nach dem Spielstart mit Bällen ----
	public void fillGrid() {
		Ball[][] balls = gModel.getBalls();
		for (int i = 0; i < balls.length; i++) {
			Ball[] balls2 = balls[i];
			for (int j = 0; j < balls2.length; j++) {
				gModel.addBall(new Ball(i, j, gModel.getColorSet()
						.newRandomColor()));
			}
		}
	}

	// Innere Klassen für die ActionListener
	class ExitListener implements ActionListener {

		public void actionPerformed(ActionEvent exit) {
			System.exit(0);
		}

	}

	class ShopListener implements ActionListener {
		public void actionPerformed(ActionEvent shop) {

		}
	}

	class MenuListener implements ActionListener {
		public void actionPerformed(ActionEvent menu) {

		}
	}

	class BronsonListener implements ActionListener {
		public void actionPerformed(ActionEvent bronson) {
			User user = gModel.getUser();
			if (user.getCbAnz() > 0) {
				activeJoker = Joker.BRONSON;
				gModel.userJoker(activeJoker);
			}
		}
	}

	class NorrisListener implements ActionListener {
		public void actionPerformed(ActionEvent norris) {
			User user = gModel.getUser();
			if (user.getCnAnz() > 0) {
				Object source = norris.getSource();
				if (source instanceof JButton || user.getSsAnz() == 0) {
					((JButton) source).setEnabled(false);

				}
				gModel.userJoker(Joker.NORRIS);
				if (gModel.getGameMode() == 1)
					gModel.setDrawsLeft(gModel.getDrawsLeft() + 5);
				else
					gModel.setTimeLeft(gModel.getTimeLeft()
							+ TimeUnit.SECONDS.toMillis(5));
			}
		}
	}

	class SeagalListener implements ActionListener {
		public void actionPerformed(ActionEvent seagal) {
			User user = gModel.getUser();
			Object source = seagal.getSource();
			if (source instanceof JButton || user.getSsAnz() == 0) {
				((JButton) source).setEnabled(false);

			}
			if (user.getSsAnz() > 0) {
				activeJoker = Joker.SEAGAL;
				gModel.userJoker(activeJoker);
			}
		}
	}

	class DragListener extends MouseMotionAdapter implements MouseListener {
		private boolean formsRectangle;

		@Override
		public void mouseDragged(MouseEvent e) {

			if (mousePath != null) {

				Ball ball = selectBall(e.getPoint());
				if (ball != null) {
					Point center = ball.getCenter();
					mousePath.lineTo(center.getX(), center.getY());
				}

			}
			gPanel.setMousePath(mousePath);
			gPanel.repaint(); // TODO: draw bereich verkleinern
		}

		private Ball selectBall(Point p) {
			Ball ball = getBallAtCoordinate(p);
			Ball lastBall = selectedBalls.peek();
			if (ball == lastBall)
				return null;
			if (ball != null) {
				if (selectedBalls.isEmpty()) {
					ball.setChoosen(true);
					selectedBalls.push(ball);
					return ball;
				} else {

					if (selectedBalls.contains(ball)) {
						if (formsRectangle(ball) && isNeighbour(ball, lastBall)) {
							formsRectangle = true;
							ball.setChoosen(true);
							selectedBalls.push(ball);
							return ball;
						} else {
							// wenn der user zurück geht, wird deselektiert
							while (true) {
								Ball undoBall = selectedBalls.pop();
								if (undoBall == ball)
									return null;
								undoBall.setChoosen(false);
								mousePath = createMousePath();
							}
						}
					} else if (ball.getBallColor().equals(
							lastBall.getBallColor())
							&& isNeighbour(ball, lastBall)) {
						ball.setChoosen(true);
						selectedBalls.push(ball);
						return ball;
					}
				}
			}
			return null;
		}

		private boolean isNeighbour(Ball ball, Ball lastBall) {
			Point pos = ball.getPos();
			Point pos2 = lastBall.getPos();
			if (pos.x == pos2.x && Math.abs(pos.y - pos2.y) == 1)
				return true;
			if (pos.y == pos2.y && Math.abs(pos.x - pos2.x) == 1)
				return true;
			return false;
		}

		@Override
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {

			if (e.getButton() == MouseEvent.BUTTON1) {
				if (activeJoker != null) {
					Ball ball = getBallAtCoordinate(e.getPoint());
					if (ball != null)
						handleJoker(ball);
					return;
				}

				Ball ball = selectBall(e.getPoint());
				if (ball != null) {
					mousePath = new Path2D.Double();
					Point center = ball.getCenter();
					mousePath.moveTo(center.x, center.y);

				}
			}

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			mousePath = null;
			gPanel.setMousePath(null);
			if (formsRectangle) {
				gModel.deleteColor(selectedBalls.peek().getBallColor());
				gModel.subDraws();
			} else {
				if (selectedBalls.size() > 1) {
					for (Ball ball : selectedBalls) {
						ball.setChoosen(false);
						gModel.deleteBall(ball);
					}
					gModel.subDraws();
				} else if (!selectedBalls.isEmpty())
					selectedBalls.peek().setChoosen(false);
			}

			formsRectangle = false;
			selectedBalls.clear();
			gPanel.repaint();

		}

		public Ball getBallAtCoordinate(Point p) {
			Point ballPos = posToLogicalPos(p);
			if (ballPos != null) {
				Ball ball = gModel.getBall(ballPos.x, ballPos.y);
				// wenn der ball zu weit weg ist, war es kein treffer
				if (ball.contains(p))
					return ball;
			}
			return null;

		}

		private Point posToLogicalPos(Point displayPos) {
			// TODO entfernungen von aussen setzen
			Point logical = new Point((displayPos.x - 25) / 50,
					(displayPos.y - 25) / 50);
			if (logical.x > 5 || logical.y > 5 || logical.x < 0
					|| logical.y < 0)
				return null;
			return logical;
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}

	public Path2D createMousePath() {
		Iterator<Ball> it = selectedBalls.descendingIterator();
		Path2D mousePath = new Path2D.Double();
		while (it.hasNext()) {
			Ball next = it.next();
			Point center = next.getCenter();
			if (mousePath.getCurrentPoint() == null)
				mousePath.moveTo(center.x, center.y);
			else
				mousePath.lineTo(center.x, center.y);
		}
		return mousePath;
	}

	public void handleJoker(Ball ball) {
		switch (activeJoker) {
		case BRONSON:
			gModel.deleteBall(ball);
			break;
		case SEAGAL:
			gModel.deleteColor(ball.getBallColor());
			break;
		default:
			break;
		}
		activeJoker = null;
		gModel.subDraws();
	}

	public boolean formsRectangle(Ball ball) {
		if (selectedBalls.size() <= 2)
			return false;
		Iterator<Ball> iterator = selectedBalls.iterator();
		int count = 0;
		while (iterator.hasNext()) {
			// wenn der ball selektiert, aber nicht der letzte ball ist, ergibt
			// sich ein rechteck
			Ball next = iterator.next();
			if (next == ball && count < 2)
				return false;
			if (count == 2)
				return true;
			count++;
		}

		return selectedBalls.size() > 2 && !selectedBalls.peek().equals(ball);
	}

	@Override
	public void animationComplete() {
		animator = new Animator(gModel, gPanel);
		animator.start();
		
	}
}
