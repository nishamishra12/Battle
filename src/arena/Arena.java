package arena;

import java.util.List;

import equipment.Equipment;
import weapon.Weapon;

public interface Arena {

  public String startBattle();

  public String getPlayerDescription();

  public List<Weapon> getWeaponArmory();

  public List<Equipment> getGearBag();
}
