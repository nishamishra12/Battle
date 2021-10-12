package arena;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bag.Belt;
import bag.Equipment;
import bag.Footwear;
import bag.HeadGear;
import bag.Potions;
import player.BattlePlayer;
import player.Player;
import randomizer.Randomizer;
import weapon.Axe;
import weapon.Broadsword;
import weapon.Flail;
import weapon.Kantana;
import weapon.TwoHandedSword;
import weapon.Weapon;

public class BattleArena implements Arena {

  List<Equipment> gearBag = new ArrayList<>();

  private Player player1;
  private Player player2;

  public BattleArena(String name1, String name2) {
    this.player1 = new BattlePlayer(name1);
    this.player2 = new BattlePlayer(name2);
    createGearBag();
    player1.calculateHealth();
    player2.calculateHealth();
    giveWeaponToPlayer(player1);
    giveWeaponToPlayer(player2);
  }

  private void giveWeaponToPlayer(Player p) {
    List<Weapon> weapons = setWeaponArmory();
    p.setCurrentWeapon(weapons.get(new Randomizer(0,5).getRandomValue()));
  }

  private List<Weapon> setWeaponArmory() {
    List<Weapon> weaponArmory = new ArrayList<>();
    weaponArmory.add(new Axe());
    weaponArmory.add(new Flail());
    weaponArmory.add(new Kantana());
    weaponArmory.add(new Broadsword());
    weaponArmory.add(new TwoHandedSword());
    Collections.shuffle(this.setWeaponArmory());
    return weaponArmory;
  }

  private void createGearBag() {
    //add 5 headgear
    for (int i = 0; i < 5; i++) {
      gearBag.add(new HeadGear());
    }
    //add 5 footwear
    for (int i = 0; i < 5; i++) {
      gearBag.add(new Footwear());
    }
    //add 15 Belt
    for (int i = 0; i < 15; i++) {
      gearBag.add(new Belt());
    }
    //add 15 Potions;
    for (int i = 0; i < 15; i++) {
      gearBag.add(new Potions());
    }
    Collections.shuffle(gearBag);

    for (int i = 0; i < 10; i++) {
      gearBag.get(i).setEffectValueNegative();
    }
    Collections.shuffle(gearBag);
    assignBagToPlayer();
  }

  private void assignBagToPlayer() {
    //assign equipments to player 1, and player 2
    for (int i = 0; i < gearBag.size(); i++) {
      if (i < 20) {
        player1.addEquipment(gearBag.get(i));
      } else {
        player2.addEquipment(gearBag.get(i));
      }
    }
  }
}
