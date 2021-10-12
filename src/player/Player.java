package player;

import java.util.List;
import java.util.Map;

import bag.Equipment;
import bag.EquipmentType;
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

  void calculateHealth();

  int getHealth();

}
