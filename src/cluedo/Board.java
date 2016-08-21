package cluedo;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import items.Token;
import location.Location;
import location.Room;
import location.Square;
import util.Pair;

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

	/**
	 * 0 = empty space
	 * 1 = square
	 * k = kitchen name
	 * b = ball room
	 * c = conservatory
	 * d = dining room
	 * i = billiard name
	 * l = library name
	 * o = lounge name
	 * h = hall name
	 * s = study name
	 *
	 **/

	String[] boardStrings = {
			"000000000100001000000000",
            "kkkkks0111bbbb1110cccccc",
            "kkkkkk11bbbbbbbb11cccccc",
            "kkkkkk11bbbbbbbb11cccccc",
            "kkkkkk11bbbbbbbb11cccccc",
            "kkkkkk11bbbbbbbb111ccco0",
			"0kkkkk11bbbbbbbb11111111",
			"11111111bbbbbbbb11111110",
			"011111111111111111iiiiii",
			"ddddd1111111111111iiiiii",
			"dddddddd1100000111iiiiii",
			"dddddddd1100000111iiiiii",
			"dddddddd1100000111iiiiii",
			"dddddddd1100000111111110",
			"dddddddd1100000111lllll0",
			"dddddddd110000011lllllll",
			"01111111110000011lllllll",
			"11111111111111111lllllll",
			"011111111334433111lllll0",
			"coooooo11hhhhhh111111111",
			"ooooooo11hhhhhh111111110",
			"ooooooo11hhhhhh11ssssssk",
			"ooooooo11hhhhhh11sssssss",
			"ooooooo11hhhhhh11sssssss",
			"oooooo0100hhhh0010ssssss"
	};

	public Board() {
		populateBoard();
		roomSetup();
		addRoomAccess();
	}

	private void populateBoard() {
		this.boardSquares = new Square[BOARD_WIDTH][BOARD_HEIGHT];
		for (int y = 0; y < BOARD_HEIGHT; y++) {
			for (int x = 0; x < BOARD_WIDTH; x++) {
				char square = boardChar(x, y);
				if (square == '1') {
					this.boardSquares[x][y] = new Square(x, y);
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
		Room kitchen = new Room("Kitchen", adjacent, 1, 2);
		this.rooms.put("k", kitchen);

		adjacent = new HashMap<>();
		adjacent.put("left", this.boardSquares[7][5]);
		adjacent.put("down left", this.boardSquares[9][8]);
		adjacent.put("down right", this.boardSquares[14][8]);
		this.rooms.put("b", new Room("Ball Room", adjacent, 9, 3));

		adjacent = new HashMap<>();
		adjacent.put("down", this.boardSquares[18][5]);
		Room conservatory = new Room("Conservatory", adjacent, 19, 1);
		this.rooms.put("c", conservatory);

		adjacent = new HashMap<>();
		adjacent.put("right", this.boardSquares[8][12]);
		adjacent.put("down", this.boardSquares[6][16]);
		this.rooms.put("d", new Room("Dining Room", adjacent, 1, 11));

		adjacent = new HashMap<>();
		adjacent.put("left", this.boardSquares[17][9]);
		adjacent.put("down", this.boardSquares[22][13]);
		this.rooms.put("i", new Room("Billiard Room", adjacent, 19, 9));

		adjacent = new HashMap<>();
		adjacent.put("up", this.boardSquares[20][13]);
		adjacent.put("left", this.boardSquares[16][16]);
		this.rooms.put("l", new Room("Library", adjacent, 19, 15));

		adjacent = new HashMap<>();
		adjacent.put("up", this.boardSquares[6][18]);
		adjacent.put("Conservatory", conservatory);
		Room lounge = new Room("Lounge", adjacent, 1, 23);
		conservatory.addAdjacent("Lounge", lounge);
		this.rooms.put("o", lounge);

		adjacent = new HashMap<>();
		adjacent.put("up left", this.boardSquares[11][17]);
		adjacent.put("up right", this.boardSquares[12][17]);
		adjacent.put("right", this.boardSquares[15][20]);
		this.rooms.put("h", new Room("Hall", adjacent, 10, 19));

		adjacent = new HashMap<>();
		adjacent.put("up", this.boardSquares[17][20]);
		adjacent.put("Kitchen", kitchen);
		Room study = new Room("Study", adjacent, 18, 22);
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
	 * Gets the image for a player at a given location.
	 *
	 * @param x An int representing the location on the x axis.
	 * @param y An int representing the location on the y axis.
	 * @return Returns a string representing a character at the location, if there is a player there.
	 */

	public Image characterAt(int x, int y) {
		Location location = this.boardSquares[x][y];
		if (location == null) {return null;}
		for (Token t : location.getTokens()) {
			return t.getImage();
		}
		return null;
	}

	/**
	 * Returns the character at a certain coordinate on the boardStrings, 0 if out of bounds.
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
	 * Add a token to a place on the board.
	 * @param t Token to place
	 * @param x The xOrdinate
	 * @param y The yOrdinate
	 */
	public void addToken(Token t, int x, int y) {
		this.boardSquares[x][y].addToken(t);
	}

	/**
	 * Moves a token around the board on a user click. If move is possible,
	 * it performs the move and returns true and how far the move was.
	 * If the move isn't possible, no move is made, and it returns false 
	 * and a message as to why the move failed.
	 * 
	 * @param t The token that is to be moved.
	 * @param destX The destination on the x axis.
	 * @param destY The destination on the y axis.
	 * @param maxDist The maximum amount of distance that the token can move.
	 * @return Pair with either an int or a string with the required message.
	 */
	public Pair<Boolean, Object> moveToken(Token t, int destX, int destY, int maxDist) {
		Location current = t.getLocation();
		
		Square destination = this.boardSquares[destX][destY];
		
		if (destination == null) {
			char boardChar = boardChar(destX, destY);
			
			if (boardChar == '0') {
				return new Pair<>(false, "");
			}
			
			Room roomDest = getRoom(destX, destY);
			
			if (!roomDest.getAdjacent().containsValue(current)) {
				return new Pair<>(false, "You have to be standing at the door to enter a room.");
			}
			
			roomDest.addToken(t);
			return new Pair<>(true, 1);
		}
		
		if (current instanceof Room) {
			
			if (!current.getAdjacent().containsValue(destination)) {
				return new Pair<>(false, "Start by moving to a square outside a door.");
			}
			
			destination.addToken(t);
			return new Pair<>(true, 1);
		}
		
		Square currentSquare = (Square) current;
		int currentX = currentSquare.getX();
		int currentY = currentSquare.getY();
		
		if (currentX != destX && currentY != destY) {
			return new Pair<>(false, "You can only move in a straight line with each click.");
		}
		
		if (currentX == destX && currentY == destY) {
			return new Pair<>(false, "You can't move on the spot.");
		}
		
		int diffX = destX - currentX;
		int diffY = destY - currentY;
		int stepX = (diffX != 0) ? diffX / Math.abs(diffX) : 0;
		int stepY = (diffY != 0) ? diffY / Math.abs(diffY) : 0;
		
		if (diffX * stepX > maxDist || diffY * stepY > maxDist) {
			return new Pair<>(false, "You can't move that far.");
		}
		
		int x = currentX + stepX;
		int y = currentY + stepY;
		
		while((x - destX) * stepX <= 0 && (y - destY) * stepY <= 0) {
			Square square = this.boardSquares[x][y];
			
			if (square == null || !square.getTokens().isEmpty()) {
				return new Pair<>(false, "You can't move that way. Something is blocking you.");
			}
			
			x += stepX;
			y += stepY;
		}
		
		destination.addToken(t);
		
		return new Pair<>(true, Math.abs(diffX + diffY));
	}
	
	private Room getRoom(int x, int y) {
		return this.rooms.get(boardChar(x, y) + "");
	}

	/**
	 * Returns a map of Strings to Rooms.
	 * @return A Map of Strings to Rooms.
	 */
	public Map<String, Room> getRooms() {
		return this.rooms;
	}
}