package cluedo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import gui.Canvas;
import gui.Frame;
import items.Card;
import items.Token;
import location.Location;
import location.Room;
import util.Pair;
import util.Triplet;


/**
 * A class containing the main game logic including setting up the game and then playing the game.
 *
 * @author Aden Kenny and Simon Pope.
 */

public class Cluedo {

	private Triplet murderInfo; // Triplet of the actual murder details.
	private Board board; // The game board.
	private List<Player> players; // List containing the players in the game.
	private Set<String> charNames; // Set containing the names of the characters
	// in the game.

	private Set<Card> setOfRooms; // Used for creating the triplet.
	private Set<Card> setOfWeapons;
	private Set<Card> setOfCharacters;

	private Set<Card> allCards; // All cards, used for dealing.
	private Set<Token> allTokens; // The character and weapon tokens

	private Canvas canvas;

	private boolean gameOver;

	public Cluedo() {
		this.players = new ArrayList<>();
		this.allCards = new HashSet<>();
		createCharStrings(); // Populate set with characters.

		createRooms();
		createWeapons();
		createCharacters();

		this.board = new Board();

		this.canvas = new Frame(board).getCanvas();

		tokensSetup();

		doMurder(); // Create triplet of murder info.

		setupPlayers();

		Player highest = doStartRolls(this.players); // Get the player with
		// the highest roll.

		this.players.remove(highest);
		this.players.add(0, highest);

		putCards();
		dealHands();

		runGame();

	}

	/**
	 * Setup the character and weapons tokens.
	 */
	private void tokensSetup() {
		this.allTokens = new HashSet<>();

		// put characters in starting locations
		this.allTokens.add(new Token("Miss Scarlett", this.board.getSquare(7, 24), true, "MS"));
		this.allTokens.add(new Token("Professor Plum", this.board.getSquare(23, 19), true, "PP"));
		this.allTokens.add(new Token("Mrs Peacock", this.board.getSquare(23, 6), true, "MP"));
		this.allTokens.add(new Token("Reverend Green", this.board.getSquare(14, 0), true, "RG"));
		this.allTokens.add(new Token("Colonel Mustard", this.board.getSquare(0, 17), true, "CM"));
		this.allTokens.add(new Token("Mrs White", this.board.getSquare(9, 0), true, "MW"));

		List<Room> rooms = new ArrayList<>();
		rooms.addAll(this.board.getRooms().values());
		Room[] weaponRooms = new Room[6];

		// get 6 random rooms
		for (int i = 0; i < 6; i++) {
			int index = (int) (Math.random() * (9 - i));
			weaponRooms[i] = rooms.remove(index);
		}

		// put weapons in their random location
		this.allTokens.add(new Token("Candlestick", weaponRooms[0], false, "cs"));
		this.allTokens.add(new Token("Dagger", weaponRooms[1], false, "dg"));
		this.allTokens.add(new Token("Lead Pipe", weaponRooms[2], false, "lp"));
		this.allTokens.add(new Token("Revolver", weaponRooms[3], false, "rv"));
		this.allTokens.add(new Token("Rope", weaponRooms[4], false, "ro"));
		this.allTokens.add(new Token("Spanner", weaponRooms[5], false, "sp"));
	}

	/**
	 * A method doing a player's turn. Is passed a player then does their dice roll, moving and if applicable, it does their
	 * suggestions.
	 *
	 * @param p The player who's turn will be completed.
	 */

