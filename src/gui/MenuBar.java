package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


/**
 * A class representing the JMenuBar that is displayed at the top of the program.
 *
 * @author Aden Kenny and Simon Pope.
 */

public class MenuBar {

	JMenuBar menuBar; // The menu bar on which everything will be displayed on.

	public MenuBar() {
		this.menuBar = new JMenuBar(); // Initialise bar.

		JMenu fileMenu = new JMenu("File"); // JMenus.
		JMenu gameMenu = new JMenu("Game");

		JMenuItem close = new JMenuItem("Close game"); // The buttons on the menu.
		close.setActionCommand("Close");

		JMenuItem newGame = new JMenuItem("New game");
		newGame.setActionCommand("New Game");

		JMenuItem about = new JMenuItem("About");
		about.setActionCommand("About");

		MenuItemListener menuItemListener = new MenuItemListener();

	}

	/**
	 * Returns the JMenuBar to be added to the frame.
	 *
	 * @return The completed
	 */

	public JMenuBar getBar() {
		return this.menuBar;
	}

	/**
	 * Inner class representing a custom action listener.
	 *
	 * @author Aden Kenny and Simon Pope.
	 *
	 */

	class MenuItemListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}
	}
}
