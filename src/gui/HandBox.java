package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import items.Card;
import location.Room;

/**
 * A class that shows the player their hand.
 * This uses a JDialog popup into which a JPanel is added. The cards are
 * then displayed on the JPanel.
 *
 * @author Aden Kenny and Simon Pope.
 */

public class HandBox extends JDialog {

	private JPanel panel;
	private HandCanvas handCanvas; //Smaller canvas on which the hand is displayed.

	public HandBox(JFrame parent, String message, Set<Card> setOfCards) {

		super(parent, message, true);

		this.handCanvas = new HandCanvas(setOfCards);

		if(setOfCards.size() < 4) {
			setSize(new Dimension(335, 195));
		}

		else if(setOfCards.size() < 7){
			setSize(new Dimension(335, 370));
		}


		Dimension parentSize = parent.getSize();
	    Point p = parent.getLocation();
	    Dimension thisSize = getSize();
	    setLocation(p.x + parentSize.width / 2 - thisSize.width / 2, p.y + parentSize.height / 2 - thisSize.height / 2);

		getContentPane().add(this.handCanvas);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		setResizable(false);
		setVisible(true);
	}

}