	@SuppressWarnings("unused")
	private void doTurn(Player p) {
		System.out.println(p.getUsername() + "'s turn.");

		Token token = p.getToken();

		Location location = token.getLocation(); // Get the location of the
													// player.

		Scanner in = null;

		try {
			in = new Scanner(System.in);

			boolean suggestionMade = false;

			int dist = rollDice(); // The distance a player can move.
			System.out.println(p.getUsername() + " rolled a " + dist);

			System.out.println("What would you like to do? 'help' for options");
			while (true) { // Player still has moves left.
				location = token.getLocation();
				Map<String, Location> adjacent = location.getAdjacent(); // Get
																			// adjacent
																			// locations.
				String instruction = in.nextLine(); // take input from user

				// help
				if (instruction.equals("help")) {
					System.out.println("enter the direction you want to move in followed by the distance you to move.");
					System.out.println("'left', 'right', 'up' or 'down'");
					System.out.println("Example: 'up 4'");
					System.out.println("'adjacent' for instructions for moving into and out of rooms, if possible.");
					System.out.println("'hand' to view hand");
					System.out.println("'suggestion' to make a suggestion.");
					System.out.println("'accusation' to make accusation.");
					System.out.println("'end turn'");
					continue;
				}

				// Print the names of the locations adjacent to this, accessible
				// by typing in the name
				if (instruction.equals("adjacent")) {
					System.out.println("Enter one of the following:");
					for (String key : adjacent.keySet()) {
						System.out.println(key);
					}
					continue;
				}

				// make an accusation
				if (instruction.equals("accusation")) {
					Triplet guess = accusation(in);

					if (guess.equalsTriplet(this.murderInfo)) { // Accusation
																// was correct,
																// player wins
																// game.
						System.out.println("Correct");
						System.out.println(p.getUsername() + " won the game as they guessed correctly!");
						this.gameOver = true;
						return;
					}
					System.out.println(p.getUsername() + " is out of the game as they guessed incorrectly!");
					p.setStatus(false); // Set player to out of the game.
					return;
				}

				// make a suggestion
				if (instruction.equals("suggestion")) {
					if (suggestionMade) {
						System.out.println("You have already made a suggestion this turn.");
						continue;
					}
					if (!(location instanceof Room)) {
						System.out.println("You must be in a room to make a suggestion.");
						continue;
					}
					Triplet suggestion = suggestion(in, p);
					Pair<Boolean, String> tempPair = suggestion.checkCards(this.players); // Check
																							// refutations.

					if (tempPair.getValue1()) { // If someone can refute.
						System.out.println(tempPair.getValue2());
					}
					else {
						System.out.println("No one could refute this.");
					}
					suggestionMade = true;
					continue;
				}

				// display your hand
				if (instruction.equals("hand")) {
					System.out.println(p.handString());
					continue;
				}

				// end your turn
				if (instruction.equals("end turn")) { // Player doesn't want to move.
					break;
				}

				// command is for moving to a room or out of one
				Location toMove = adjacent.get(instruction);
				if (toMove != null) {
					if (dist == 0) {
						System.out.println("You can't move any further this turn");
						continue;
					}
					toMove.addToken(token);
					--dist;
					System.out.println("You can move up to " + dist + " more.");
					continue;
				}

				// command is for moving certain distance in a direction
				String[] split = instruction.split(" ");
				if (split.length != 2) {
					System.out.println("Unexpected entry. Please try again, or 'help' for options");
					continue;
				}
				try {
					int instrDist = Integer.parseInt(split[1]);
					if (instrDist < 1) {
						System.out.println("Distance must be greater than 0");
						continue;
					}
					int xDir = 0;
					int yDir = 0;
					if (split[0].equals("left")) {
						xDir = -1;
					}
					else if (split[0].equals("right")) {
						xDir = 1;
					}
					else if (split[0].equals("up")) {
						yDir = -1;
					}
					else if (split[0].equals("down")) {
						yDir = 1;
					}
					else {
						System.out.println("Unexpected entry. Please try again, or 'help' for options");
						continue;
					}
					if (instrDist > dist) {
						System.out.println("You can't move that far.");
						continue;
					}
					if (this.board.moveToken(token, xDir, yDir, instrDist)) {
						dist -= instrDist;
						System.out.println("You can move up to " + dist + " more.");
						continue;
					}
					System.out.println("Illegal Move");
				}

				catch (NumberFormatException e) {
					System.out.println("This isn't a valid move thing.");
					continue; // Generic error.
				}

			}

		}

		catch (RuntimeException e) {
			System.out.println(e);
		}

	}

