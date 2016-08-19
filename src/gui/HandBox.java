package gui;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Set;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import items.Card;

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

	/**
	 * Constructor for the HandBox. 
	 * 
	 * @param parent The parent of this box. I.e. the main frame.
	 * @param message The name of the player who's hand is being presented.
	 * @param setOfCards A set containing the cards in a player's hand.
	 */
	
	public HandBox(JFrame parent, String message, Set<Card> setOfCards) {

		super(parent, message, true);

		this.handCanvas = new HandCanvas(setOfCards); //Create a new hand canvas object.

		if(setOfCards.size() < 4) { //If the player has less than four cards in their hand we only need to display one row.
			setSize(new Dimension(335, 195));
		}

		else if(setOfCards.size() < 7) { //4 to 6 cards requires two rows.
			setSize(new Dimension(335, 370));
		}


		Dimension parentSize = parent.getSize(); //Set this to the centre of the parent box.
	    Point p = parent.getLocation();
	    Dimension thisSize = getSize();
	    setLocation(p.x + parentSize.width / 2 - thisSize.width / 2, p.y + parentSize.height / 2 - thisSize.height / 2);

		getContentPane().add(this.handCanvas); //Add to the parent.

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		setResizable(false); //Make sure we can't mess with the size.
		setVisible(true);
	}

}
