package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import cluedo.Board;
import location.Location;
import location.Room;

/**
 * A class representing the canvas on the board on which tokens (players and weapons)
 * are drawn.
 *
 * @author Aden Kenny and Simon Pope.
 *
 */


public class Canvas extends JPanel {

	private Board board = null;

	private Image boardImage;

	private static final int BOARD_LEFT = 30;
	private static final int BOARD_TOP = 6;

	private static final int SQUARE_WIDTH = 31;
	private static final double SQUARE_HEIGHT = 29.5;

	private static final int SQUARES_WIDTH = 24;
	private static final int SQUARES_HEIGHT = 25;

	private static final int BOARD_WIDTH = 800;
	private static final int BOARD_HEIGHT = 760;

	public Canvas() {
		super();
		this.boardImage = loadImage("board/board.png"); //Load board file.
	}

	public void addBoard(Board board) {
		this.board = board;
		this.repaint();
	}

	private static int xToPixels(int x) {
		return x * SQUARE_WIDTH + BOARD_LEFT;
	}

	private static int yToPixels(int y) {
		return (int) (y * SQUARE_HEIGHT) + BOARD_TOP;
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(this.boardImage, 0, 0, BOARD_WIDTH, BOARD_HEIGHT, null, null);

		if (this.board == null) {
			return;
		}

		for (int x = 0; x < SQUARES_HEIGHT; x++) {
			for (int y = 0; y < SQUARES_HEIGHT; y++) {
				Image img = this.board.characterAt(x, y);
				if (img != null) {
					g.drawImage(img, xToPixels(x), yToPixels(y), null, null);
				}
			}
		}

		for (Room room : this.board.getRooms().values()) {
			Point p = room.getDisplayCoords();
			int x = xToPixels((int)p.getX());
			int y = yToPixels((int)p.getY());
			Image[] images = room.getDisplay();
			for (Image img : images) {
				g.drawImage(img, x, y, null, null);
				x += img.getWidth(null);
			}
		}
	}

	public static Image loadImage(String filename) {
		try {
	        File file = new File("assets/images/" + filename);
	        Image image = ImageIO.read(file);
	        return image;
		}
		catch(IOException e) {
			System.out.println(e);
		}
		return null;
	}
}
