package cluedo;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import util.Board;
import util.Token;
import util.Triplet;

/**
 * A class containing the main game logic including setting up the game and then
 * playing the game.
 * 
 * @author Aden Kenny and Simon Pope.
 */

public class Cluedo {

	private Triplet murderInfo; // Triplet of the actual murder details.
	private Board board; // The game board.
	private Set<Player> players; //Set containing the players in the game.
	private Set<String> charNames; //Set containing the names of the characters in the game.

	public Cluedo() {
		this.players = new HashSet<>();
		this.charNames = createCharacters(); //Populate set with characters.

		Scanner in = new Scanner(System.in);
		int numbPlayers = 0;

		

		while(numbPlayers == 0) { //Make sure we do eventually get a valid number of players.
			
			System.out.println("Enter the number of human players: ");

			String tmp = in.next();
			
			try {
				numbPlayers = Integer.parseInt(tmp); //Try to parse to int.
			}

			catch (NumberFormatException e) { // If input is not convertable to an int. Invalid input.
				System.out.println("This is not a valid number of players");
			}
		}

		for (int i = 0; i < numbPlayers; i++) { // Loop through number of players setting them up.

			this.players.add(setupPlayer(i)); //Add the new player to the set of players.
		}

		for (Player p : this.players) { // Testing purposes.
			System.out.println(p.getToken().getName());
		}
	}

	/**
	 * Sets up a human player and adds it to the set of players to be passed to the board when the
	 * game is setup.
	 * 
	 * @param playerNumb
	 *            - The number of the player to be setup.
	 * @return newPlayer - A setup player that will be added to the set of players in the game.
	 */

	//TODO Base starting location off of unique position for each token.
	public Player setupPlayer(int playerNumb) { // Does this need to take an arg?

		Scanner in = new Scanner(System.in);

		System.out.println("Enter your username:");
		String username = in.nextLine();

		for(Player p : this.players) { //Make sure the chosen username is unique.
			if(p.getUsername().equals(username)) {
				System.out.println("This username has already been taken");
				setupPlayer(playerNumb); //Try again.
			}
		}
		
		System.out.println("Which character would you like to be?");
		String charName = in.nextLine();

		if (!this.charNames.contains(charName)) { //Invalid character, either doesn't exist 
												  //or already taken.
			System.out.println("This is not a valid character");
			setupPlayer(playerNumb); //Recall setup function, could just ask for character again.
		}

		this.charNames.remove(charName); // Remove this character as a pickable character.

		Token token = new Token(charName, null); // TODO Change location to a real location.
		return (new Player(username, token)); //Return the new character.
		
	}

	/**
	 * Gets a random number between 1 and 6 representing a dice roll for moving around the board.
	 *
	 * @return An int between 1 and 6.
	 */

	public int rollDice() {
		return (int) (Math.random() * 6 + 1);
	}
	
	/**
	 * Puts the list of playable characters in a set and returns them.
	 * 
	 * @return A set containing the playable characters.
	 */
	
	private Set<String> createCharacters() {
		HashSet<String> temp = new HashSet<>(); // This could be another type of set to keep
												// the ordering of characters.
		temp.add("Miss Scarlett");
		temp.add("Professor Plum");
		temp.add("Mrs Peacock");
		temp.add("Reverend Green");
		temp.add("Colonel Mustard");
		temp.add("Mrs White");

		return temp;
	}

	public static void main(String[] args) {
		new Cluedo();
	}

}
