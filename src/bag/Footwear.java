package bag;

import java.util.ArrayList;
import java.util.List;

import player.Ability;
import randomizer.Randomizer;

public class Footwear implements Equipment {

  private final String name;
  private final List<Ability> effectAbility = new ArrayList<>();
  private int effectValue;

  public Footwear(String name) {

    this.name = name;
    Randomizer random = new Randomizer(1, 7);
    this.effectValue = random.getRandomValue();
    this.effectAbility.add(Ability.CONSTITUTION);
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
    return 1;
  }

  @Override
  public int getEffectMoves() {
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
