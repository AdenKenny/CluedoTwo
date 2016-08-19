package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import cluedo.GameOfCluedo;

/**
 * A class that handles mouse events.
 *
 * @author Aden Kenny and Simon.
 *
 */

public class Mouse implements MouseListener {

	GameOfCluedo cluedo = null;

	private static final int BOARD_LEFT = 30;
	private static final int BOARD_TOP = 6;
	private static final int BOARD_RIGHT = 774;
	private static final int BOARD_BOTTOM = 743;

	private static final int SQUARE_WIDTH = 31;
	private static final double SQUARE_HEIGHT = 29.5;

	public void addGame(GameOfCluedo cluedo) {
		this.cluedo = cluedo;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (this.cluedo == null) {
			return;
		}

		int mouseX = e.getX();
		int mouseY = e.getY();

		if (mouseX >= BOARD_LEFT && mouseX <= BOARD_RIGHT && mouseY >= BOARD_TOP && mouseY <= BOARD_BOTTOM) {
			this.cluedo.boardClicked((mouseX - BOARD_LEFT) / SQUARE_WIDTH, (int)((mouseY - BOARD_TOP) / SQUARE_HEIGHT) - 1);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//not used
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//not used
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//not used
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//not used
	}
}
