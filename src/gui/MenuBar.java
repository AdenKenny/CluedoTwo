package gui;

import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


/**
 * A class representing the JMenuBar that is displayed at the top of the program.
 *
 * @author Aden Kenny and Simon Pope.
 */

public class MenuBar {

	Frame frame;
	JMenuBar menuBar; // The menu bar on which everything will be displayed on.

	public MenuBar(Frame frame) {

		this.frame = frame;

		this.menuBar = new JMenuBar(); // Initialise bar.

		JMenu fileMenu = new JMenu("File"); // JMenus.
		JMenu gameMenu = new JMenu("Game");

		JMenuItem close = new JMenuItem("Close game"); // The buttons on the menu.
		close.setActionCommand("Close"); //The string that's passed along

		JMenuItem newGame = new JMenuItem("New game");
		newGame.setActionCommand("New Game");

		JMenuItem about = new JMenuItem("About");
		about.setActionCommand("About");

		MenuItemListener menuItemListener = new MenuItemListener();

		about.addActionListener(menuItemListener);
		newGame.addActionListener(menuItemListener);
		close.addActionListener(menuItemListener);

		gameMenu.add(newGame);
		gameMenu.add(close);
		fileMenu.add(about);

		this.menuBar.add(fileMenu);
		this.menuBar.add(gameMenu);

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

		/**
		 * Takes the button and calls the function that equates to the menu buttons.
		 */

		@Override
		public void actionPerformed(ActionEvent e) {
			String action = e.getActionCommand();

			if(action.equals("About")) {
				MenuBar.this.frame.showMessage("A graphical Cluedo. Written by Aden Kenny and Simon Pope - 2016");
			}

			else if(action.equals("New Game")) {
				MenuBar.this.frame.newGame();
			}

			else if(action.equals("Close")) {
				System.exit(1);
			}
		}
	}
}
