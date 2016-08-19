package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;

import items.Card;


public class HandCanvas extends JPanel {

	private static final int CARD_WIDTH = 110;
	private static final int CARD_HEIGHT = 170;

	private Set<Card> setOfCards;

	public HandCanvas(Set<Card> setOfCards) {
		super();
		this.setOfCards = setOfCards;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		int x = 0;
		int y = 0;
		int i = 0;

		for (Card card : this.setOfCards) {

			y = (i / 3) * CARD_HEIGHT;
			x = (i % 3) * CARD_WIDTH;

			Image image = card.getImage();
			g.drawImage(image, x, y, CARD_WIDTH, CARD_HEIGHT, null);

			x += CARD_WIDTH;
			i++;
		}

	}

}
