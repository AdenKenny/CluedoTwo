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
	
	//Constants
	
	private static final int BOARD_TOP = 55;
	private static final int BOARD_BOTTOM = (int) (Frame.NUM_SQUARES_VERTICAL * Frame.SQUARE_HEIGHT) + BOARD_TOP;

	GameOfCluedo cluedo = null;

	public void addGame(GameOfCluedo cluedo) {
		this.cluedo = cluedo;
	}

	public void endGame() {
		this.cluedo = null;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//not used
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (this.cluedo == null) {
			return;
		}

		int mouseX = e.getX();
		int mouseY = e.getY();

		//Get the position on the visual board in squares.
		if (mouseX >= Frame.BOARD_LEFT && mouseX <= Frame.BOARD_RIGHT && mouseY >= BOARD_TOP && mouseY <= BOARD_BOTTOM) {
			this.cluedo.boardClicked((int)((mouseX - Frame.BOARD_LEFT) / Frame.SQUARE_WIDTH), (int)((mouseY - BOARD_TOP) / Frame.SQUARE_HEIGHT));
		}
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
