package cluedo;

import java.util.HashMap;
import java.util.Map;

import items.Token;
import location.Location;
import location.Room;
import location.Square;

/**
 * A class representing the board on which the game of Cluedo is played on.
 * Contains all the tokens, and positions of everything in the game.
 *
 * @author Aden Kenny and Simon Pope.
 *
 */

public class Board {
	private static final int BOARD_WIDTH = 24;
	private static final int BOARD_HEIGHT = 25;

	private Square[][] boardSquares;
	private Map<String, Room> rooms; //Set of the rooms on the board.

	private char[][] asciiBoard;

	private String[] key;

	/**
	 * 0 = empty space
	 * 1 = square
	 * 2 = square next to door
	 * 3 = room
	 * 4 = room next to door
	 * K = kitchen name
	 * B = ball room name
	 * C = conser- name
	 * V = vatory name
	 * D = dining name
	 * R = room name
	 * I = billiard name
	 * L = library name
	 * O = lounge name
	 * H = hall name
	 * S = study name
	 *
	 * Lower case letters are where characters are displayed in room.
	 * They correspond to the uppercase letters used for names.
	 **/

	String[] boardStrings = {
			"000000000100001000000000",
			"@3333301113333111033333@",
			"3333331133333333113C3333",
			"3K33331133333333113V3333",
			"3333331133B33333114c3333",
			"3k3333124333333321233330",
			"033343113b33333311111111",
			"111121113433334311111110",
			"0111111112111121113i3333",
			"3333311111111111124I3333",
			"3d333333113333311133R333",
			"333333331133333111333333",
			"33D333342133333111333343",
			"333R33331133333111112120",
			"333333331133333111334330",
			"333333431133333113l33333",
			"0111112111333331243L3333",
			"111111111112211113333333",
			"011111211334433111333330",
			"3333334113h3333111111111",
			"333333311333334212111110",
			"33O33331133H333114333333",
			"3333333113333331133S3333",
			"3o3333311333333113s33333",
			"@3333301003333001033333@",
	};

	public Board() {
		populateBoard();
		roomSetup();
		addRoomAccess();
		asciiBoardSetup();
		keySetup();
	}

	/**
	 * Populates a two dimensional array representing the squares of the board.
	 * '0' represents blank areas, '1' represents squares that a player can move on
	 * and to, '2' represents a door into a room on a square (so the player can still
	 * move to this square), and '3' represents a room.
	 */

	private void populateBoard() {
		this.boardSquares = new Square[BOARD_WIDTH][BOARD_HEIGHT];
		for (int y = 0; y < BOARD_HEIGHT; y++) {
			for (int x = 0; x < BOARD_WIDTH; x++) {
				char square = boardChar(x, y);
				if (square == '1' || square == '2') {
					this.boardSquares[x][y] = new Square();
				}
			}
		}
	}

	/**
	 * Sets up all the rooms, including the secret passageways.
	 */
	private void roomSetup() {
		this.rooms = new HashMap<>();

		Map<String, Location> adjacent = new HashMap<>();
		adjacent.put("down", this.boardSquares[4][7]);
		Room kitchen = new Room("Kitchen", adjacent);
		this.rooms.put("k", kitchen);

		adjacent = new HashMap<>();
		adjacent.put("left", this.boardSquares[7][5]);
		adjacent.put("down left", this.boardSquares[9][8]);
		adjacent.put("down right", this.boardSquares[14][8]);
		this.rooms.put("b", new Room("Ball Room", adjacent));

		adjacent = new HashMap<>();
		adjacent.put("down", this.boardSquares[18][5]);
		Room conservatory = new Room("Conservatory", adjacent);
		this.rooms.put("c", conservatory);

		adjacent = new HashMap<>();
		adjacent.put("right", this.boardSquares[8][12]);
		adjacent.put("down", this.boardSquares[6][16]);
		this.rooms.put("d", new Room("Dining Room", adjacent));

		adjacent = new HashMap<>();
		adjacent.put("left", this.boardSquares[17][9]);
		adjacent.put("down", this.boardSquares[22][13]);
		this.rooms.put("i", new Room("Billiard Room", adjacent));

		adjacent = new HashMap<>();
		adjacent.put("up", this.boardSquares[20][13]);
		adjacent.put("left", this.boardSquares[16][16]);
		this.rooms.put("l", new Room("Library", adjacent));

		adjacent = new HashMap<>();
		adjacent.put("up", this.boardSquares[6][18]);
		adjacent.put("Conservatory", conservatory);
		Room lounge = new Room("Lounge", adjacent);
		conservatory.addAdjacent("Lounge", lounge);
		this.rooms.put("o", lounge);

		adjacent = new HashMap<>();
		adjacent.put("up left", this.boardSquares[11][17]);
		adjacent.put("up right", this.boardSquares[12][17]);
		adjacent.put("right", this.boardSquares[15][20]);
		this.rooms.put("h", new Room("Hall", adjacent));

		adjacent = new HashMap<>();
		adjacent.put("up", this.boardSquares[17][20]);
		adjacent.put("Kitchen", kitchen);
		Room study = new Room("Study", adjacent);
		kitchen.addAdjacent("Study", study);
		this.rooms.put("s", study);
	}

