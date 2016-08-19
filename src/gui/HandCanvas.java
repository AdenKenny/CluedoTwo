package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;

import items.Card;

public class HandCanvas extends JPanel {

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
	    for(Card card : this.setOfCards) {
	    	Image image = card.getImage();
	    	g.drawImage(image, x, y, null);

	    	x += image.getWidth(null);
	    }

	}

}
