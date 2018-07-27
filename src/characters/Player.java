package characters;

import java.util.LinkedHashMap;
import java.util.Map;
import tools.UserInput;

public class Player extends Character {
  private static Player player;
  
  private Player() {}
  
  public static Player getInstance() {
    if(player == null)
      player = new Player();
    return player;
  }
  
  public void setName() {
    char confirm;
    String name;
		
    do {
      name    = UserInput.getUserInput("Please enter your character name");
      confirm = UserInput.printOptions("Are you sure you want this name?");
    } while(confirm == UserInput.USER_DECLINE);
    
    player.setName(name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase());
  }
  
  public void setAge() {
    boolean validAge  = true;
    int age           = 0;
    String inputAge;
    
    do {
      inputAge  = UserInput.getUserInput("Please enter your character age.");
      validAge  = isAgeValid(inputAge);
      
      if (validAge == false) {
        UserInput.printUnvalidError("This is not a valid age.");
        continue;
      }
      
      age = Integer.parseInt(inputAge); 
      
      if (age < 18 || age > 60) {
        UserInput.printUnvalidError("Age out of range. Try choosing between 18-60.");
        validAge = false;
        continue;
      }
      
    } while(!validAge);
    
    player.setAge(age);
  }
  
  public void setGender() {
    Map<String, String> genderMap = new LinkedHashMap<>();
    String selectedGender;
    char genderOption;
    int opNumber = 1;
    
    for(Gender g : Gender.values()) {
      genderMap.put("" + opNumber, g.name());
      opNumber++;
    }
    
    genderOption    = UserInput.printOptions("Choose you gender.", genderMap);
    selectedGender  = genderMap.get("" + genderOption);
    
    player.setGender(Gender.valueOf(selectedGender));
  }
  
  public void setFaction() {
    Map<String, String> factionMap = new LinkedHashMap<>();
    String selectedFaction;
    char factionOption;
    int opNumber = 1;
    
    for(Faction f : Faction.values()) {
      if (f == Faction.DAUGHTERS && player.isMale()) continue;
      factionMap.put("" + opNumber, f.name());
      opNumber++;
    }
    
    String factionIntroduction = 
        "The world has 4 main factions. "
        + "Through the course of the story, you will find that the faction you pledged alliance "
        + "may play in your favor, or bring you more trouble. You will learn more about the factions while you continue with the game. \n \n "
        + "The ILLORIAN EMPIRE. The main faction that controls most of Lyros, the main continent of the world. The Almair has been active for thousand years, currently under the rule of King Lothair. Like all empires, their goal is to expand and mantain order. Though the empire is not perfect, corruption exist within its ranks. It has allied with the Daughters of Midaria to gain control of both order and faith of the people. \n \n "
        + "The DAUGHTERS OF MIDARIA. The daugthers are a large group of follower of the goddess Midaria, it is formed entirely by women. Its history follows Midaria, who strike down the Great Chaos, and allowed men and women to walk the earth. Her figure represents purity and forgiveness, but great suffering for those who opose her ideals. The daughters now are part of the Empire, thus sharing the same territory. Their leader, Matriarch Marie de Mavarré, counsels the king and makes sure Midara is the only and true idol of the empire. \n \n "
        + "The COVENANT OF WEST PROVINCES. Started as the rebel province of Vlaros, when Lord Nathel provoked an uprising at Ludos, the capital of the Empire, to bring down the king's ruling and restore the independance of the outter provinces . It has grown to a large group of provinces located to the east of Lyros, and have formed an alliance to bring the Empire to an end. Although the covenant lacks the military thoroughness of the empire, they are famous for being vicious warriors in combat, and will do anything to defend their Eastlands from the hands of the Empire.  \n \n "
        + "The ACOLYTES OF YAH'VOROTH. The acolytes are a cult following a mythical figure called Yah'Voroth. Yah is the old god of the Rising Void, who once created the universe as a perpetual dream in which he has control of. It is said that the cult goes back even before the empire itself, in the times where the acolytes were the main religious group of Lyros. Since then, Yah's followers are seen as lunatics, conspirators of the Faith of Midaria and enemies to everyone that does not recognice Yah as the true god. Practitioners of dark, long forgotten arts, the acolytes lurk between the people, plotting and waiting the return of Yah'Voroth to restore its old glory.  \n \n "
        + "Choose a FACTION.";
    
    factionOption    = UserInput.printOptions(factionIntroduction, factionMap);
    selectedFaction  = factionMap.get("" + factionOption);
    
    player.setFaction(Faction.valueOf(selectedFaction));
  }
  
  public boolean isAgeValid(String age) {
    return age.matches("^\\d+$");
  }
  
  public void describe() {
    System.out.println("Your name shall be " + player.getName() + ". ");
    System.out.println("Your age will be " + player.getAge()  + ". ");
    System.out.println("Your gender will be " + player.getGender()  + ". ");
    System.out.println("Your faction will be " + player.getFaction()  + ". ");
  }
}
