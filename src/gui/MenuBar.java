package gui;

import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


/**
 * A class representing the JMenuBar that is displayed at the top of the program.
 *
 * @author Aden Kenny and Simon Pope.
 */

public class MenuBar extends JFrame {

	Frame frame; //The base frame on which the game is displayed on.
	JMenuBar menuBar; // The menu bar on which everything will be displayed on.

	Icon aboutIcon;
	Icon fileIcon;
	Icon closeIcon;
	Icon playIcon;
	Icon muteIcon;

	/**
	 * Constructor for our menu bar. Takes a frame input on which it will be
	 * displayed.
	 *
	 * @param frame The frame on which this menu bar will be added to.
	 */

	public MenuBar(Frame frame) {

		this.frame = frame;
		this.menuBar = new JMenuBar(); // Initialise bar.

		this.aboutIcon = new ImageIcon("images/questionMark.png"); //Set icons to assets.
		this.fileIcon = new ImageIcon("images/fileIcon.png");
		this.closeIcon = new ImageIcon("images/closeIcon.png");
		this.playIcon = new ImageIcon("images/playIcon.png");
		this.muteIcon = new ImageIcon("images/muteIcon.png");

		JMenu fileMenu = new JMenu("File"); // JMenus.
		fileMenu.setMnemonic(KeyEvent.VK_F1); //Sets the mnemonic for the keyboard shortcut.

		JMenu gameMenu = new JMenu("Game");
		gameMenu.setMnemonic(KeyEvent.VK_F2);

		JMenu audioMenu = new JMenu("Audio");
		audioMenu.setMnemonic(KeyEvent.VK_F3);

		JMenuItem about = new JMenuItem("About - Alt+A", this.aboutIcon); //The buttons on the menu.
		about.setActionCommand("About"); //The string that is passed along.
		about.setMnemonic(KeyEvent.VK_A); //Sets the mnemonic for the keyboard shortcut.

		JMenuItem newGame = new JMenuItem("New game - Alt+N", this.fileIcon);
		newGame.setActionCommand("New Game");
		newGame.setMnemonic(KeyEvent.VK_N); //Sets the mnemonic for the keyboard shortcut.

		JMenuItem close = new JMenuItem("Close game - Alt+C", this.closeIcon);
		close.setActionCommand("Close");
		close.setMnemonic(KeyEvent.VK_C);

		JMenuItem unmute = new JMenuItem("Unmute Audio - Alt+U", this.playIcon);
		unmute.setActionCommand("unmute");
		unmute.setMnemonic(KeyEvent.VK_M);

		JMenuItem mute = new JMenuItem("Mute Audio - Alt+M", this.muteIcon);
		mute.setActionCommand("mute");
		mute.setMnemonic(KeyEvent.VK_U);

		MenuItemListener menuItemListener = new MenuItemListener();

		about.addActionListener(menuItemListener); //Adds the custom listener to the buttons.
		newGame.addActionListener(menuItemListener);
		close.addActionListener(menuItemListener);
		mute.addActionListener(menuItemListener);
		unmute.addActionListener(menuItemListener);

		gameMenu.add(newGame); //Adds the menu items to the menus.
		gameMenu.add(close);
		fileMenu.add(about);
		audioMenu.add(mute);
		audioMenu.add(unmute);

		this.menuBar.add(fileMenu); //Adds the menus to the menu bar.
		this.menuBar.add(gameMenu);
		this.menuBar.add(audioMenu);

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

			else if(action.equals("mute")) {
				MenuBar.this.frame.muteAudio();
			}

			else if(action.equals("unmute")) {
				MenuBar.this.frame.unmuteAudio();
			}
		}
	}
}
