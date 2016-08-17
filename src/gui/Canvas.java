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
	private Image candlestick;

	public Canvas() {
		super();
		this.boardImage = loadImage("board.png");
		this.candlestick = loadImage("candlestick.png");
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(this.boardImage, 0, 0, 800, 760, null, null);
		g.drawImage(this.candlestick, 80, 60, null, null);
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