	/**
	 * Creates an accusation based on the user's input. Makes sure inputs are valid
	 * i.e. when prompted for the weapon, that the input actually corresponds a
	 * weapon.
	 *
	 * @param in The scanner which input will be read from (Probably System.in).
	 * @return a Triplet with the user input values.
	 */

	private Triplet accusation(Scanner in) {
		System.out.println("Person:");

		String personSuggest;
		Person: while (true) {
			personSuggest = in.nextLine();
			for (Token t : this.allTokens) {
				if (t.getName().equals(personSuggest)) {
					break Person;
				}
			}
			System.out.println("That isn't a person.");
		}

		System.out.println("Weapon:");

		String weaponSuggest;
		Weapon: while (true) {
			weaponSuggest = in.nextLine();
			for (Token t : this.allTokens) {
				if (!t.isCharacter() && t.getName().equals(weaponSuggest)) {
					break Weapon;
				}
			}
			System.out.println("That isn't a weapon.");
		}

		System.out.println("Room:");

		String roomSuggest;
		Room: while (true) {
			roomSuggest = in.nextLine();
			for (Room r : this.board.getRooms().values()) {
				if (r.getName().equals(roomSuggest)) {
					break Room;
				}
			}
			System.out.println("That isn't a room.");
		}

		return new Triplet(new Card(personSuggest), new Card(weaponSuggest), new Card(roomSuggest));
	}

	/**
	 * Creates a triplet based on the player's guess. A player is required to be passed as the room in the triplet is based on the
	 * player's current location.
	 *
	 * @param in The scanner passed along, should be System.in()
	 * @param p The player who's guessing.
	 * @return A triplet based on the info from the scanner.
	 */

	private Triplet suggestion(Scanner in, Player p) {

		Room room = (Room) p.getToken().getLocation();

		System.out.println("Person:");

		String personSuggest;
		Person: while (true) {
			personSuggest = in.nextLine();
			for (Token t : this.allTokens) {
				if (t.isCharacter() && t.getName().equals(personSuggest)) {
					room.addToken(t);
					break Person;
				}
			}
			System.out.println("That isn't a person.");
		}

		System.out.println("Weapon:");

		String weaponSuggest;
		Weapon: while (true) {
			weaponSuggest = in.nextLine();
			for (Token t : this.allTokens) {
				if (!t.isCharacter() && t.getName().equals(weaponSuggest)) {
					room.addToken(t);
					break Weapon;
				}
			}
			System.out.println("That isn't a weapon.");
		}
		String roomSuggest = room.getName();

		System.out.println(personSuggest + " with a " + weaponSuggest + " in the " + roomSuggest);

		return new Triplet(new Card(personSuggest), new Card(weaponSuggest), new Card(roomSuggest));
	}

	/**
	 * After the murder details have been done this puts all the cards into one set ready to be dealt amongst the players.
	 */

	private void putCards() {

		for (Card c : this.setOfCharacters) {
			this.allCards.add(c);
		}

		for (Card c : this.setOfWeapons) {
			this.allCards.add(c);
		}

		for (Card c : this.setOfRooms) {
			this.allCards.add(c);
		}
	}

	/**
	 * Deals the remaining cards to the hands of the players in the game. Each player will receive (18 / n) cards where n is the
	 * number of players.
	 */

	private void dealHands() {

		int numbPlayers = this.players.size();
		int playerNumb = 0;

		for (Card c : this.allCards) {
			++playerNumb;

			if (playerNumb == numbPlayers) {
				playerNumb = 0; // Reset to first player.
			}

			Player p = this.players.get(playerNumb);
			p.addCard(c);

		}

	}

	/**
	 * Returns the player with the highest roll to see who starts.
	 *
	 * @param temp List of players we're iterating through.
	 * @return Player with the highest roll.
	 */

