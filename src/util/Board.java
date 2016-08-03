package util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

	private Location[][] boardLocation;
	private Map<String, Room> rooms; //Set of the rooms on the board.
	private Set<Token> tokens; //Set of person and weapon tokens.
	
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
	**/

	String[] boardStrings = {
			"000000000100001000000000",
			"333333011133331110333333",
			"3333331133333333113C3333",
			"3K33331133333333113V3333",
			"3k33331133B3333311433333",
			"333333124333333321233330",
			"033343113333333311111111",
			"111121113433334311111110",
			"011111111211112111333333",
			"3333311111111111124I3333",
			"33333333113333311133R333",
			"333333331133333111333333",
			"33D333342133333111333343",
			"333R33331133333111112120",
			"333333331133333111334330",
			"333333431133333113333333",
			"0111112111333331243L3333",
			"111111111112211113333333",
			"011111211334433111333330",
			"333333411333333111111111",
			"333333311333334212111110",
			"33O33331133H333114333333",
			"3333333113333331133S3333",
			"333333311333333113333333",
			"333333010033330010333333",
	};

	public Board() {
		this.tokens = new HashSet<>();
		populateBoard();
		roomSetup();
		asciiBoardSetup();
	}

	/**
	 * Populates a two dimensional array representing the squares of the board.
	 * '0' represents blank areas, '1' represents squares that a player can move on
	 * and to, '2' represents a door into a room on a square (so the player can still
	 * move to this square), and '3' represents a room.
	 */
	private void populateBoard() {
		this.boardLocation = new Square[BOARD_WIDTH][BOARD_HEIGHT];
		for (int y = 0; y < BOARD_HEIGHT; y++) {
			for (int x = 0; x < BOARD_WIDTH; x++) {
				if (boardChar(x, y) == '1') {
					this.boardLocation[x][y] = new Square();
				}
			}
		}
	}
	
	private void roomSetup() {
		this.rooms = new HashMap<>();
		Map<String, Location> adjacent = new HashMap<>();
		this.rooms.put("", new Room("", adjacent));
				/*
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
	*/
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

	private String characterAt(int x, int y) {
		Location location = this.boardLocation[x][y];
		if (location == null) {return null;}
		for (Token t : location.getTokens()) {
			if (t.isCharacter()) {
				return t.getDisplay();
			}
		}
		return null;
	}
	
	private String charactersInRoom(char c) {
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
					String characters = charactersInRoom(c);
					if (characters != null) {
						System.out.print(characters);
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
}