package util;

import java.util.HashSet;
import java.util.Set;

public class Board {
	private static final int BOARD_WIDTH = 24;
	private static final int BOARD_HEIGHT = 25;
	
	private Square[][] boardSquares;
	private Set<Room> rooms; //Set of the rooms on the board.
	private Set<Token> tokens; //Set of person and weapon tokens.
	
	public Board() {
		this.boardSquares = new Square[BOARD_WIDTH][BOARD_HEIGHT];
		this.rooms = new HashSet<>();
		this.tokens = new HashSet<>();
		populateBoard();
	}
	
	private void populateBoard() {
		String[] boardStrings = {
				"000000000100001000000000",
				"333333011133331110333333",
				"000000110000000011000000",
				"000000110000000011000000",
				"000000110000000011000000",
				"000000120000000021200000",
				"000000110000000011111111",
				"111121110000000011111110",
				"011111111211112111000000",
				"000001111111111112000000",
				"000000001100000111000000",
				"000000001100000111000000",
				"000000002100000111000000",
				"000000001100000111112120",
				"000000001100000111000000",
				"000000001100000110000000",
				"011111211100000120000000",
				"111111111112211110000000",
				"",
				"",
				"",
				"",
				"",
				"",
				"",
		};
	}
}
