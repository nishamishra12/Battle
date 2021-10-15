package player;

import java.util.List;
import java.util.Map;

import equipment.Equipment;
import equipment.EquipmentType;
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

  public int getHealth();

  public int getStrikingPower();

  public int getAvoidanceAbility();

  public int getPotentialDamage();

  public int getActualDamage();

  public void calculatePlayerPowers(Player player);

  public void calculateInitialHealth();

  public void updateHealth(int reducedHealth);

  public void removeGears(int move);

}