	/**
	 * Adds the connections between the rooms and the squares outside the door.
	 */
	private void addRoomAccess() {
		this.boardSquares[4][7].addAdjacent("Kitchen", this.rooms.get("k"));

		this.boardSquares[7][5].addAdjacent("Ball Room", this.rooms.get("b"));
		this.boardSquares[9][8].addAdjacent("Ball Room", this.rooms.get("b"));
		this.boardSquares[14][8].addAdjacent("Ball Room", this.rooms.get("b"));

		this.boardSquares[18][5].addAdjacent("Conservatory", this.rooms.get("c"));

		this.boardSquares[8][12].addAdjacent("Dining Room", this.rooms.get("d"));
		this.boardSquares[6][16].addAdjacent("Dining Room", this.rooms.get("d"));

		this.boardSquares[17][9].addAdjacent("Billiard Room", this.rooms.get("i"));
		this.boardSquares[22][13].addAdjacent("Billiard Room", this.rooms.get("i"));

		this.boardSquares[20][13].addAdjacent("Library", this.rooms.get("l"));
		this.boardSquares[16][16].addAdjacent("Library", this.rooms.get("l"));

		this.boardSquares[6][18].addAdjacent("Lounge", this.rooms.get("o"));

		this.boardSquares[11][17].addAdjacent("Hall", this.rooms.get("h"));
		this.boardSquares[12][17].addAdjacent("Hall", this.rooms.get("h"));
		this.boardSquares[15][20].addAdjacent("Hall", this.rooms.get("h"));

		this.boardSquares[17][20].addAdjacent("Study", this.rooms.get("s"));
	}

