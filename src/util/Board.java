package util;

import java.util.HashSet;
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

	private Square[][] boardSquares;
	private Set<Room> rooms; //Set of the rooms on the board.
	private Set<Token> tokens; //Set of person and weapon tokens.

	/**
	0 = empty space
	1 = square
	2 = square next to door
	3 = room
	4 = room next to door
	k = kitchen name
	b = ball room name
	c = conser- name
	v = vatory name
	d = dining name
	r = room name
	B = billiard name
	l = library name
	L = lounge name
	h = hall name
	 = study name
	**/

	String[] boardStrings = {
			"000000000100001000000000",
			"333333011133331110333333",
			"3333331133333333113c3333",
			"3k33331133333333113v3333",
			"3333331133b3333311433333",
			"333333124333333321233330",
			"033343113333333311111111",
			"111121113433334311111110",
			"011111111211112111333333",
			"3333311111111111124B3333",
			"33333333113333311133r333",
			"333333331133333111333333",
			"33d333342133333111333343",
			"333r33331133333111112120",
			"333333331133333111334330",
			"333333431133333113333333",
			"0111112111333331243l3333",
			"111111111112211113333333",
			"011111211334433111333330",
			"333333411333333111111111",
			"333333311333334212111110",
			"33L33331133h333114333333",
			"3333333113333331133s3333",
			"333333311333333113333333",
			"333333010033330010333333",
	};

	public Board() {
		this.boardSquares = new Square[BOARD_WIDTH][BOARD_HEIGHT];
		this.rooms = new HashSet<>();
		this.tokens = new HashSet<>();
		populateBoard();
	}

	/**
	 * Populates a two dimensional array representing the squares of the board.
	 * '0' represents blank areas, '1' represents squares that a player can move on
	 * and to, '2' represents a door into a room on a square (so the player can still
	 * move to this square), and '3' represents a room.
	 */

	private void populateBoard() {

	}

	private String characterAt(int x, int y) {
		Square square = this.boardSquares[x][y];
		if (square == null) {return null;}
		for (Token t : square.getTokens()) {
			//if t is a character, return t
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
		for (int i = 0; i < BOARD_WIDTH; i++) {
			if (this.boardStrings[0].charAt(i) == '0') {
				System.out.print("  ");
			}
			else {
				System.out.print(" _");
			}
		}
		System.out.print("\n");
		for (int y = 0; y < BOARD_HEIGHT; y++) {
			for (int x = 0; x < BOARD_WIDTH; x++) {
				String character = characterAt(x, y);
				char square = boardChar(x, y);
				char left = boardChar(x - 1, y);
				char down = boardChar(x, y + 1);
				if (square == '0') {
					if (left == '0') {
						System.out.print(" ");
					}
					else {
						System.out.print("|");
					}
					if (down != '0') {
						System.out.print("_");
					}
					else {
						System.out.print(" ");
					}
				}
				else if (square == '1') {
					System.out.print("|");
					if (character != null) {
						//draw character
					}
					else {
						System.out.print("_");
					}
				}
				else if (square == '2') {
					if (left == '4') {
						System.out.print(":");
					}
					else {
						System.out.print("|");
					}
					if (character != null) {
						//draw character
					}
					else if (down == '4') {
						System.out.print(".");
					}
					else {
						System.out.print("_");
					}
				}
				else if (square == '3') {
					if (left == '3' || left == '4') {
						System.out.print(" ");
					}
					else {
						System.out.print("|");
					}
					if (down == '0' || down == '1' || down == '2')
						System.out.print("_");
					else {
						System.out.print(" ");
					}
				}
				else if (square == '4') {
					if (left == '0' || left == '1') {
						System.out.print("|");
					}
					else if (left == '2') {
						System.out.print(":");
					}
					else {
						System.out.print(" ");
					}
					if (down == '0' || down == '1') {
						System.out.print("_");
					}
					else if (down == '2') {
						System.out.print(".");
					}
					else {
						System.out.print(" ");
					}
				}
				else if (square == 'k') {
					System.out.print(" KITCHEN");
					x += 3;
				}
				else if (square == 'b') {
					System.out.print("BALL ROOM ");
					x += 4;
				}
				else if (square == 'c') {
					System.out.print(" CONSER-");
					x += 3;
				}
				else if (square == 'v') {
					System.out.print(" VATORY ");
					x += 3;
				}
				else if (square == 'd') {
					System.out.print(" DINING ");
					x += 3;
				}
				else if (square == 'r') {
					System.out.print("ROOM");
					x += 1;
				}
				else if (square == 'B') {
					System.out.print("BILLIARD");
					x += 3;
				}
				else if (square == 'l') {
					System.out.print("LIBRARY ");
					x += 3;
				}
				else if (square == 'L') {
					System.out.print("LOUNGE");
					x += 2;
				}
				else if (square == 'h') {
					System.out.print("HALL");
					x += 1;
				}
				else if (square == 's') {
					System.out.print(" STUDY");
					x += 2;
				}
			}
			if (this.boardStrings[y].charAt(BOARD_WIDTH - 1) != '0') {
				System.out.print("|");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
}