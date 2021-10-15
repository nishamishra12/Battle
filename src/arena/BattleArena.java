package arena;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import equipment.Belt;
import equipment.Equipment;
import equipment.EquipmentType;
import equipment.Footwear;
import equipment.HeadGear;
import equipment.Potions;
import player.BattlePlayer;
import player.Player;
import randomizer.RandomGenerator;
import weapon.Axe;
import weapon.Broadsword;
import weapon.Flail;
import weapon.Kantana;
import weapon.TwoHandedSword;
import weapon.Weapon;

public class BattleArena implements Arena {

  private Player player1;
  private Player player2;
//  private final Player initialPlayer1;
//  private final Player initialPlayer2;
  RandomGenerator randomGenerator;
  List<Equipment> gearBag = new ArrayList<>();
  private int move;

  public BattleArena(String name1, String name2, RandomGenerator randomGenerator) {
    this.randomGenerator = randomGenerator;
    this.player1 = new BattlePlayer(name1, this.randomGenerator);
    this.player2 = new BattlePlayer(name2, this.randomGenerator);

    createGearBag();
  }

  //equip players with gears
  public void equipPlayerWithGear() {
    assignBagToPlayer();
  }

  //players to request weapon
  public void requestWeapon() {
    giveWeaponToPlayer(player1);
    giveWeaponToPlayer(player2);
    player1.calculateHealth();
    player2.calculateHealth();
  }

  //start the battle
  public void startBattle() {
    System.out.println("Battle Started");
    System.out.println("Player 1 health: " + player1.getHealth() + " Charisma" + player1.getCharisma());
    System.out.println("Player 2 health: " + player2.getHealth() + " Charisma" + player2.getCharisma());
    if (player1.getCharisma() > player2.getCharisma()) {
      attack(player1, player2);
    } else if (player1.getCharisma() < player2.getCharisma()) {
      attack(player2, player1);
    } else if (player1.getCharisma() == player2.getCharisma()) {
      System.out.println("Game Draw, No one Wins ");
    }
  }

  private void attack(Player attacker, Player defendant) {
    if (attacker.getHealth() > 0 && defendant.getHealth() > 0 && move <= 30) {
      move++;
      System.out.println("************Move " + move + "Attacker " + attacker.getName() + "Defendant " + defendant.getName());
      System.out.println("Health attacker: " + attacker.getHealth());
      System.out.println("Health defendant: " + defendant.getHealth());

      if (move == 5) { //remove potions in round 2
        attacker.removePotions();
        defendant.removePotions();
      }
      attacker.calculatePlayerPowers(defendant);
      defendant.calculatePlayerPowers(attacker);

      System.out.println("Striking Power Attacker: " + attacker.getStrikingPower());
      System.out.println("Actual Damage Attacker: " + attacker.getActualDamage());
      System.out.println("Avoidance defendant: " + defendant.getAvoidanceAbility());
      if (attacker.getStrikingPower() > defendant.getAvoidanceAbility()
              && attacker.getActualDamage() > 0) {
        defendant.updateHealth(defendant.getHealth() - attacker.getActualDamage());
      }
      System.out.println("New Health Attacker: " + attacker.getHealth());
      System.out.println("New Health defendant: " + defendant.getHealth());
      attack(defendant, attacker);
    } else {
      if (player1.getHealth() <= 0 && player2.getHealth() > 0) {
        System.out.println(player1.getName() + "Wins");
      } else if (player1.getHealth() > 0 && player2.getHealth() <= 0) {
        System.out.println(player2.getName() + "Wins");
      } else {
        System.out.println("No one is able to attack, game draw");
      }
    }
  }

  public String getPlayerDescription() {
    return getDescriptionHelper(player1) + '\n' + getDescriptionHelper(player2);
  }

  private String getDescriptionHelper(Player player) {
    List<Equipment> playerGears = new ArrayList<>();
    for (Map.Entry<EquipmentType, List<Equipment>> entry : player.getPlayerBag().entrySet()) {
      playerGears.addAll(entry.getValue());
    }
    Collections.sort(playerGears);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("************** Player Description **************" + '\n' + "Player Name: ")
            .append(player.getName() + '\n').
            append("Charisma: " + player.getCharisma() + ", Constitution: " + player.getConstitution()
                    + ", Dexterity: " + player.getDexterity() + ", Strength: " + player.getStrength() + '\n')
            .append("Gears - " + '\n');
    for (int i = 0; i < playerGears.size(); i++) {
      stringBuilder.append(playerGears.get(i).getName()).append('\n');
    }
    stringBuilder.append("Weapon: " + player.getCurrentWeapon().getClass().getSimpleName());
    return stringBuilder.toString();
  }

  private void giveWeaponToPlayer(Player p) {
    List<Weapon> weapons = setWeaponArmory();
    p.setCurrentWeapon(weapons.get(this.randomGenerator.getNextInt(0, 5)));
  }

  private List<Weapon> setWeaponArmory() {
    List<Weapon> weaponArmory = new ArrayList<>();
    weaponArmory.add(new Axe(this.randomGenerator));
    weaponArmory.add(new Flail(this.randomGenerator));
    weaponArmory.add(new Kantana(this.randomGenerator));
    weaponArmory.add(new Broadsword(this.randomGenerator));
    weaponArmory.add(new TwoHandedSword(this.randomGenerator));
    return weaponArmory;
  }

  private void createGearBag() {
    //add 5 headgear
    for (int i = 0; i < 5; i++) {
      int c = i + 65;
      gearBag.add(new HeadGear("Headgear" + (char) c, this.randomGenerator));
    }
    //add 5 footwear
    for (int i = 0; i < 5; i++) {
      int c = i + 65;
      gearBag.add(new Footwear("Footwear" + (char) c, this.randomGenerator));
    }
    //add 15 Belt
    for (int i = 0; i < 15; i++) {
      int c = i + 65;
      gearBag.add(new Belt("Belt" + (char) c, this.randomGenerator));
    }
    //add 15 Potions;
    for (int i = 0; i < 15; i++) {
      int c = i + 65;
      gearBag.add(new Potions("Potion" + (char) c,this.randomGenerator));
    }
    gearBag = randomGenerator.shuffleList(gearBag);

    for (int i = 0; i < 10; i++) {
      gearBag.get(i).setEffectValueNegative();
    }
    gearBag = randomGenerator.shuffleList(gearBag);
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
