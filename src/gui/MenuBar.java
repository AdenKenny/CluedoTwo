package gui;

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


	/**
	 * Constructor for our menu bar. Takes a frame input on which it will be
	 * displayed.
	 *
	 * @param frame The frame on which this menu bar will be added to.
	 */

	public MenuBar(Frame frame) {
		
		this.frame = frame;
		this.menuBar = new JMenuBar(); //Initialise bar.

		String path = "assets/images/icons/"; 

		Icon infoIcon = new ImageIcon(path + "infoIcon.png"); //Set icons to assets.
		Icon fileIcon = new ImageIcon(path + "fileIcon.png");
		Icon closeIcon = new ImageIcon(path + "closeIcon.png");
		Icon playIcon = new ImageIcon(path + "playIcon.png");
		Icon muteIcon = new ImageIcon(path + "muteIcon.png");
		Icon gameIcon = new ImageIcon(path + "gameIcon.png");
		Icon questionIcon = new ImageIcon(path + "questionIcon.png");
		Icon musicIcon = new ImageIcon(path + "musicIcon.png");

		JMenu gameMenu = new JMenu("Game"); // JMenus.
		gameMenu.setMnemonic(KeyEvent.VK_F1); //Sets the mnemonic for the keyboard shortcut.
		gameMenu.setIcon(gameIcon);
		gameMenu.setToolTipText("Create new game or exit current game - Alt + F1"); //Hover text.
		
		JMenu audioMenu = new JMenu("Audio");
		audioMenu.setMnemonic(KeyEvent.VK_F2);
		audioMenu.setIcon(musicIcon);
		audioMenu.setToolTipText("Play audio or unmute audio - Alt + F2");
		
		JMenu helpMenu = new JMenu("Help"); 
		helpMenu.setMnemonic(KeyEvent.VK_F3); 
		helpMenu.setIcon(questionIcon);
		helpMenu.setToolTipText("Get help with the game - Alt + F3");
		
		//JMenuItems.
		
		JMenuItem about = new JMenuItem("About - Alt+A", infoIcon); //The buttons on the menu.
		about.setActionCommand("about"); //The string that is passed along to the action listener.
		about.setMnemonic(KeyEvent.VK_A); //Sets the mnemonic for the keyboard shortcut.

		JMenuItem newGame = new JMenuItem("New game - Alt+N", fileIcon);
		newGame.setActionCommand("new game");
		newGame.setMnemonic(KeyEvent.VK_N);
		
		JMenuItem close = new JMenuItem("Close game - Alt+C", closeIcon);
		close.setActionCommand("close");
		close.setMnemonic(KeyEvent.VK_C);

		JMenuItem unmute = new JMenuItem("Play Audio - Alt+U", playIcon);
		unmute.setActionCommand("unmute");
		unmute.setMnemonic(KeyEvent.VK_M);

		JMenuItem mute = new JMenuItem("Mute Audio - Alt+M", muteIcon);
		mute.setActionCommand("mute");
		mute.setMnemonic(KeyEvent.VK_U);

		MenuItemListener menuItemListener = new MenuItemListener();

		about.addActionListener(menuItemListener); //Adds the custom listener to the buttons.
		newGame.addActionListener(menuItemListener);
		close.addActionListener(menuItemListener);
		unmute.addActionListener(menuItemListener);
		mute.addActionListener(menuItemListener);

		gameMenu.add(newGame); //Adds the menu items to the menus.
		gameMenu.add(close);
		helpMenu.add(about);
		audioMenu.add(unmute);
		audioMenu.add(mute);

		this.menuBar.add(gameMenu); //Add the menus to the menu bar.
		this.menuBar.add(audioMenu);
		this.menuBar.add(helpMenu);

	}

	/**
	 * Returns the JMenuBar to be added to the frame.
	 *
	 * @return The JMenuBar with everything added onto it.
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

			if(action.equals("about")) {
				MenuBar.this.frame.showMessage("A graphical Cluedo. Written by Aden Kenny and Simon Pope - 2016");
			}

			else if(action.equals("new game")) {
				MenuBar.this.frame.newGame();
			}

			else if(action.equals("close")) {
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