	/**
	 * Reads the board strings and converts it into a 2d array of display characters.
	 */
	private void asciiBoardSetup() {
		this.asciiBoard = new char[2 * BOARD_WIDTH + 1][2 * BOARD_HEIGHT + 1];

		//add wall to top of house
		for (int i = 0; i < BOARD_WIDTH; i++) {
			if (this.boardStrings[0].charAt(i) == '0') {
				this.asciiBoard[i * 2][0] = ' ';
				this.asciiBoard[i * 2 + 1][0] = ' ';
			}
			else {
				this.asciiBoard[i * 2][0] = ' ';
				this.asciiBoard[i * 2 + 1][0] = '_';
			}
		}

		this.asciiBoard[2 * BOARD_WIDTH][0] = ' ';

		//read through each square
		for (int y = 0; y < BOARD_HEIGHT; y++) {
			for (int x = 0; x < BOARD_WIDTH; x++) {
				char square = boardChar(x, y);
				char left = boardChar(x - 1, y);
				char down = boardChar(x, y + 1);
				//lowercase letter, token in rooms are displayed here
				if (Character.isLetter(square) && Character.toLowerCase(square) == square) {
					this.asciiBoard[x * 2][y + 1] = square;
					this.asciiBoard[x * 2 + 1][y + 1] = ' ';
				}
				//if this is an empty space, i.e. not part of the gameboard
				else if (square == '0') {
					if (left == '0') {
						this.asciiBoard[x * 2][y + 1] = ' ';
					}
					else {
						this.asciiBoard[x * 2][y + 1] = '|';
					}
					if (down != '0') {
						this.asciiBoard[x * 2 + 1][y + 1] = '_';
					}
					else {
						this.asciiBoard[x * 2 + 1][y + 1] = ' ';
					}
				}
				//normal square
				else if (square == '1') {
					this.asciiBoard[x * 2][y + 1] = '|';
					this.asciiBoard[x * 2 + 1][y + 1] = '_';
				}
				//square which connects to room
				else if (square == '2') {
					if (left == '4') {
						this.asciiBoard[x * 2][y + 1] = ':';
					}
					else {
						this.asciiBoard[x * 2][y + 1] = '|';
					}
					if (down == '4') {
						this.asciiBoard[x * 2 + 1][y + 1] = ' ';
					}
					else {
						this.asciiBoard[x * 2 + 1][y + 1] = '_';
					}
				}
				//normal room
				else if (square == '3') {
					if (left == '3' || left == '4' || left == '@' || (Character.isLetter(left) && Character.toLowerCase(left) == left)) {
						this.asciiBoard[x * 2][y + 1] = ' ';
					}
					else {
						this.asciiBoard[x * 2][y + 1] = '|';
					}
					if (down == '0' || down == '1' || down == '2')
						this.asciiBoard[x * 2 + 1][y + 1] = '_';
					else {
						this.asciiBoard[x * 2 + 1][y + 1] = ' ';
					}
				}
				//room part which connects to a square (door)
				else if (square == '4') {
					if (left == '0' || left == '1') {
						this.asciiBoard[x * 2][y + 1] = '|';
					}
					else if (left == '2') {
						this.asciiBoard[x * 2][y + 1] = ':';
					}
					else {
						this.asciiBoard[x * 2][y + 1] = ' ';
					}
					if (down == '0' || down == '1') {
						this.asciiBoard[x * 2 + 1][y + 1] = '_';
					}
					else if (down == '2') {
						this.asciiBoard[x * 2 + 1][y + 1] = '.';
					}
					else if (boardChar(x, y - 1) == '2') {
						this.asciiBoard[x * 2 + 1][y + 1] = '\'';
					}
					else {
						this.asciiBoard[x * 2 + 1][y + 1] = ' ';
					}
				}
				//secret passageway
				else if (square == '@') {
					if (left == '0') {
						this.asciiBoard[x * 2][y + 1] = '|';
					}
					else {
						this.asciiBoard[x * 2][y + 1] = ' ';
					}
					this.asciiBoard[x * 2 + 1][y + 1] = '@';
				}
				//display names of rooms
				else if (square == 'K') {
					roomName(" KITCHEN", x * 2, y + 1);
					x += 3;
				}
				else if (square == 'B') {
					roomName("BALL ROOM ", x * 2, y + 1);
					x += 4;
				}
				else if (square == 'C') {
					roomName(" CONSER-", x * 2, y + 1);
					x += 3;
				}
				else if (square == 'V') {
					roomName(" VATORY ", x * 2, y + 1);
					x += 3;
				}
				else if (square == 'D') {
					roomName(" DINING ", x * 2, y + 1);
					x += 3;
				}
				else if (square == 'R') {
					roomName("ROOM", x * 2, y + 1);
					x += 1;
				}
				else if (square == 'I') {
					roomName("BILLIARD", x * 2, y + 1);
					x += 3;
				}
				else if (square == 'L') {
					roomName("LIBRARY ", x * 2, y + 1);
					x += 3;
				}
				else if (square == 'O') {
					roomName("LOUNGE", x * 2, y + 1);
					x += 2;
				}
				else if (square == 'H') {
					roomName("HALL", x * 2, y + 1);
					x += 1;
				}
				else if (square == 'S') {
					roomName(" STUDY", x * 2, y + 1);
					x += 2;
				}
			}
			//add wall to right side of house
			if (this.boardStrings[y].charAt(BOARD_WIDTH - 1) != '0') {
				this.asciiBoard[BOARD_WIDTH * 2][y + 1] = '|';
			}
			else {
				this.asciiBoard[BOARD_WIDTH * 2][y + 1] = ' ';
			}
		}
	}

	/**
	 * Set up the key displayed on the side of the board.
	 */
	private void keySetup() {
		this.key = new String[BOARD_HEIGHT];
		this.key[0] = "MS = Miss Scarlett";
		this.key[1] = "PP = Professor Plum";
		this.key[2] = "MP = Mrs Peacock";
		this.key[3] = "RG = Reverend Green";
		this.key[4] = "CM = Colonel Mustard";
		this.key[5] = "MW = Mrs White";
		this.key[6] = "";
		this.key[7] = "cs = Candlestick";
		this.key[8] = "dg = Dagger";
		this.key[9] = "lp = Lead Pipe";
		this.key[10] = "rv = Revolver";
		this.key[11] = "ro = Rope";
		this.key[12] = "sp = Spanner";
		for (int i = 13; i < BOARD_HEIGHT; i++) {
			this.key[i] = "";
		}
	}

	/**
	 * Add the name of the room to the ascii char array.
	 * @param name The name to write out.
	 * @param startX The x index to write the first character.
	 * @param y The y index to write out the characters.
	 */
	private void roomName(String name, int startX, int y) {
		for (int i = 0; i < name.length(); i++) {
			this.asciiBoard[startX + i][y] = name.charAt(i);
		}
	}

