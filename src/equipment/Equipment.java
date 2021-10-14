package equipment;

import java.util.List;

import player.Ability;

public interface Equipment extends Comparable<Equipment> {

  public int getEffectValue();

  public List<Ability> getEffectAbility();

  public int getBeltSize();

  public void setEffectValueNegative();

  public EquipmentType getEquipmentType();

  public String getName();

}
