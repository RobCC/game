package tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class UserInput {
  public static char USER_ACCEPT  = 'Y';
  public static char USER_DECLINE = 'N';
  private static Scanner input;
  private static int width;
  
  private UserInput() {}
  
  public static void setScanner(Scanner input) {
    UserInput.input = input;
  }
  
  public static void setWidth(int width) {
    UserInput.width = width;
  }
  
  public static void getWidth(int width) {
  UserInput.width = width;
  }
  
  public static char printMenu(String gameName) {
//    printMessage("This is the world we live now. Devastated, corrupted, filled with nightmares and whatnot. Since that day, everyuthing is different. Since that day, the day when the bombs feel from the sky, our world change forever. Because war, war never changes.");
    
    Map<String, String> menuOptions = new LinkedHashMap<>();
    menuOptions.put("N", "New Game");
    menuOptions.put("C", "Credits");
    menuOptions.put("E", "Exit");
    
    return printOptions(null, menuOptions, gameName);
  }
  
  public static char printOptions(String message, Map<String, String> options) {
    return printOptions(message, options, null);
  }
  
  public static char printOptions(String message, Map<String, String> options, String gameName) {
    boolean validInput = true;
    String keyPressed;
    char charPressed = 0;

    if(gameName != null) {
      printLimit(gameName);
    } else {
      printLimit(true);
    }
    
    if(message != null) {
      printMessage(message);
    }

    for (Entry<String, String> e : options.entrySet()) {
      String key = e.getKey();
      String msg = e.getValue();
      printMessage("[" + key + "] " + msg);
    }

    printLimit(false);
    System.out.print("> ");
    
    
    do {
      keyPressed = input.nextLine().toUpperCase();
      
      if (isResponseEmpty(keyPressed)) {
        validInput = false;
        continue;
      }
      
      charPressed = keyPressed.charAt(0);
      validInput  = validateInput(options, charPressed);
      
      if (!validInput) {
        printOptionNotAvailable();
        }
    } while(!validInput);
    
    return charPressed;
  }
  
  public static char printOptions(String message) {
    Map<String, String> menuOptions = new LinkedHashMap<>();
    menuOptions.put("Y", "Yes");
    menuOptions.put("N", "No");
    return printOptions(message, menuOptions);
  }
  
  public static String getUserInput(String message) {
    String response;
    
    printLimit(true);
    printMessage(message);
    printLimit(false);
    System.out.print("> ");
    response = input.nextLine();
    return response;
  }
  
  public static void printMessage(String message) {
    List<String> messageWords = new ArrayList<String>(Arrays.asList(message.split(" ")));
    final int AVAILABLE_SPACE = width - 2 - 2; //border chars, padding (1 before, 1 after)
    boolean newLine           = true;
    int remainingSpace        = AVAILABLE_SPACE;
    
    for (String word : messageWords) {
      int wordLength = word.length() + 1; //Adds post space word separator.
      
      if (newLine) {
        System.out.print("│ ");
        newLine = false;
      }
      
      if (remainingSpace >= wordLength) {
        System.out.print(word + " ");
        remainingSpace -= wordLength;
      } else {
        for (int i = 0; i < remainingSpace; i++) {
          System.out.print(" ");
        }
        System.out.println(" │");
        remainingSpace = AVAILABLE_SPACE;
        newLine = true;
      }
    }
    
    for (int i = 0; i < remainingSpace; i++) {
      System.out.print(" ");
    }
    System.out.println(" │");
  }
  
  private static boolean isResponseEmpty(String response) {
    if (response.isEmpty()) {
      System.out.print("> ");
      return true;
    }
    return false;
  }
  
  private static boolean validateInput(Map<String, String> options, char response) {
    for (Entry<String, String> e : options.entrySet()) {
      String key = e.getKey();
      
      if (key.charAt(0) == response)
        return true;
    }
    return false;
    }
  
  private static void printOptionNotAvailable() {
    System.out.println(" Option not available. Please try again");
    System.out.print("> ");
    }
  	
  /**
  * Prints limits of border header & footer
  */
  	
  private static void printLimit(boolean isHeader) {
    String initChar = isHeader ? "┌" : "└";
    String endChar  = isHeader ? "┐" : "┘";
    
    System.out.print(initChar);
    for(int i = 0; i < width - 2; i++) {
      System.out.print("─");
      }
    System.out.println(endChar);
    }
  
  private static void printLimit(String gameName) {
    String initChar = "┌";
    String endChar  = "┐";
    float middleStartChars;
    float middleEndChars;
    int nonNameChars;
    boolean isExactMiddle;
    
    nonNameChars  = (width -2) - gameName.length();
    isExactMiddle = (nonNameChars % 2 == 0) ? true : false;
    
    if (isExactMiddle) {
      middleStartChars  = nonNameChars / 2;
      middleEndChars    = middleStartChars;
      } else {
      middleStartChars  = (float) Math.floor(nonNameChars / 2);
      middleEndChars    = middleStartChars + 1;
    }
    
    System.out.print(initChar);
    for (int i = 0; i < middleStartChars; i++) {
      System.out.print("─");
    }
    
    for (int i = 0; i < gameName.length(); i++) {
      System.out.print(gameName.charAt(i));
    }
    
    for (int i = 0; i < middleEndChars; i++) {
      System.out.print("─");
    }
    System.out.println(endChar);
  }
}
