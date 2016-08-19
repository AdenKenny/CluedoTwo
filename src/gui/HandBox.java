package gui;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A class that shows the player their hand.
 * This uses a JDialog popup into which a JPanel is added. The cards are
 * then displayed on the JPanel.
 *
 * @author Aden Kenny and Simon Pope.
 */

public class HandBox extends JDialog{

	private JPanel panel;

	public HandBox(JFrame parent, String message) {

		super(parent, message, true);

		this.panel = new JPanel();



		setSize(new Dimension(500, 500));

		Dimension parentSize = parent.getSize();
	    Point p = parent.getLocation();
	    Dimension thisSize = getSize();
	    setLocation(p.x + parentSize.width / 2 - thisSize.width / 2, p.y + parentSize.height / 2 - thisSize.height / 2);

		getContentPane().add(this.panel);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		setVisible(true);
	}

}
