package player;

public class BattlePlayer implements Player {

  private int strength;
  private int constitution;
  private int dexterity;
  private int charisma;


  public BattlePlayer(int strength, int constitution, int dexterity, int charisma) {

    if (strength < 6 || strength > 18) {
      throw new IllegalArgumentException("Value of strength is incorrect");
    }
    if (constitution < 6 || constitution > 18) {
      throw new IllegalArgumentException("Value of strength is incorrect");
    }
    if (dexterity < 6 || dexterity > 18) {
      throw new IllegalArgumentException("Value of strength is incorrect");
    }
    if (charisma < 6 || charisma > 18) {
      throw new IllegalArgumentException("Value of strength is incorrect");
    }
    this.strength = strength;
    this.constitution = constitution;
    this.dexterity = dexterity;
    this.charisma = charisma;
  }

  @Override
  public int getStrength() {
    return this.strength;
  }

  @Override
  public int getConstitution() {
    return this.constitution;
  }

  @Override
  public int getDexterity() {
    return this.dexterity;
  }

  @Override
  public int getCharisma() {
    return this.charisma;
  }
}
