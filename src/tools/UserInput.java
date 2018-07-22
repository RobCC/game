package tools;

import java.util.Scanner;

public class UserInput {
	
	public static char USER_ACCEPT 	= 'Y';
	public static char USER_DECLINE = 'N';
	
	public static final String ANSI_RED 	= "\u001B[31m";
	public static final String ANSI_RESET = "\u001B[0m";
	
	private static Scanner input;
	private static boolean ANSIColor = false;
	
	public static void setScanner(Scanner input) {
		UserInput.input = input;
	}
	
	public static void setTextColor(boolean ANSIColor) {
		UserInput.ANSIColor = ANSIColor;
	}
	
	public static String getUserInput(String message) {
		String 	response;
		
		System.out.println(message);
		System.out.print("> ");
		response = input.nextLine();
		return response;
	}
		
	//TODO
	//Change to getUserKey with map<char, String>
	
	public static char askUserOptions(String message) {
		return askUserOptions(message, USER_ACCEPT, USER_DECLINE);
	}
	
	public static char askUserOptions(String message, char... options) {
		boolean validInput = true;
		String sResponse;
		char response = 0;
		
		System.out.print(message + " ");
		
		for(int i = 0; i < options.length; i++) {
			System.out.print("[" + ("" + options[i]).toUpperCase() + "] ");
		}
		System.out.println("");
		System.out.print("> ");
		
		
		do {
			sResponse 	= input.nextLine().toUpperCase();
			
			if (isResponseEmpty(sResponse)) {
				validInput = false;
				continue;
			}
			
			response 		= sResponse.charAt(0);
			validInput 	= validateInput(options, response);
			
			if (!validInput) {
				printOptionNotAvailable();
			}
		} while(!validInput);
		
		return response;
	}
	
	private static void printOptionNotAvailable() {
		if (ANSIColor)
			System.out.println(ANSI_RED + "Option not available. Please try again" + ANSI_RESET);
		else
			System.out.println("Option not available. Please try again");
		System.out.print("> ");
	}
	
	private static boolean isResponseEmpty(String response) {
		if (response.isEmpty()) {
			System.out.print("> ");
			return true;
		}
		return false;
	}
	
	private static boolean validateInput(char[] options, char response) {
		for(char c : options) {
			if (c == response) {
				return true;
			}
		}
		return false;
	}
}
