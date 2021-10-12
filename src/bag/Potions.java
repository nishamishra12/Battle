package bag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import player.Ability;
import randomizer.Randomizer;

public class Potions implements Equipment {

  private final List<Ability> effectAbility = new ArrayList<>();
  private int effectValue;
  private int effectMoves;

  public Potions() {
    this.effectValue = new Randomizer(1, 7).getRandomValue();
    this.effectAbility.add(Arrays.asList(Ability.values()).get(new Randomizer(0, 3).getRandomValue()));
    this.effectMoves = 2;
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
    return this.effectMoves;
  }

  @Override
  public void setEffectValueNegative() {
    this.effectValue = -1 * this.effectValue;
  }

  @Override
  public EquipmentType getEquipmentType() {
    return EquipmentType.POTION;
  }
}
