package player;

import tools.UserInput;

public class Player {
	private static Player player;
	private static String name;
	private static int age;
	
	private Player() {}
	
	//TODO
	// Add class, choose background, choose faction
	
	public static Player getInstance() {
		if(player == null)
			player = new Player();
		return player;
	}
	
	public void setName() {
		char confirm;
		String name;
		
		do {
			name 		= UserInput.getUserInput("Please enter your character name");
			confirm = UserInput.printOptions("Are you sure you want this name?");
		} while(confirm == UserInput.USER_DECLINE);
		
		Player.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
	}
	
	public void setAge() {
		char confirm;
		boolean validAge = true;
		String age;
		
		do {
			
			if (validAge == false) {
				System.out.println("This is not a valid age, dummy");
			}
			
			age 			= UserInput.getUserInput("Please enter your character age");
			confirm 	= UserInput.printOptions("Are you sure you want this age?");
			validAge 	= isAgeValid(age);
		} while((confirm == UserInput.USER_DECLINE) || !validAge);
		
		Player.age = Integer.parseInt(age);
	}
	
	public boolean isAgeValid(String age) {
		return age.matches("^\\d+$");
	}
	
	public void describe() {
		System.out.print("Your name shall be " + name + ". ");
		System.out.print("Your age will be " + age + ".");
		System.out.println();
	}
}
