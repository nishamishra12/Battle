package equipment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import player.Ability;
import randomizer.Randomizer;

public class Belt extends EquipmentAbstract {

  private final String name;
  private final List<Ability> effectAbility = new ArrayList<>();
  private final BeltSize size;
  private int effectValue;

  public Belt(String name) {

    this.name = name;
    this.effectValue = new Randomizer(1, 4).getRandomValue();
    for (int i = 0; i < new Randomizer(1, 2).getRandomValue(); i++) {
      this.effectAbility.add(Arrays.asList(Ability.values()).get(new Randomizer(0, 4).getRandomValue()));
    }
    this.size = Arrays.asList(BeltSize.values()).get(new Randomizer(0, 3).getRandomValue());
  }

  @Override
  public int compareTo(Equipment s) {
    if (s instanceof EquipmentAbstract) {
      EquipmentAbstract abstractGear = (EquipmentAbstract) s;
      return abstractGear.compareToBelt(this);
    }
    return -1;
  }

  @Override
  protected int compareToFootwear(Equipment s) {
    return 1;
  }

  @Override
  protected int compareToBelt(Equipment s) {
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
    return this.size.getSizeValue();
  }

  @Override
  public void setEffectValueNegative() {
    this.effectValue = -1 * this.effectValue;
  }

  @Override
  public EquipmentType getEquipmentType() {
    return EquipmentType.BELT;
  }

  @Override
  public String getName() {
    return this.name;
  }
}