package bag;

import java.util.ArrayList;
import java.util.List;

import player.Ability;
import randomizer.Randomizer;

public class HeadGear implements Equipment {

  private final List<Ability> effectAbility = new ArrayList<>();
  private int effectValue;

  public HeadGear() {
    this.effectValue = new Randomizer(1, 7).getRandomValue();
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
    return EquipmentType.HEADGEAR;
  }

}
