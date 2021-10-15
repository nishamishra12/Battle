package equipment;

import java.util.ArrayList;
import java.util.List;

import player.Ability;
import randomizer.RandomGenerator;

public class Footwear extends EquipmentAbstract {

  private final String name;
  private final List<Ability> effectAbility = new ArrayList<>();
  private int effectValue;

  public Footwear(String name, RandomGenerator randomGenerator) {

    this.name = name;
    this.effectValue = randomGenerator.getNextInt(1,4);
    this.effectAbility.add(Ability.DEXTERITY);
  }

  @Override
  public int compareTo(Equipment s) {
    if (s instanceof EquipmentAbstract) {
      EquipmentAbstract abstractGear = (EquipmentAbstract) s;
      return abstractGear.compareToFootwear(this);
    }
    return -1;
  }

  @Override
  protected int compareToBelt(Equipment s) {
    return -1;
  }

  @Override
  protected int compareToFootwear(Equipment s) {
    return s.getName().compareTo(this.getName());
  }

  @Override
  protected int compareToHeadgear(Equipment s) {
    return -1;
  }

  @Override
  protected int compareToPotion(Equipment s) {
    return -1;
  }

  @Override
  public int getEffectValue() {
    return this.effectValue;
  }

  @Override
  public List<Ability> getEffectAbility() {
    return this.effectAbility;
  }

  @Override
  public int getBeltSize() {
    return -1;
  }

  @Override
  public void setEffectValueNegative() {
    this.effectValue = -1 * this.effectValue;
  }

  @Override
  public EquipmentType getEquipmentType() {
    return EquipmentType.FOOTWEAR;
  }

  @Override
  public String getName() {
    return this.name;
  }
}
