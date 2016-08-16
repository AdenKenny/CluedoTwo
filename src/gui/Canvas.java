package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import cluedo.Board;

public class Canvas extends JPanel {

	private Image boardImage;

	private Board board;

	private int x = -2;
	private int y = -2;

	public Canvas(Board board) {
		super();
		this.boardImage = loadImage("board.png");
		this.board = board;
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(this.boardImage, 0, 0, 800, 760, null, null);
		g.drawRect(x * 31 + 30, (int)(y * 29.5) + 6, 31, 30);
	}

	public void x(int x) {
		this.x = x;
	}

	public void y(int y) {
		this.y = y;
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
