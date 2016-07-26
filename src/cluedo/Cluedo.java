package cluedo;

import java.util.HashSet;
import java.util.LinkedHashSet;
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

	private Set<Card> setOfRooms; //Used for creating the triplet.
	private Set<Card> setOfWeapons;
	private Set<Card> setOfCharacters;

	public Cluedo() {
		this.players = new HashSet<>();
		this.charNames = createCharStrings(); //Populate set with characters.

		this.setOfRooms = createRooms();
		this.setOfWeapons = createWeapons();
		this.setOfCharacters = createCharacters();

		this.murderInfo = doMurder(); //Create triplet of murder info.
		this.board = new Board();



		run();

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

		in.close();

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
	 * @param playerNumb - The number of the player to be setup.
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

		in.close();

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
	 * For purposes of selecting characters.
	 *
	 * @return A set containing the playable characters.
	 */

	private Set<String> createCharStrings() {
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

	/**
	 * Creates cards based on the characters and adds them to a set.
	 *
	 * @return A set containing cards of the playable characters.
	 */

	private Set<Card> createCharacters() {
		Set<Card> temp = new LinkedHashSet<>();

		temp.add(new Card("Miss Scarlett"));
		temp.add(new Card("Colonel Mustard"));
		temp.add(new Card("Mrs White"));
		temp.add(new Card("The Reverend Green"));
		temp.add(new Card("Mrs Peacock"));
		temp.add(new Card("Professor Plum"));

		return temp;
	}

	/**
	 * Creates cards based on the weapons and adds them to a set.
	 *
	 * @return A set containing cards of the murder weapons.
	 */

	private Set<Card> createWeapons() {
		HashSet<Card> temp = new HashSet<>();

		temp.add(new Card("Candlestick"));
		temp.add(new Card("Dagger"));
		temp.add(new Card("Lead Pipe"));
		temp.add(new Card("Revolver"));
		temp.add(new Card("Rope"));
		temp.add(new Card("Spanner"));

		return temp;
	}

	/**
	 * Creates cards based on the rooms and adds them to a set.
	 *
	 * @return A set containing cards of the rooms on the board.
	 */

	private Set<Card> createRooms() {
		HashSet<Card> temp = new HashSet<>();

		temp.add(new Card("Kitchen"));
		temp.add(new Card("Ballroom"));
		temp.add(new Card("Conservatory"));
		temp.add(new Card("Billiard Room"));
		temp.add(new Card("Library"));
		temp.add(new Card("Study"));
		temp.add(new Card("Hall"));
		temp.add(new Card("Lounge"));
		temp.add(new Card("Dining Room"));

		return temp;
	}

	/**
	 * Picks a random weapon, person, and room into a triplet.
	 * These are the murder details that have to be guessed.
	 *
	 * @return A triplet the murder details.
	 */

	private Triplet doMurder() {

		int randChar = (int) Math.round(Math.random() * this.setOfCharacters.size());
		Card[] arrOfCards = new Card[this.setOfCharacters.size()]; //Create new array.
		this.setOfCharacters.toArray(arrOfCards); //Put contents of set in new array.
		Card charCard = arrOfCards[randChar]; //Get card at random position.

		int randWeapon = (int) Math.round(Math.random() * this.setOfWeapons.size());
		Card[] arrOfWeapons = new Card[this.setOfWeapons.size()];
		this.setOfCharacters.toArray(arrOfWeapons);
		Card weaponCard = arrOfWeapons[randWeapon];

		int randRoom = (int) Math.round(Math.random() * this.setOfRooms.size());
		Card[] arrOfRooms = new Card[this.setOfRooms.size()];
		this.setOfRooms.toArray(arrOfRooms);
		Card roomCard = arrOfRooms[randRoom];

		return (new Triplet(charCard, weaponCard, roomCard)); //Return the new random triplet.
	}

	private void run() {
		this.board.draw();
	}

	public static void main(String[] args) {
		new Cluedo();
	}

}
