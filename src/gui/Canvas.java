package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import cluedo.Board;

public class Canvas extends JPanel {

	private Board board;
	
	private Image boardImage;

	public Canvas() {
		super();
		this.boardImage = loadImage("board.png");
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(this.boardImage, 0, 0, 800, 760, null, null);
		for (int x = 0; x < 24; x++) {
			for (int y = 0; y < 25; y++) {
				
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
