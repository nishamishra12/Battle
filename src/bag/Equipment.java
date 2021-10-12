package bag;

import java.util.List;

import player.Ability;

public interface Equipment {

  public int getEffectValue();

  public List<Ability> getEffectAbility();

  public int getBeltSize();

  public int getEffectMoves();

  public void setEffectValueNegative();

  public EquipmentType getEquipmentType();
}
