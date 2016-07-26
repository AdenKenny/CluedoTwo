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

	String[] boardStrings = {
			"000000000100001000000000",
			"333333011133331110333333",
			"333333113333333311333333",
			"333333113333333311333333",
			"333333113333333311333333",
			"333333123333333321233330",
			"033333113333333311111111",
			"111121113333333311111110",
			"011111111211112111333333",
			"333331111111111112333333",
			"333333331133333111333333",
			"333333331133333111333333",
			"333333332133333111333333",
			"333333331133333111112120",
			"333333331133333111333330",
			"333333331133333113333333",
			"011111211133333123333333",
			"111111111112211113333333",
			"011111211333333111333330",
			"333333311333333111111111",
			"333333311333333212111110",
			"333333311333333113333333",
			"333333311333333113333333",
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

	public void draw() {
		for (int i = 0; i < BOARD_WIDTH; i++) {
			if (boardStrings[0].charAt(i) == '0') {
				System.out.print("  ");
			}
			else {
				System.out.print(" _");
			}
		}
		System.out.print("\n");
		for (int y = 0; y < BOARD_HEIGHT; y++) {
			char last = '0';
			for (int x = 0; x < BOARD_WIDTH; x++) {
				char square = boardStrings[y].charAt(x);
				if (square == '0') {
					if (square == last) {
						System.out.print(" ");
					}
					else {
						System.out.print("|");
					}
					if (y < BOARD_HEIGHT - 1) {
						System.out.print("_");
					}
					else {
						System.out.print(" ");
					}
				}
				else if (square == '1') {
					System.out.print("|_");
				}
				else if (square == '2') {
					if (boardStrings[y + 1].charAt(x) == '3') {
						System.out.print("| ");
					}
					else if (boardStrings[y].charAt(x - 1) == '3') {
						System.out.print(" _");
					}
					else {
						System.out.print("|_");
					}
				}
				else if (square == '3') {
					if (square == last || last == 2) {
						System.out.print(" ");
					}
					else {
						System.out.print("|");
					}
					if (y == BOARD_HEIGHT - 1 || boardStrings[y + 1].charAt(x) == '0' || boardStrings[y + 1].charAt(x) == '1')
						System.out.print("_");
					else {
						System.out.print("x");
					}
				}
				last = square;

			}
			if (boardStrings[y].charAt(BOARD_WIDTH - 1) != '0') {
				System.out.print("|");
			}
			System.out.print("\n");
		}
	}
}
