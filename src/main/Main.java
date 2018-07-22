package main;

import java.util.Scanner;
import player.Player;
import tools.UserInput;

/**
 * @author RobCC
 */

public class Main {
	
	public static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		UserInput.setScanner(input);

		printMenu();
		setPlayer();
		input.close();
	}
	
	private static void printMenu() {
		System.out.println("Welcome to the game!");
	}
	
	private static void setPlayer() {
		boolean isPlayerSet = false;
		String response;
		
		Player.enterPlayerName();
		Player.enterPlayerAge();
		Player.describe();
	}

}
