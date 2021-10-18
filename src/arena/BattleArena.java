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
import equipment.Potion;
import player.BattlePlayer;
import player.Player;
import randomizer.RandomGenerator;
import weapon.Axe;
import weapon.Broadsword;
import weapon.Flail;
import weapon.Katana;
import weapon.TwoHandedSword;
import weapon.Weapon;

/**
 * This class represents the Battle Arena where the players are called, equipped with gears
 * and weapon and made ready to battle.
 */
public class BattleArena implements Arena {

  private final Player initialPlayer1;
  private final Player initialPlayer2;
  private final RandomGenerator randomGenerator;
  private List<Weapon> weaponArmory;
  private List<Equipment> gearBag = new ArrayList<>();
  private Player player1;
  private Player player2;
  private int move;

  /**
   * Constructs a battle arena which is used to host battle between players.
   *
   * @param name1           this parameter takes the name of player 1
   * @param name2           this parameter takes the name of player 2
   * @param randomGenerator this parameter takes the random generator
   * @throws IllegalArgumentException when a null value for name of the players
   *                                  or random generator is entered
   */
  public BattleArena(String name1, String name2, RandomGenerator randomGenerator)
          throws IllegalArgumentException {

    if (name1 == null || name2 == null) {
      throw new IllegalArgumentException("Name of player cannot be null");
    }
    if (randomGenerator == null) {
      throw new IllegalArgumentException("Randomizer cannot be null");
    }
    this.randomGenerator = randomGenerator;
    this.initialPlayer1 = new BattlePlayer(name1, this.randomGenerator);
    this.initialPlayer2 = new BattlePlayer(name2, this.randomGenerator);
    createGearBag();

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void equipPlayerWithGears() {
    assignBagToPlayer();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void requestWeaponForPlayer() {
    giveWeaponToPlayer(initialPlayer1);
    giveWeaponToPlayer(initialPlayer2);
    initialPlayer1.calculateInitialHealth();
    initialPlayer2.calculateInitialHealth();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String startBattle() {
    this.player1 = new BattlePlayer(initialPlayer1, randomGenerator);
    this.player2 = new BattlePlayer(initialPlayer2, randomGenerator);
    move = 0;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Players are ready for Battle" + '\n');

    if (player1.getCharisma() > player2.getCharisma()) {
      stringBuilder = attack(player1, player2, stringBuilder);
    } else if (player1.getCharisma() < player2.getCharisma()) {
      stringBuilder = attack(player2, player1, stringBuilder);
    } else if (player1.getCharisma() == player2.getCharisma()) {
      stringBuilder.append("Charisma same! Game Draw, No one Wins");
    }
    return stringBuilder.toString();
  }

  /**
   * Private Helper method which performs the actions for every move for the battle.
   *
   * @param attacker      this parameter takes the attacking player object
   * @param defendant     this parameter takes the defending player object
   * @param stringBuilder this parameter takes the string for creating the attack description
   * @return gives the description of the attack after every move
   */
  private StringBuilder attack(Player attacker, Player defendant, StringBuilder stringBuilder) {
    if (attacker.getHealth() > 0 && defendant.getHealth() > 0 && move <= 30) {
      move++;
      stringBuilder.append('\n' + "-------------------------- Move No " + move
              + " --------------------------").append("\n" + attacker.getName() + " is attacking "
              + defendant.getName() + '\n');

      stringBuilder.append(attacker.removeGears(this.move));
      stringBuilder.append(defendant.removeGears(this.move));
      attacker.calculatePlayerPowers(defendant);
      defendant.calculatePlayerPowers(attacker);

      stringBuilder.append('\n' + "Striking Power of Attacker " + attacker.getStrikingPower());
      stringBuilder.append('\n' + "Defender Avoidance Ability " + defendant.getAvoidanceAbility());
      stringBuilder.append('\n' + "Health of the attacker " + attacker.getName() + ": "
              + attacker.getHealth());
      stringBuilder.append('\n' + "Health of the defendant " + defendant.getName() + ": "
              + defendant.getHealth());
      stringBuilder.append('\n' + "Attacker Damage Value: " + attacker.getActualDamage());
      if (attacker.getStrikingPower() > defendant.getAvoidanceAbility()
              && attacker.getActualDamage() > 0) {
        stringBuilder.append('\n' + "Attack successful,").append(" Damage done: "
                + attacker.getActualDamage());
        defendant.updateHealth(defendant.getHealth() - attacker.getActualDamage());
      }
      if (attacker.getStrikingPower() > defendant.getAvoidanceAbility()
              && attacker.getActualDamage() <= 0) {
        stringBuilder.append('\n' + "Attack successful,").append(" but Damage done 0 ");
        defendant.updateHealth(defendant.getHealth() - attacker.getActualDamage());
      } else if (attacker.getStrikingPower() <= defendant.getAvoidanceAbility()) {
        stringBuilder.append('\n' + "Attack was unsuccessful");
      }
      attack(defendant, attacker, stringBuilder);
    } else {
      if (player1.getHealth() <= 0 && player2.getHealth() > 0) {
        stringBuilder.append('\n' + "-------------------------- Game Over "
                + "--------------------------" + '\n' + player1.getName() + " Wins!!");
      } else if (player1.getHealth() > 0 && player2.getHealth() <= 0) {
        stringBuilder.append('\n' + "-------------------------- Game Over "
                + "--------------------------" + '\n' + player2.getName() + " Wins!!");
      } else {
        stringBuilder.append('\n' + "\"-------------------------- Game Over --------------------"
                + '\n' + "------" + "Moves Over, Game Draw");
      }
    }
    return stringBuilder;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getPlayerDescription() {
    return getDescriptionHelper(this.initialPlayer1) + '\n'
            + getDescriptionHelper(this.initialPlayer2);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Equipment> getSortedGearList(List<Equipment> gearList) {
    if (gearList == null) {
      throw new IllegalArgumentException("Gear List cannot be null");
    }
    List<Equipment> playerGears = new ArrayList<>(gearList);
    Collections.sort(playerGears);
    return playerGears;
  }

  /**
   * This method provides the description of both the players who are in the battle.
   *
   * @param player this parameter takes the player object whose description is required
   * @return the description of the player
   */
  private String getDescriptionHelper(Player player) {
    List<Equipment> playerGears = new ArrayList<>();

    for (Map.Entry<EquipmentType, List<Equipment>> entry : player.getPlayerBag().entrySet()) {
      playerGears.addAll(entry.getValue());
    }
    playerGears = getSortedGearList(playerGears);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("************** Player Description **************" + '\n'
                    + "Player Name: ").append(player.getName() + '\n')
            .append("Charisma: " + player.getCharisma() + ","
                    + " Constitution: " + player.getConstitution()
                    + ", Dexterity: " + player.getDexterity() + ","
                    + " Strength: " + player.getStrength() + '\n').append("Gears - " + '\n');

    for (int i = 0; i < playerGears.size(); i++) {
      stringBuilder.append(playerGears.get(i).getName()).append('\n');
    }
    stringBuilder.append("Weapon: " + player.getCurrentWeapon().getClass().getSimpleName());
    return stringBuilder.toString();
  }

  /**
   * This method randomly sets the weapon for a player.
   *
   * @param p this parameter takes the Player object
   */
  private void giveWeaponToPlayer(Player p) {
    List<Weapon> weapons = setWeaponArmory();
    p.setCurrentWeapon(weapons.get(this.randomGenerator.getNextInt(0, 5)));
  }

  /**
   * This method creates a weapon armory for each player.
   *
   * @return the list of all weapons in the armory
   */
  private List<Weapon> setWeaponArmory() {
    weaponArmory = new ArrayList<>();
    weaponArmory.add(new Axe(this.randomGenerator));
    weaponArmory.add(new Flail(this.randomGenerator));
    weaponArmory.add(new Katana(this.randomGenerator));
    weaponArmory.add(new Broadsword(this.randomGenerator));
    weaponArmory.add(new TwoHandedSword(this.randomGenerator));
    List<Weapon> weaponArmoryCopy = new ArrayList<>(weaponArmory);
    return weaponArmoryCopy;
  }

  /**
   * This method creates the gear bag consisting of all the equipments.
   */
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
    //add 15 Potion;
    for (int i = 0; i < 15; i++) {
      int c = i + 65;
      gearBag.add(new Potion("Potion" + (char) c, this.randomGenerator));
    }
    gearBag = randomGenerator.shuffleList(gearBag);

    for (int i = 0; i < 10; i++) {
      gearBag.get(i).setEffectValueNegative();
    }
    gearBag = randomGenerator.shuffleList(gearBag);
  }

  /**
   * This method is used to assign bag to both the players.
   */
  private void assignBagToPlayer() {
    //assign equipments to player 1, and player 2
    for (int i = 0; i < gearBag.size(); i++) {
      if (i < 20) {
        initialPlayer1.addEquipment(gearBag.get(i));
      } else {
        initialPlayer2.addEquipment(gearBag.get(i));
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Equipment> getGearBag() {
    List<Equipment> equipmentList = new ArrayList<>(this.gearBag);
    return equipmentList;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Weapon> getWeaponArmory() {
    List<Weapon> weaponList = new ArrayList<>(this.weaponArmory);
    return weaponList;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Player getPlayer1() {
    Player player1 = this.initialPlayer1;
    return player1;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Player getPlayer2() {
    Player player2 = this.initialPlayer2;
    return player2;
  }
}
