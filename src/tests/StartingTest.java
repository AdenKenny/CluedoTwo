package tests;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

import cluedo.Cluedo;

public class StartingTest {

	@Test
	public void testDice6() {
		for (int i = 0; i < 1000; i++) {
			int roll = Cluedo.rollDice6();
			assert roll >= 1 && roll <= 6;
		}
	}

	@Test
	public void testDice12() {
		for (int i = 0; i < 1000; i++) {
			int roll = Cluedo.rollDice();
			assert roll >= 2 && roll <= 12;
		}
	}

	@Test
	public void testEntertainment() {
		new Cluedo();
		System.out.println("Was the game fun?");
		String reply = "";
		try {
			Scanner in = new Scanner(System.in);
			reply = in.nextLine();
		}
		catch(RuntimeException e) {
			fail();
		}
		assertEquals(reply, "yes");
	}

}
