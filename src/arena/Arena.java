package arena;

import java.util.List;

import equipment.Equipment;
import player.Player;
import weapon.Weapon;

/**
 * This is class represents the Battle Arena where the players are called, equipped with gears
 * and weapon and made ready to battle.
 */
public interface Arena {

  /**
   * This method is used to start the battle between the two players.
   *
   * @return the result of the battle as String.
   */
  public String startBattle();

  /**
   * This method is used to get description of the two players who are in the battle.
   *
   * @return the description of each player with abilities, gears, and weapon used.
   */
  public String getPlayerDescription();

  /**
   * This method is used to get the Armory of all the weapons from which players
   * can select a weapon randomly.
   *
   * @return list of weapons present in the armory from which player can choose any weapon
   */
  public List<Weapon> getWeaponArmory();

  /**
   * This method is used to get the bag of all equipments for both the players,
   * the players will be randomly distributed gears from this bag.
   *
   * @return list of equipments from which players will be assigned random gears.
   */
  public List<Equipment> getGearBag();

  /**
   * This method is used to get the lexicographically sorted bag
   * of all equipments for both the players.
   *
   * @return lexicographically sorted list of equipments of a player
   * @throws IllegalArgumentException when list provided is null
   */
  public List<Equipment> getSortedGearList(List<Equipment> gearList)
          throws IllegalArgumentException;

  /**
   * This method provides the object of Player1.
   *
   * @return player1 object
   */
  public Player getPlayer1();

  /**
   * This method provides the object of Player2.
   *
   * @return player2 object
   */
  public Player getPlayer2();

  /**
   * This method equips both the players playing in th arena with gears.
   */
  public void equipPlayerWithGears();

  /**
   * This method randomly assigns weapons to both the players playing in the arena.
   */
  public void requestWeaponForPlayer();
}
