package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import cluedo.GameOfCluedo;

public class Mouse implements MouseListener {

	GameOfCluedo cluedo = null;

	public void addGame(GameOfCluedo cluedo) {
		this.cluedo = cluedo;
	}

	public void endGame() {
		this.cluedo = null;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (this.cluedo == null) {
			return;
		}
		int mouseX = e.getX();
		int mouseY = e.getY();
		if (mouseX >= 32 && mouseX <= 776 && mouseY >= 6 && mouseY <= 773) {
			this.cluedo.boardClicked((mouseX - 30) / 31, (int)((mouseY - 6) / 29.5) - 1);
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
