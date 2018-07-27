package main;

import java.util.Scanner;

import characters.Player;
import tools.UserInput;

public class Game {
  private static Scanner input    = new Scanner(System.in);
  public static final String NAME = "GAME_NAME";
  private static Game game;
  private Player player;
  
  private Game() {}
  
  public static Game getInstance() {
    if(game == null)
      game = new Game();
    return game;
  }
  
  public void start() {
    UserInput.setScanner(input);
    UserInput.setWidth(110);
    printMenu();
//    setPlayer();
    
    /* 
     * run intro
     * run story
     */
    
    input.close();
  }
  
  public void printMenu() {
    char keyPressed;
    
    do {
      keyPressed = UserInput.printMenu(NAME);
      
      switch(keyPressed) {
      case 'C':
        rollCredits();
        break;
      case 'N':
        setPlayer();
        break;
      }
    } while(keyPressed != 'E');
  }
	
  public void setPlayer() {
    boolean isPlayerSet = false;
    String response;
    
    player = Player.getInstance();
    
    player.setName();
    player.setAge();
    player.setGender();
    player.setFaction();
    player.describe();
  }
  
  private void rollCredits() {
    System.out.println("Made by RobCC. Started on July 18th 2018.");
    System.out.println();
  }
}
