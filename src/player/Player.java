package player;

import java.util.List;
import java.util.Map;

import equipment.Equipment;
import equipment.EquipmentType;
import weapon.Weapon;

public interface Player {

  String getName();

  int getStrength();

  int getConstitution();

  int getDexterity();

  int getCharisma();

  Map<EquipmentType, List<Equipment>> getPlayerBag();

  void addEquipment(Equipment equipment);

  Weapon getCurrentWeapon();

  void setCurrentWeapon(Weapon currentWeapon);

  int getHealth();

  public int getStrikingPower();

  public int getAvoidanceAbility();

  public int getPotentialDamage();

  public int getActualDamage();

  public void calculatePlayerPowers(Player player);

  public void calculateHealth();

  public void updateHealth(int reducedHealth);

  public void removePotions();

}
