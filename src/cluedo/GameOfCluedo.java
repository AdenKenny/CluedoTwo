package cluedo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import gui.Canvas;
import gui.Frame;
import items.Card;
import items.Token;
import location.Location;
import location.Room;
import util.Pair;
import util.Tuple;

/**
 * A class containing the main game logic including setting up the game and then playing the game.
 * Is then passed to the Frame class for graphical representation.
 * 
 * @author Aden Kenny and Simon Pope.
 */

public class GameOfCluedo {

	private Tuple murderInfo; // Triplet of the actual murder details.
	private Board board; // The game board.
	private List<Player> players; // List containing the players in the game.

	private Set<Card> setOfRooms; // Used for creating the triplet.
	private Set<Card> setOfWeapons;
	private Set<Card> setOfCharacters;

	private String[] charNames;
	private String[] weaponNames;
	private String[] roomNames;

	private Set<Card> allCards; // All cards, used for dealing.
	private Set<Token> allTokens; // The character and weapon tokens.

	private Frame frame; //Frame the gui is based on.

	private int turnNumber; 
	private boolean suggestionMade;
	private int moveDistance; //Distance a player has left to move.

	public GameOfCluedo(Frame frame) {

		this.frame = frame;

		this.players = new ArrayList<>();
		this.allCards = new HashSet<>();

		createRooms(); //Basic setup.
		createWeapons();
		createCharacters();

		setupNames();

		this.board = new Board(); //Board logic setup.

		tokensSetup();

		doMurder(); // Create triplet of murder info.
	}

	/**
	 * Make an accusation.
	 */
	public void accusation() {
		String personSuggest = (String) Frame.askOptions("Person:", this.charNames);
		if (personSuggest == null) {
			return;
		}

		String weaponSuggest = (String) Frame.askOptions("Weapon:", this.weaponNames);
		if (weaponSuggest == null) {
			return;
		}

		String roomSuggest = (String) Frame.askOptions("Room:", this.roomNames);

		if (roomSuggest == null) {
			return;
		}

		Tuple guess = new Tuple(new Card(personSuggest), new Card(weaponSuggest), new Card(roomSuggest));

		Player p = this.players.get(this.turnNumber);

		if (guess.equalsTriplet(this.murderInfo)) { // Accusation
													// was correct,
													// player wins
													// game.
			this.frame.showMessage("Correct");
			this.frame.showMessage(p.getUsername() + " won the game as they guessed correctly!");
			this.frame.endGame();
			return;
		}

		this.frame.showMessage(p.getUsername() + " is out of the game as they guessed incorrectly!");
		p.setStatus(false); // Set player to out of the game.
		nextTurn();
	}

	/**
	 * Called to handle the logic of moving.
	 * 
	 * @param x The place clicked on the x-axis.
	 * @param y The place clicked on the y-axis.
	 */
	
	public void boardClicked(int x, int y) {
		
		if (this.moveDistance == -1) { //Player hasn't rolled.
			this.frame.showMessage("You need to roll the dice to see how far you can move.");
			return;
		}
		
		if (this.moveDistance == 0) { //Player doesn't have any moves left.
			this.frame.showMessage("You can't move any further this turn.");
			return;
		}
		
		Player current = this.players.get(this.turnNumber);
		Token t = current.getToken();
		Pair<Boolean, Object> move = this.board.moveToken(t, x, y, this.moveDistance);
		
		if (move.first()) { //Move was valid.
			this.moveDistance -= (int) move.second();
			this.frame.setTitle(current.getUsername() + "'s Turn - " + this.moveDistance + " Squares Left");
			this.frame.getCanvas().repaint();
		}
		
		else { //Invalid move.
			String reason = (String) move.second(); 
			if (reason.length() != 0) {
				this.frame.showMessage(reason);
			}
		}
	}


	/**
	 * Creates cards based on the characters and adds them to a set.
	 */
	private void createCharacters() {
		this.setOfCharacters = new HashSet<>();

		String path = "cards/"; //Base location of card images in assets folder.

		this.setOfCharacters.add(new Card("Miss Scarlett", Canvas.loadImage(path + "missScarlett.png")));
		this.setOfCharacters.add(new Card("Colonel Mustard", Canvas.loadImage(path + "colonelMustard.png")));
		this.setOfCharacters.add(new Card("Mrs White", Canvas.loadImage(path + "mrsWhite.png")));
		this.setOfCharacters.add(new Card("Reverend Green", Canvas.loadImage(path + "reverendGreen.png")));
		this.setOfCharacters.add(new Card("Mrs Peacock", Canvas.loadImage(path + "mrsPeacock.png")));
		this.setOfCharacters.add(new Card("Professor Plum", Canvas.loadImage(path + "professorPlum.png")));

	}

