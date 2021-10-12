package player;

import java.util.List;
import java.util.Map;

import bag.Equipment;
import bag.EquipmentType;
import weapon.Weapon;

public interface Player {

  public String getName();

  public int getStrength();

  public int getConstitution();

  public int getDexterity();

  public int getCharisma();

  public Map<EquipmentType, List<Equipment>> getPlayerBag();

  public void addEquipment(Equipment equipment);

  public Weapon getCurrentWeapon();

  public void setCurrentWeapon(Weapon currentWeapon);

  public void calculateHealth();

  public int getHealth();

}
