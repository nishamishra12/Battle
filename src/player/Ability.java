package player;

/**
 * This is an enumeration for Ability Values of the Player.
 */
public enum Ability {
  DEXTERITY("Dexterity"),
  CONSTITUTION("Constitution"),
  CHARISMA("Charisma"),
  STRENGTH("Strength");

  private final String abilityName;

  Ability(String abilityName) {
    this.abilityName = abilityName;
  }

  public String abilityName() {
    return abilityName;
  }
}