	/**
	 * Creates cards based on the rooms and adds them to a set.
	 */
	private void createRooms() {
		this.setOfRooms = new HashSet<>();

		String path = "cards/"; //Base location of card images in assets folder.

		this.setOfRooms.add(new Card("Kitchen", Canvas.loadImage(path + "kitchen.png")));
		this.setOfRooms.add(new Card("Ball Room", Canvas.loadImage(path + "ballRoom.png")));
		this.setOfRooms.add(new Card("Conservatory", Canvas.loadImage(path + "conservatory.png")));
		this.setOfRooms.add(new Card("Billiard Room", Canvas.loadImage(path + "billiardRoom.png")));
		this.setOfRooms.add(new Card("Library", Canvas.loadImage(path + "library.png")));
		this.setOfRooms.add(new Card("Study", Canvas.loadImage(path + "study.png")));
		this.setOfRooms.add(new Card("Hall", Canvas.loadImage(path + "hall.png")));
		this.setOfRooms.add(new Card("Lounge", Canvas.loadImage(path + "lounge.png")));
		this.setOfRooms.add(new Card("Dining Room", Canvas.loadImage(path + "diningRoom.png")));
	}

	/**
	 * Creates cards based on the weapons and adds them to a set.
	 */
	private void createWeapons() {
		this.setOfWeapons = new HashSet<>();

		String path = "cards/";

		this.setOfWeapons.add(new Card("Candlestick", Canvas.loadImage(path + "candlestick.png")));
		this.setOfWeapons.add(new Card("Dagger", Canvas.loadImage(path + "dagger.png")));
		this.setOfWeapons.add(new Card("Lead Pipe", Canvas.loadImage(path + "leadPipe.png")));
		this.setOfWeapons.add(new Card("Revolver", Canvas.loadImage(path + "revolver.png")));
		this.setOfWeapons.add(new Card("Rope", Canvas.loadImage(path + "rope.png")));
		this.setOfWeapons.add(new Card("Spanner", Canvas.loadImage(path + "spanner.png")));
	}

	/**
	 * Deals the remaining cards to the hands of the players in the game. Each player will receive (18 / n) cards where n is the
	 * number of players.
	 */
	private void dealHands() {

		int numbPlayers = this.players.size(); //Number of players in the game.
		int playerNumb = 0;

		for (Card c : this.allCards) { //Iterate through set of remaing cards.
			++playerNumb;

			if (playerNumb == numbPlayers) {
				playerNumb = 0; // Reset to first player.
			}

			Player p = this.players.get(playerNumb);
			p.addCard(c); //Put card into player's hand.
		}
	}

	/**
	 * Picks a random weapon, person, and room into a triplet. These are the murder details that have to be guessed.
	 */
	private void doMurder() {

		int randChar = (int) (Math.random() * this.setOfCharacters.size()); //Random position in array.
		Card[] arrOfCards = new Card[this.setOfCharacters.size()]; // Create new array.
		this.setOfCharacters.toArray(arrOfCards); // Put contents of set in new array.
		Card charCard = arrOfCards[randChar]; // Get card at random position.
		this.setOfCharacters.remove(charCard); //Remove card so it can't be dealt.

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

		this.murderInfo = new Tuple(charCard, weaponCard, roomCard); //Put the cards a tuple and store.

	}