	/**
	 * Gets the player at a given location.
	 *
	 * @param x An int representing the location on the x axis.
	 * @param y An int representing the location on the y axis.
	 * @return Returns a string representing a character at the location, if there is a player there.
	 */

	private String characterAt(int x, int y) {
		Location location = this.boardSquares[x][y];
		if (location == null) {return null;}
		for (Token t : location.getTokens()) {
			if (t.isCharacter()) {
				return t.getDisplay();
			}
		}
		return null;
	}

	/**
	 * Returns the character at a certain coordinate, 0 if out of bounds.
	 * @param x The xOrdinate
	 * @param y The yOrdinate
	 * @return char
	 */
	private char boardChar(int x, int y) {
		if (x < 0 || x == BOARD_WIDTH || y < 0 || y == BOARD_HEIGHT) {
			return '0';
		}
		return this.boardStrings[y].charAt(x);
	}

	/**
	 * Returns the square at a specified coordinate.
	 * @param x The xOrdinate
	 * @param y The yOrdinate
	 * @return Square - The square at this coordinate.
	 */
	public Square getSquare(int x, int y) {
		return this.boardSquares[x][y];
	}

	/**
	 * Draw the board and key.
	 */
	public void draw() {
		for (int i = 0; i < BOARD_WIDTH * 2 + 1; i++) {
			System.out.print(this.asciiBoard[i][0]);
		}
		System.out.print('\n');
		for (int y = 0; y < BOARD_HEIGHT; y++) {
			for (int x = 0; x < BOARD_WIDTH; x++) {
				String character = characterAt(x, y);
				char c = this.asciiBoard[x * 2][y + 1];
				if (character != null) {
					System.out.print(character);
				}
				else if ((Character.isLetter(c) && Character.toLowerCase(c) == c)) {
					String characters = this.rooms.get(Character.toString(c)).getDisplay();
					if (characters != null) {
						System.out.print(characters);
						x += characters.length() / 2 - 1;
					}
					else {
						System.out.print("  ");
					}
				}
				else {
					System.out.print(this.asciiBoard[x * 2][y + 1]);
					System.out.print(this.asciiBoard[x * 2 + 1][y + 1]);
				}
			}
			System.out.print(this.asciiBoard[BOARD_WIDTH * 2][y + 1]);
			System.out.print("     " + this.key[y] + "\n");
		}
		System.out.print('\n');
	}

	/**
	 * Add a token to a place on the board.
	 * @param t Token to place
	 * @param x The xOrdinate
	 * @param y The yOrdinate
	 */
	public void addToken(Token t, int x, int y) {
		this.boardSquares[x][y].addToken(t);
	}

	/**
	 * Checks if a move is legal. If it is, does the move and returns true
	 * Otherwise, it does nothing and returns false.
	 *
	 * @param t Token to move.
	 * @param xDir Direction in x axis.
	 * @param yDir Direction in y axis.
	 * @param dist How far to move.
	 * @return True if move was valid, false if move illegal.
	 */
	public boolean moveToken(Token t, int xDir, int yDir, int dist) {
		Location location = t.getLocation();
		if (location instanceof Room) {
			return false;
		}
		for (int x = 0; x < BOARD_WIDTH; x++) {
			for (int y = 0; y < BOARD_HEIGHT; y++) {
				if (this.boardSquares[x][y] == location) {
					if (checkDir(x, y, xDir, yDir, dist)) {
						this.boardSquares[x + xDir * dist][y + yDir * dist].addToken(t);
						return true;
					}
					return false;
				}
			}
		}
		return false;
	}

	/**
	 * Recursive function to check if a move in a direction is legal.
	 * @param x The start xOrdinate.
	 * @param y The start yOrdinate.
	 * @param xDir The direction to move in x axis.
	 * @param yDir The direction to move in y axis.
	 * @param dist How far to move.
	 * @return
	 */
	private boolean checkDir(int x, int y, int xDir, int yDir, int dist) {
		if (this.boardSquares[x][y] == null) {
			return false;
		}
		if (dist > 0) {
			return checkDir(x + xDir, y + yDir, xDir, yDir, dist - 1);
		}
		if (this.boardSquares[x][y].getNumbTokens() == 0){
			return true;
		}
		return false;
	}

	/**
	 * Returns a map of Strings to Rooms.
	 * @return A Map of Strings to Rooms.
	 */
	public Map<String, Room> getRooms() {
		return this.rooms;
	}
}