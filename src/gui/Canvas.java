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

	public Canvas(Board board) {
		super();
		this.boardImage = loadImage("board.png");
		this.board = board;
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(this.boardImage, 0, 0, null, null);
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
