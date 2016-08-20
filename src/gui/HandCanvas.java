package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Set;

import javax.swing.JPanel;

import items.Card;

/**
 * A class representing a canvas on which the player's hand will be drawn.
 * 
 * @Author Aden Kenny and Simon Pope.
 */

public class HandCanvas extends JPanel {

	private static final int CARD_WIDTH = 110; //Constants of image size.
	private static final int CARD_HEIGHT = 170;

	private Set<Card> setOfCards; //Set in which the player's hand is stored.
	
	public HandCanvas(Set<Card> setOfCards) {
		super();
		this.setOfCards = setOfCards;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		int i = 0;
		
		for (Card card : this.setOfCards) { //Draws the cards on the canvas.
			
			int y = (i / 3) * CARD_HEIGHT;
			int x = (i % 3) * CARD_WIDTH;

			Image image = card.getImage();
			g.drawImage(image, x, y, CARD_WIDTH, CARD_HEIGHT, null);

			x += CARD_WIDTH;
			i++;
		}

	}

}