	/**
	 * Returns the player with the highest roll to see who starts.
	 *
	 * @param temp List of players we're iterating through.
	 * @return Player with the highest roll.
	 */
	private Player doStartRolls(List<Player> temp) {

		int highest = 0; //Highest roll so far.

		Map<Player, Integer> rolls = new HashMap<>();

		for (Player p : temp) { //Iterate through players.
			int roll = rollD6();
			this.frame.showMessage(p.getUsername() + " rolled a " + roll); //Show current roll.
			if (roll < highest) { //Roll isn't new highest.
				continue;
			}
			highest = roll; //Else roll is new highest.
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
		
		this.frame.showMessage("Draw! Highest rollers rolling again."); //Redo rolling.
		return doStartRolls(highRollers);
	}

	/**
	 * Returns the board.
	 *
	 * @return A board object.
	 */

	public Board getBoard() {
		return this.board;
	}

	/**
	 * Returns the player who has the current turn.
	 *
	 * @return The player who is on their turn.
	 */

	public Player getCurrentPlayer() {
		return this.players.get(this.turnNumber);
	}

	/**
	 * Sets up a game with preset players and assumes
	 * Player 1 won the roll off for first turn.
	 */
	public void mockGame() {
		setupPlayer("Player 1", "Miss Scarlett");
		setupPlayer("Player 2", "Colonel Mustard");
		setupPlayer("Player 3", "Mrs Peacock");
		this.turnNumber = -1;
		putCards();
		dealHands();
		nextTurn();
	}
	
	public void nextTurn() {
		int playersLeft = 0; // calculate the number of players left
		Player last = null; // if there is only player, this is the winner
		
		for (Player pl : this.players) {
			if (pl.getStatus()) {
				playersLeft++;
				last = pl;
			}
		}
		
		// if only one player is left, they win
		if (playersLeft == 1) {
			assert (last != null);
			this.frame.showMessage(last.getUsername() + " won as everyone else is out.");
			this.frame.showMessage("The murder was actually done by " + this.murderInfo);
			this.frame.showMessage("It seems that detective work requires more competence than you lot have.");
			this.frame.endGame();
			return;
		}

		do {
			this.turnNumber++;
			if (this.turnNumber >= this.players.size()) {
				this.turnNumber = 0;
			}
		} while(!this.players.get(this.turnNumber).getStatus());

		this.suggestionMade = false;
		this.moveDistance = -1;

		Player current = this.players.get(this.turnNumber);
		this.frame.setTitle(current.getUsername() + "'s Turn");
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
	 * Gets a random number between 1 and 12 representing a dice roll for moving around the board.
	 *
	 * @return An int between 1 and 12.
	 */
	public static int roll2D6() {
		return rollD6() + rollD6();
	}

	/**
	 * Gets a random number between 1 and 6 representing a dice roll for moving around the board.
	 *
	 * @return An int between 1 and 6.
	 */
	public static int rollD6() {
		return (int) (Math.random() * 6 + 1);
	}

	public 
	
	void rollDice() {
		if (this.moveDistance != -1) {
			this.frame.showMessage("You've already rolled the dice this turn.");
			return;
		}
		this.moveDistance = roll2D6(); // The distance a player can move.
		Player current = this.players.get(this.turnNumber);
		this.frame.showMessage(current.getUsername() + " rolled a " + this.moveDistance);
		this.frame.setTitle(current.getUsername() + "'s Turn - " + this.moveDistance + " Squares Left");
	}
	
	/**
	 * Gives everything on the board in the array a name.
	 */
	
	private void setupNames() {
		this.charNames = new String[6];
		this.charNames[0] = "Colonel Mustard";
		this.charNames[1] = "Miss Scarlett";
		this.charNames[2] = "Mrs Peacock";
		this.charNames[3] = "Mrs White";
		this.charNames[4] = "Professor Plum";
		this.charNames[5] = "Reverend Green";

		this.weaponNames = new String[6];
		this.weaponNames[0] = "Candlestick";
		this.weaponNames[1] = "Dagger";
		this.weaponNames[2] = "Lead Pipe";
		this.weaponNames[3] = "Revolver";
		this.weaponNames[4] = "Rope";
		this.weaponNames[5] = "Spanner";

		this.roomNames = new String[9];
		this.roomNames[0] = "Ball Room";
		this.roomNames[1] = "Billiard Room";
		this.roomNames[2] = "Conservatory";
		this.roomNames[3] = "Dining Room";
		this.roomNames[4] = "Hall";
		this.roomNames[5] = "Kitchen";
		this.roomNames[6] = "Library";
		this.roomNames[7] = "Lounge";
		this.roomNames[8] = "Study";
	}

	/**
	 * Sets up a human player and adds it to the set of players to be passed to the board when the game is setup.
	 *
	 * @param username The username of the player
	 * @param charName The name of the character being played
	 * @return newPlayer A setup player that will be added to the set of players in the game.
	 */
	public void setupPlayer(String username, String charName) {
		Token token = null;

		for (Token t : this.allTokens) {
			if (t.getName().equals(charName)) {
				token = t;
			}
		}

		this.players.add(new Player(username, token)); // Return the new character.
	}

	/**
	 * Setup the players.
	 */
	public void setupPlayers() {
		
		//get the number of players
		Integer[] possibleNumbers = { 3, 4, 5, 6 };
		Integer selectedNumber = (Integer) Frame.askOptions("How many players?", possibleNumbers);

		if (selectedNumber == null) {
			this.frame.endGame();
			return;
		}
		int numPlayers = selectedNumber;

		String[] tempCharNames = this.charNames;

		String[] usernames = new String[numPlayers];
				
		// get each players name and character
		for (int i = 0; i < numPlayers; i++) {
			String username = Frame.askText("Enter a username:");
			outer: while(true) {
				if (username == null) {
					this.frame.endGame();
					return;
				}
				for (String user : usernames) {
					if (username.equals(user)) {
						username = Frame.askText("Username already in use. Try again:");
						continue outer;
					}
				}
				break;
			}

			usernames[i] = username;
			
			Object selectedCharacter = Frame.askOptions("Select a character:", tempCharNames);
			if (selectedCharacter == null) {
				this.frame.endGame();
				return;
			}

			//remove character from array
			String[] temp = new String[tempCharNames.length - 1];
			int j = 0;
			for (String c : tempCharNames) {
				if (!c.equals(selectedCharacter)) {
					temp[j++] = c;
				}
			}
			tempCharNames = temp;

			setupPlayer(username, (String)selectedCharacter);
		}
	}

	public Set<Card> showHand() {
		return this.players.get(this.turnNumber).getHand();
	}

	public void startGame() {
		this.frame.showMessage("Rolling off for first turn.");
		Player highest = doStartRolls(this.players); // Get the player with
				// the highest roll.
		this.frame.showMessage(highest.getUsername() + " goes first!");

		this.players.remove(highest);
		this.players.add(0, highest);
		this.turnNumber = -1;

		putCards();
		dealHands();

		nextTurn();
	}

	/**
	 * Make a suggestion.
	 */
	public void suggestion() {
		if (this.suggestionMade) {
			this.frame.showMessage("You have already made a suggestion this turn.");
			return;
		}

		Player p = this.players.get(this.turnNumber);
		Location loc = p.getToken().getLocation();
		if (!(loc instanceof Room)) {
			this.frame.showMessage("You must be in a room to make a suggestion.");
			return;
		}

		String personSuggest = (String)Frame.askOptions("Person:", this.charNames);
		if (personSuggest == null) {
			return;
		}

		String weaponSuggest = (String)Frame.askOptions("Weapon:", this.weaponNames);
		if (weaponSuggest == null) {
			return;
		}
		
		for (Token t : this.allTokens) {
			String name = t.getName();
			if (name.equals(personSuggest) || name.equals(weaponSuggest)) {
				loc.addToken(t);
			}
		}
		
		this.frame.getCanvas().repaint();
		
		String roomSuggest = ((Room) loc).getName();

		Tuple suggestion = new Tuple(new Card(personSuggest), new Card(weaponSuggest), new Card(roomSuggest));

		Pair<Boolean, String> refutation = suggestion.checkCards(this.players); // Check
																				// refutations.

		if (refutation.first()) { // If someone can refute.
			this.frame.showMessage(refutation.second());
		}
		else {
			this.frame.showMessage("No one could refute this.");
		}
		
		this.suggestionMade = true;
	}

	/**
	 * Setup the character and weapons tokens.
	 */
	private void tokensSetup() {
		this.allTokens = new HashSet<>();

		// put characters in starting locations
		this.allTokens.add(new Token("Miss Scarlett", this.board.getSquare(7, 24), true, "people/missScarlett.png"));
		this.allTokens.add(new Token("Professor Plum", this.board.getSquare(23, 19), true, "people/professorPlum.png"));
		this.allTokens.add(new Token("Mrs Peacock", this.board.getSquare(23, 6), true, "people/mrsPeacock.png"));
		this.allTokens.add(new Token("Reverend Green", this.board.getSquare(14, 0), true, "people/reverendGreen.png"));
		this.allTokens.add(new Token("Colonel Mustard", this.board.getSquare(0, 17), true, "people/colonelMustard.png"));
		this.allTokens.add(new Token("Mrs White", this.board.getSquare(9, 0), true, "people/mrsWhite.png"));

		List<Room> rooms = new ArrayList<>();
		rooms.addAll(this.board.getRooms().values());
		Room[] weaponRooms = new Room[6];

		// get 6 random rooms
		for (int i = 0; i < 6; i++) {
			int index = (int) (Math.random() * (9 - i));
			weaponRooms[i] = rooms.remove(index);
		}

		// put weapons in their random location
		this.allTokens.add(new Token("Candlestick", weaponRooms[0], false, "weapons/candlestick.png"));
		this.allTokens.add(new Token("Dagger", weaponRooms[1], false, "weapons/dagger.png"));
		this.allTokens.add(new Token("Lead Pipe", weaponRooms[2], false, "weapons/pipe.png"));
		this.allTokens.add(new Token("Revolver", weaponRooms[3], false, "weapons/revolver.png"));
		this.allTokens.add(new Token("Rope", weaponRooms[4], false, "weapons/rope.png"));
		this.allTokens.add(new Token("Spanner", weaponRooms[5], false, "weapons/spanner.png"));
	}

}
