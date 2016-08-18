package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Canvas extends JPanel {

	private Image boardImage;
	
	private Image candlestick;
	private Image revolver;
	private Image dagger;
	private Image pipe;
	private Image spanner;
	private Image rope;

	public Canvas() {
		super();
		this.boardImage = loadImage("board.png");
		this.candlestick = loadImage("candlestick.png");
		this.revolver = loadImage("revolver.png");
		this.dagger = loadImage("dagger.png");
		this.pipe = loadImage("pipe.png");
		this.spanner = loadImage("spanner.png");
		this.rope = loadImage("rope.png");
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(this.boardImage, 0, 0, 800, 760, null, null);
		g.drawImage(this.candlestick, 80, 60, null, null);
		g.drawImage(this.revolver, 140, 60, null, null);
		g.fillOval(130, 120, 30, 30);
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