	private Player doStartRolls(List<Player> temp) {

		int highest = 0;

		Map<Player, Integer> rolls = new HashMap<>();

		for (Player p : temp) {
			int roll = rollDice6();
			System.out.println(p.getUsername() + " rolled a " + roll);
			if (roll < highest) {
				continue;
			}
			highest = roll;
			rolls.put(p, roll);
		}

		List<Player> highRollers = new ArrayList<>();

		for (Player p : rolls.keySet()) {
			if (rolls.get(p) == highest) {
				highRollers.add(p);
			}
		}

		if (highRollers.size() == 1) {
			return highRollers.get(0);
		}
		System.out.println("Draw! Highest rollers rolling again.");
		return doStartRolls(highRollers);
	}

	/**
	 * Sets up a human player and adds it to the set of players to be passed to the board when the game is setup.
	 *
	 * @param playerNumb The number of the player to be setup.
	 * @return newPlayer A setup player that will be added to the set of players in the game.
	 */
	private Player setupPlayer(int playerNumb) { // Does this need to take an
		// arg?

		Scanner in = null;

		try {
			in = new Scanner(System.in);
			System.out.println("Enter your username:");

			String username = in.nextLine();

			String charName = null;

			while (!this.charNames.contains(charName)) {

				for (Player p : this.players) { // Make sure the chosen username
					// is
					// unique.
					if (p.getUsername().equals(username)) {
						System.out.println("This username has already been taken");
						setupPlayer(playerNumb); // Try again.
					}
				}

				System.out.println("Select a character: ");

				charName = in.nextLine();

				if (!this.charNames.contains(charName)) { // Invalid character,
					// either
					// doesn't exist
					// or already taken.
					System.out.println("This is not a valid character");
				}
			}

			this.charNames.remove(charName); // Remove this character as a
			// pickable
			// character.

			Token token = null;

			for (Token t : this.allTokens) {
				if (t.getName().equals(charName)) {
					token = t;
				}
			}

			return (new Player(username, token)); // Return the new character.
		}

		catch (RuntimeException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Gets a random number between 1 and 12 representing a dice roll for moving around the board.
	 *
	 * @return An int between 1 and 12.
	 */

	public static int rollDice() {
		return rollDice6() + rollDice6();
	}

	/**
	 * Gets a random number between 1 and 6 representing a dice roll for moving around the board.
	 *
	 * @return An int between 1 and 6.
	 */

	public static int rollDice6() {
		return (int) (Math.random() * 6 + 1);
	}

	/**
	 * Puts the list of playable characters in a set and returns them. For purposes of selecting characters.
	 */

	private void createCharStrings() {
		this.charNames = new HashSet<>();
		this.charNames.add("Miss Scarlett");
		this.charNames.add("Professor Plum");
		this.charNames.add("Mrs Peacock");
		this.charNames.add("Reverend Green");
		this.charNames.add("Colonel Mustard");
		this.charNames.add("Mrs White");
	}

	/**
	 * Creates cards based on the characters and adds them to a set.
	 */

	private void createCharacters() {
		this.setOfCharacters = new HashSet<>();

		this.setOfCharacters.add(new Card("Miss Scarlett"));
		this.setOfCharacters.add(new Card("Colonel Mustard"));
		this.setOfCharacters.add(new Card("Mrs White"));
		this.setOfCharacters.add(new Card("Reverend Green"));
		this.setOfCharacters.add(new Card("Mrs Peacock"));
		this.setOfCharacters.add(new Card("Professor Plum"));
	}

	/**
	 * Creates cards based on the weapons and adds them to a set.
	 */

	private void createWeapons() {
		this.setOfWeapons = new HashSet<>();

		this.setOfWeapons.add(new Card("Candlestick"));
		this.setOfWeapons.add(new Card("Dagger"));
		this.setOfWeapons.add(new Card("Lead Pipe"));
		this.setOfWeapons.add(new Card("Revolver"));
		this.setOfWeapons.add(new Card("Rope"));
		this.setOfWeapons.add(new Card("Spanner"));
	}

	/**
	 * Creates cards based on the rooms and adds them to a set.
	 */

	private void createRooms() {
		this.setOfRooms = new HashSet<>();

		this.setOfRooms.add(new Card("Kitchen"));
		this.setOfRooms.add(new Card("Ball Room"));
		this.setOfRooms.add(new Card("Conservatory"));
		this.setOfRooms.add(new Card("Billiard Room"));
		this.setOfRooms.add(new Card("Library"));
		this.setOfRooms.add(new Card("Study"));
		this.setOfRooms.add(new Card("Hall"));
		this.setOfRooms.add(new Card("Lounge"));
		this.setOfRooms.add(new Card("Dining Room"));
	}

	/**
	 * Picks a random weapon, person, and room into a triplet. These are the murder details that have to be guessed.
	 */

	private void doMurder() {

		int randChar = (int) (Math.random() * this.setOfCharacters.size());
		Card[] arrOfCards = new Card[this.setOfCharacters.size()]; // Create new
		// array.
		this.setOfCharacters.toArray(arrOfCards); // Put contents of set in new
		// array.
		Card charCard = arrOfCards[randChar]; // Get card at random position.
		this.setOfCharacters.remove(charCard);

		int randWeapon = (int) (Math.random() * this.setOfWeapons.size());
		Card[] arrOfWeapons = new Card[this.setOfWeapons.size()];
		this.setOfWeapons.toArray(arrOfWeapons);
		Card weaponCard = arrOfWeapons[randWeapon];
		this.setOfWeapons.remove(weaponCard);

		int randRoom = (int) (Math.random() * this.setOfRooms.size());
		Card[] arrOfRooms = new Card[this.setOfRooms.size()];
		this.setOfRooms.toArray(arrOfRooms);
		Card roomCard = arrOfRooms[randRoom];
		this.setOfRooms.remove(roomCard);

		this.setOfCharacters.remove(roomCard); // Remove selected
		this.setOfWeapons.remove(weaponCard);
		this.setOfRooms.remove(roomCard);

		this.murderInfo = new Triplet(charCard, weaponCard, roomCard);

	}

	/**
	 * Does the game logic.
	 */

	private void runGame() {
		while (true) {
			for (Player p : this.players) {
				if (this.gameOver) {
					return;
				}
				int playersLeft = 0; // calculate the number of players left
				Player last = null; // if there is only player, this is the
									// winner
				for (Player pl : this.players) {
					if (pl.getStatus()) {
						playersLeft++;
						last = pl;
					}
				}
				// if only one player is left, they win
				if (playersLeft == 1) {
					assert (last != null);
					System.out.println(last.getUsername() + " won as everyone else is out.");
					System.out.println("The murder was actually done by " + this.murderInfo);
					System.out.println("It seems that detective work requires more competence than you lot have.");
					return;
				}
				if (p.getStatus()) {
					doTurn(p);
				}

			}
		}
	}

	@SuppressWarnings("unused")

	/**
	 * Gets the player details from the System.in. Then adds the player created from
	 * these details into the game. Once all the players have been setup the board is drawn.
	 */

	private void setupPlayers() {
		try {

			Scanner in = new Scanner(System.in);

			int numbPlayers = 0;

			// Make sure we do eventually get a valid number of players.
			while (numbPlayers < 3 || numbPlayers > 6) {

				System.out.println("Enter the number of players: ");

				String input = in.nextLine();

				try {
					numbPlayers = Integer.parseInt(input); // Try to parse to
															// int.
				}
				catch (NumberFormatException e) {
					// If input is not convertable to an int. Invalid input.
					System.out.println("This is not a valid number.");
				}

				if (numbPlayers > 6 || numbPlayers < 3) {
					System.out.println("This is not a valid number of players, needs to be between 3-6");
					numbPlayers = 0; // Go back to top of while loop.
				}
			}
			// Loop through number of players setting them up.
			// Add the new player to the set of players.
			for (int i = 0; i < numbPlayers; i++) {
				this.players.add(setupPlayer(i));
			}
		}

		catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		new Cluedo();
	}

}
