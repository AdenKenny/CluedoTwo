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

public class Canvas extends JPanel {

	private Board board;
	
	private Image boardImage;


	public Canvas(Board board) {
		super();
		this.boardImage = loadImage("board.png");
		this.board = board;
	}
	
	private static int xToPixels(int x) {
		return x * 31 + 30;
	}
	
	private static int yToPixels(int y) {
		return (int)(y * 29.5) + 6;
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(this.boardImage, 0, 0, 800, 760, null, null);
		for (int x = 0; x < 24; x++) {
			for (int y = 0; y < 25; y++) {
				Image img = board.characterAt(x, y);
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
	        File file = new File("images/" + filename);
	        Image image = ImageIO.read(file);
	        return image;
		}
		catch(IOException e) {
			System.out.println(e);
		}
		return null;
	}
}
