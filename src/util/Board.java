package util;

import java.util.HashMap;
import java.util.Map;

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
	private Map<String, Square> startingLocations; //Map of character names to locations.
	
	private char[][] asciiBoard;

	/**
	0 = empty space
	1 = square
	2 = square next to door
	3 = room
	4 = room next to door
	K = kitchen name
	B = ball room name
	C = conser- name
	V = vatory name
	D = dining name
	R = room name
	I = billiard name
	L = library name
	O = lounge name
	H = hall name
	S = study name
	lower case letters are where characters are displayed in room.
	They correspond to the uppercase letters used for names.
	**/

	String[] boardStrings = {
			"000000000100001000000000",
			"333333011133331110333333",
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
			"333333010033330010333333",
	};

	public Board() {
		populateBoard();
		roomSetup();
		addRoomAccess();
		asciiBoardSetup();
		startingLocationSetup();
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
	
	private void addRoomAccess() {
		this.boardSquares[4][7].addAdjacent("Kitchen", rooms.get("k"));
		
		this.boardSquares[7][5].addAdjacent("Ball Room", rooms.get("b"));
		this.boardSquares[9][8].addAdjacent("Ball Room", rooms.get("b"));
		this.boardSquares[14][8].addAdjacent("Ball Room", rooms.get("b"));
		
		this.boardSquares[18][5].addAdjacent("Conservatory", rooms.get("c"));
		
		this.boardSquares[8][12].addAdjacent("Dining Room", rooms.get("d"));
		this.boardSquares[6][16].addAdjacent("Dining Room", rooms.get("d"));
		
		this.boardSquares[17][9].addAdjacent("Billiard Room", rooms.get("i"));
		this.boardSquares[22][13].addAdjacent("Billiard Room", rooms.get("i"));
		
		this.boardSquares[20][13].addAdjacent("Library", rooms.get("l"));
		this.boardSquares[16][16].addAdjacent("Library", rooms.get("l"));
		
		this.boardSquares[6][18].addAdjacent("Lounge", rooms.get("o"));
		
		this.boardSquares[11][17].addAdjacent("Hall", rooms.get("h"));
		this.boardSquares[12][17].addAdjacent("Hall", rooms.get("h"));
		this.boardSquares[15][20].addAdjacent("Hall", rooms.get("h"));
		
		this.boardSquares[17][20].addAdjacent("Study", rooms.get("s"));
	}
	
	private void asciiBoardSetup() {
		this.asciiBoard = new char[2 * BOARD_WIDTH + 1][2 * BOARD_HEIGHT + 1];
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
		for (int y = 0; y < BOARD_HEIGHT; y++) {
			for (int x = 0; x < BOARD_WIDTH; x++) {
				char square = boardChar(x, y);
				char left = boardChar(x - 1, y);
				char down = boardChar(x, y + 1);
				if (Character.isLetter(square) && Character.toLowerCase(square) == square) {
					this.asciiBoard[x * 2][y + 1] = square;
					this.asciiBoard[x * 2 + 1][y + 1] = ' ';
				}
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
				else if (square == '1') {
					this.asciiBoard[x * 2][y + 1] = '|';
					this.asciiBoard[x * 2 + 1][y + 1] = '_';
				}
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
				else if (square == '3') {
					if (left == '3' || left == '4' || (Character.isLetter(left) && Character.toLowerCase(left) == left)) {
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
			if (this.boardStrings[y].charAt(BOARD_WIDTH - 1) != '0') {
				this.asciiBoard[BOARD_WIDTH * 2][y + 1] = '|';
			}
			else {
				this.asciiBoard[BOARD_WIDTH * 2][y + 1] = ' ';
			}
		}
	}
	
	private void roomName(String name, int startX, int y) {
		for (int i = 0; i < name.length(); i++) {
			this.asciiBoard[startX + i][y] = name.charAt(i);
		}
	}
	
	private void startingLocationSetup() {
		startingLocations = new HashMap<>();
		startingLocations.put("Mrs White", boardSquares[9][0]);
		startingLocations.put("Reverend Green", boardSquares[14][0]);
		startingLocations.put("Mrs Peacock", boardSquares[23][6]);
		startingLocations.put("Professor Plum", boardSquares[23][19]);
		startingLocations.put("Miss Scarlett", boardSquares[7][24]);
		startingLocations.put("Colonel Mustard", boardSquares[0][17]);
	}

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

	private char boardChar(int x, int y) {
		if (x < 0 || x == BOARD_WIDTH || y < 0 || y == BOARD_HEIGHT) {
			return '0';
		}
		return this.boardStrings[y].charAt(x);
	}

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
					String characters = rooms.get(Character.toString(c)).getDisplay();
					if (characters != null) {
						System.out.print(characters);
						x += characters.length() / 2;
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
			System.out.print('\n');
		}
		System.out.print('\n');
	}
	
	public Location getStartingLocation(String name) {
		return startingLocations.get(name);
	}
	
	public void addToken(Token t, int x, int y) {
		boardSquares[x][y].addToken(t);
	}
	
	public boolean moveToken(Token t, int xDir, int yDir, int dist) {
		Location location = t.getLocation();
		if (location instanceof Room) {
			return false;
		}
		for (int x = 0; x < BOARD_WIDTH; x++) {
			for (int y = 0; y < BOARD_HEIGHT; y++) {
				if (boardSquares[x][y] == location) {
					if (checkDir(x, y, xDir, yDir, dist)) {
						boardSquares[x + xDir * dist][y + yDir * dist].addToken(t);
						return true;
					}
					return false;
				}
			}
		}
		return false;
	}
	
	private boolean checkDir(int x, int y, int xDir, int yDir, int dist) {
		if (boardSquares[x][y] == null) {
			return false;
		}
		if (dist > 0) {
			return checkDir(x + xDir, y + yDir, xDir, yDir, dist - 1);
		}
		if (boardSquares[x][y].getNumbTokens() == 0){
			return true;
		}
		return false;
	}
}