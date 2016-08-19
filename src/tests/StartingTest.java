package tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.junit.Test;

import cluedo.GameOfCluedo;

/**
 * A class containing many JUnit tests that cover a wide variety of cases and
 * can minimize the chances of bugs in this glorious game of Cluedo. These tests
 * 100% definitely cover all possible cases in the game. But overall the spirit of
 * these tests is not to check for bugs, it is to find the inner meaning of the game
 * of Cluedo. Cluedo and all it stands for cannot be simply represented by specifications
 * or unit tests. These could not possibly capture the joy, wonder, and entertainment that
 * has been brought to millions of smiling happy people. This led us to develop tests that
 * could answer the big and important questions that can be presented by Cluedo rather than
 * getting bogged down on bugs and programming guidelines.
 *
 * @author Aden Kenny and Simon Pope.
 *
 */

public class StartingTest {

	/**
	 * Tests to make sure d6 roll meets required specifications.
	 * I.e. the roll is between 1 and 6 and not higher than 6 or lower than 1.
	 *
	 */

	@Test
	public void testDice6() {
		for (int i = 0; i < 1000; i++) {
			int roll = GameOfCluedo.rollD6();
			assert roll >= 1 && roll <= 6;
		}
	}

	/**
	 * Tests to make sure d12 roll meets required specifications.
	 * I.e. the roll is between 2 and 12 and not higher than 12 or lower than 2.
	 *
	 */

	@Test
	public void testDice12() {
		for (int i = 0; i < 1000; i++) {
			int roll = GameOfCluedo.roll2D6();
			assert roll >= 2 && roll <= 12;
		}
	}

	/**
	 * Make sure rollDice6() is random enough.
	 */

	@Test
	public void testRandomness6() {
		Map<Integer, Integer> map = new HashMap<>();

		for(int i = 0; i < 10000; i++) {
			int roll = GameOfCluedo.rollD6();
			if(map.containsKey(roll)) {
				int numb = map.get(roll);
				numb++;
				map.put(roll, numb);
			}

			else {
				map.put(roll, 1);
			}
		}

		for(Entry<Integer, Integer> e : map.entrySet()) {
			assert(e.getValue() >= 1300);
		}
	}

	/**
	 * Make sure rollDice() is random enough.
	 */

	@Test
	public void testRandomness12() {
		Map<Integer, Integer> map = new HashMap<>();

		for(int i = 0; i < 10000; i++) {
			int roll = GameOfCluedo.roll2D6();
			if(map.containsKey(roll)) {
				int numb = map.get(roll);
				numb++;
				map.put(roll, numb);
			}

			else {
				map.put(roll, 1);
			}
		}
		for(Entry<Integer, Integer> e : map.entrySet()) {
			System.out.println(e.getValue());
			assert(e.getValue() >= 200);
		}
	}

	/**
	 * The most important part of a game is the enjoyment of the user.
	 * There is no point playing a game if it is not fun. Someone
	 * might create an amazingly functionally complete Cluedo that
	 * is the most boring game ever. So we decided the most important
	 * test was for you, the user, to play the game, and rate your
	 * enjoyment. This gives a much better picture of the success of
	 * the game than some silly bug testing. If this test were to fail,
	 * we have not only failed the simple test case, we have failed
	 * ourselves and most importantly we have failed you.
	 */

	@Test
	public void testEntertainment() {
		new GameOfCluedo();
		System.out.println("Was the game fun?");
		String reply = "";

		try {
			Scanner in = new Scanner(System.in);
			reply = in.nextLine();
		}

		catch (RuntimeException e) {
			fail();
		}

		assertEquals(reply, "yes");
	}

}
