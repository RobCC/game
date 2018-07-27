package characters;

public abstract class Character {
  private String name;
  private int age;
  private Gender gender;
  private Faction faction;
  
  public enum Gender { MALE, FEMALE }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setAge(int age) {
    this.age = age;
  }
  
  public void setGender(Gender gender) {
    this.gender = gender;
  }
  
  public void setFaction(Faction faction) {
    this.faction = faction;
  }
  
  public String getName() {
    return name;
  }
  
  public int getAge() {
    return age;
  }
  
  public Gender getGender() {
    return gender;
  }
  
  public Faction getFaction() {
    return faction;
  }
  
  public boolean isMale() {
    return gender == Gender.MALE ? true : false;
  }
  
  public String introduce() {
    return "";
  }
  
  /*TODO
   * If age 19 -  25 = boy/kid/lad
   * If age 26 - 40 = man/woman/lady/lord
   * If age 41 - 60 = oldman/ragg
   */
  
  
}
