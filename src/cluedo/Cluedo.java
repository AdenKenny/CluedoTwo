package cluedo;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import util.Board;
import util.Triplet;

public class Cluedo {

	private Triplet murderInfo; //Triplet of the actual murder details.
	private Board board; //The game board.
	private Set<Player> players;

	public Cluedo() {
	
		Set<String> charNames = new HashSet<>();
		
		charNames.add("Miss Plum");
		
		
		Scanner in = new Scanner(System.in);
		int numbPlayers = 0;
		
		System.out.println("Enter the number of human players: ");
		
		String tmp = in.next();

		try {
			 numbPlayers = Integer.parseInt(tmp);
		}
		
		catch (NumberFormatException e) { //If input is not convertable to an int. Invalid input.
			System.out.println("This is not a valid number of players");
		}
		
		//TODO shift this to the setup player method.
		for(int i = 0; i < numbPlayers; i++) { //Loop through the amount of human players.
			String playerName = in.nextLine();
			
			if(!charNames.contains(playerName.trim())) { //TODO reselect character.
				System.out.println("This is not a valid character.");
			}
			
			for(Player p : this.players) {
				if(p.getToken().getName().equals(playerName)) {
					System.out.println("This character has already been taken");
				}
			}
			
		}
			
	}
	
	/**
	 * Sets up a human player and adds it to the set of players to be passed to the board
	 * when the game is setup.
	 * 
	 * @param playerNumb - The number of the player to be setup.
	 * @return newPlayer - A setup player that will be added to the set of players in the game.
	 */
	
	public Player setupPlayer(int playerNumb) {
		
		
		return null;
	}

	
	
	
	/**
	 * Gets a random number between 1 and 6 representing a dice roll
	 * for moving around the board.
	 *
	 * @return An int between 1 and 6.
	 */

	public int rollDice() {
		return (int)(Math.random() * 6 + 1);
	}

	public static void main(String[] args) {
		new Cluedo();
	}

}
