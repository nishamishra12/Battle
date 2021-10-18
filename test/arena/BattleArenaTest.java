package arena;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import equipment.Belt;
import equipment.Equipment;
import equipment.EquipmentType;
import equipment.Footwear;
import equipment.HeadGear;
import equipment.Potion;
import randomizer.FixedRandGenerator;
import randomizer.RandomGenerator;
import weapon.Axe;
import weapon.Barehanded;
import weapon.Broadsword;
import weapon.Flail;
import weapon.Katana;
import weapon.TwoHandedSword;
import weapon.Weapon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class to check all the implementation of battle arena.
 */
public class BattleArenaTest {

  private Arena arena;
  private RandomGenerator randomGenerator = new FixedRandGenerator(1);

  @Before
  public void setUp() throws Exception {
    arena = new BattleArena("Tom", "Jerry", randomGenerator);
    arena.equipPlayerWithGears();
    arena.requestWeaponForPlayer();
  }

  @Test
  public void testForPlayerIsBareHanded() {
    arena = new BattleArena("Chick Jr", "Duck Jr", randomGenerator);
    assertTrue(arena.getPlayer1().getCurrentWeapon() instanceof Barehanded);
    assertTrue(arena.getPlayer2().getCurrentWeapon() instanceof Barehanded);
  }

  @Test
  public void testForPlayerWithBasicAbilities() {
    randomGenerator = new FixedRandGenerator(2);
    arena = new BattleArena("Chick Jr", "Duck Jr", randomGenerator);
    assertEquals(2, arena.getPlayer1().getStrength());
    assertEquals(2, arena.getPlayer1().getCharisma());
    assertEquals(2, arena.getPlayer1().getDexterity());
    assertEquals(2, arena.getPlayer1().getConstitution());
    //check for player 2
    assertEquals(2, arena.getPlayer2().getStrength());
    assertEquals(2, arena.getPlayer2().getCharisma());
    assertEquals(2, arena.getPlayer2().getDexterity());
    assertEquals(2, arena.getPlayer2().getConstitution());

  }

  @Test(expected = IllegalArgumentException.class)
  public void InvalidPlayer1Name() {
    new BattleArena("p1", null, randomGenerator);
  }

  @Test(expected = IllegalArgumentException.class)
  public void InvalidPlayer2Name() {
    new BattleArena(null, "p2", randomGenerator);
  }

  @Test(expected = IllegalArgumentException.class)
  public void InvalidRandomizer() {
    new BattleArena("p1", "p2", null);
  }

  @Test
  public void testForSameCharisma() {
    randomGenerator = new FixedRandGenerator(1);
    //If charisma is same battle is drawn
    assertEquals("Players are ready for Battle\n" +
            "Charisma same! Game Draw, No one Wins", arena.startBattle());
  }

  @Test
  public void getGearBag() {
    assertEquals(40, arena.getGearBag().size());
    assertEquals(EquipmentType.HEADGEAR, arena.getGearBag().get(0).getEquipmentType());
    assertEquals(EquipmentType.FOOTWEAR, arena.getGearBag().get(5).getEquipmentType());
    assertEquals(EquipmentType.BELT, arena.getGearBag().get(10).getEquipmentType());
    assertEquals(EquipmentType.POTION, arena.getGearBag().get(25).getEquipmentType());
  }

  @Test
  public void equipPlayerWithGears() {
    arena = new BattleArena("John", "Marry", randomGenerator);
    assertEquals(0, arena.getPlayer1().getPlayerBag().size());
    assertEquals(0, arena.getPlayer2().getPlayerBag().size());
    arena.equipPlayerWithGears();
    //gears added to players and the size of the player bag changed.
    assertNotEquals(0, arena.getPlayer1().getPlayerBag().size());
    assertNotEquals(0, arena.getPlayer2().getPlayerBag().size());
  }

  @Test
  public void equipPlayerWithWeapon() {
    arena = new BattleArena("John", "Marry", randomGenerator);
    assertTrue(arena.getPlayer1().getCurrentWeapon() instanceof Barehanded);
    assertTrue(arena.getPlayer2().getCurrentWeapon() instanceof Barehanded);
    arena.requestWeaponForPlayer();
    //weapon given to players.
    assertTrue(arena.getPlayer1().getCurrentWeapon() instanceof Flail);
    assertTrue(arena.getPlayer2().getCurrentWeapon() instanceof Flail);
  }

  @Test
  public void testForAttackerHasGreaterCharisma() {
    randomGenerator = new FixedRandGenerator(2, 3);
    arena = new BattleArena("Jin", "Jun", randomGenerator);
    arena.equipPlayerWithGears();
    arena.requestWeaponForPlayer();
    String s = "Players are ready for Battle\n" +
            "\n" +
            "-------------------------- Move No 1 --------------------------\n" +
            "Jun is attacking Jin\n" +
            "\n" +
            "Striking Power of Attacker 21\n" +
            "Defender Avoidance Ability 2\n" +
            "Health of the attacker Jun: 59\n" +
            "Health of the defendant Jin: 18\n" +
            "Attacker Damage Value: 23\n" +
            "Attack successful, Damage done: 23\n" +
            "-------------------------- Game Over --------------------------\n" +
            "Jin Wins!!";
    //you can see that as Jun will have random value 3, he will have more charisma
    // and hence Jun is starting the battle and is the attacker
    assertEquals(s, arena.startBattle());
  }

  @Test
  public void testForSuccessfulAttack() {
    randomGenerator = new FixedRandGenerator(2, 3);
    arena = new BattleArena("Jin", "Jun", randomGenerator);
    arena.equipPlayerWithGears();
    arena.requestWeaponForPlayer();
    String s = "Players are ready for Battle\n" +
            "\n" +
            "-------------------------- Move No 1 --------------------------\n" +
            "Jun is attacking Jin\n" +
            "\n" +
            "Striking Power of Attacker 21\n" +
            "Defender Avoidance Ability 2\n" +
            "Health of the attacker Jun: 59\n" +
            "Health of the defendant Jin: 18\n" +
            "Attacker Damage Value: 23\n" +
            "Attack successful, Damage done: 23\n" +
            "-------------------------- Game Over --------------------------\n" +
            "Jin Wins!!";
    //As striking power of Attacker is greater than Defender,
    // the attack is successful and damage is done
    assertEquals(s, arena.startBattle());
  }

  @Test
  public void testForUnSuccessfulAttack() {
    randomGenerator = new FixedRandGenerator(2, 3, 2, 1, 1, 3, 3, 1, 1, 3);
    arena = new BattleArena("Jin", "Jun", randomGenerator);
    arena.equipPlayerWithGears();
    arena.requestWeaponForPlayer();
    //As striking power of Attacker is lesser than Defender avoidance ability,
    // hence the attack is successful and no damage is incurred
    assertTrue(arena.startBattle().contains("Attack was unsuccessful"));
  }

  @Test
  public void testForHealthChangeAfterDamage() {
    randomGenerator = new FixedRandGenerator(2, 3, 1, 3);
    arena = new BattleArena("Jin", "Jun", randomGenerator);
    arena.equipPlayerWithGears();
    arena.requestWeaponForPlayer();
    //In move 2 the health of player Jin is changes from 12 to 1 after
    // successful attack of damage 11
    assertEquals(true, arena.startBattle().contains("-------------------------- "
            + "Move No 6 --------------------------\n"
            + "Jin is attacking Jun\n" + "\n"
            + "Striking Power of Attacker 5\n"
            + "Defender Avoidance Ability 2\n"
            + "Health of the attacker Jin: 20\n"
            + "Health of the defendant Jun: 33\n"
            + "Attacker Damage Value: 4\n"
            + "Attack successful, Damage done: 4\n"
            + "-------------------------- Move No 7 --------------------------\n"
            + "Jun is attacking Jin\n" + "\n"
            + "Striking Power of Attacker 5\n"
            + "Defender Avoidance Ability 2\n"
            + "Health of the attacker Jun: 29\n"
            + "Health of the defendant Jin: 20\n"
            + "Attacker Damage Value: -2\n" + "Attack successful, but Damage done 0 "));
  }

  @Test
  public void testForDamageZeroHealthSame() {
    randomGenerator = new FixedRandGenerator(2, 1, 1, 3);
    arena = new BattleArena("Jin", "Jun", randomGenerator);
    arena.equipPlayerWithGears();
    arena.requestWeaponForPlayer();
    //In move 7 the damage value of the attacker Jun is -1, and the damage value of defendant
    // Jin is not changed as the health of the attacker is not greater than 0
    assertEquals(true, arena.startBattle().contains("" +
            "-------------------------- Move No 4 --------------------------\n"
            + "Jin is attacking Jun\n" + "\n"
            + "Striking Power of Attacker 3\n"
            + "Defender Avoidance Ability 3\n"
            + "Health of the attacker Jin: 18\n"
            + "Health of the defendant Jun: 27\n"
            + "Attacker Damage Value: -3\n"
            + "Attack was unsuccessful\n"
            + "-------------------------- Move No 5 --------------------------\n"
            + "Jun is attacking Jin\n" + "\n"
            + "Striking Power of Attacker 3\n" + "Defender Avoidance Ability 3\n"
            + "Health of the attacker Jun: 27\n" + "Health of the defendant Jin: 18\n"
            + "Attacker Damage Value: -1\n" + "Attack was unsuccessful"));
  }

  @Test
  public void getSortedEquipments() {

    Equipment headGear1 = new HeadGear("HeadGear 1", new FixedRandGenerator(2));
    Equipment potion2 = new Potion("Potion 2", new FixedRandGenerator(2));
    Equipment footwear1 = new Footwear("Footwear 1", new FixedRandGenerator(2));
    Equipment belt2 = new Belt("Belt 2", new FixedRandGenerator(2));
    Equipment potion1 = new Potion("Potion 1", new FixedRandGenerator(2));
    Equipment belt1 = new Belt("Belt 1", new FixedRandGenerator(2));

    List<Equipment> unsortedList = new ArrayList<>();
    unsortedList.add(potion1);
    unsortedList.add(belt1);
    unsortedList.add(headGear1);
    unsortedList.add(footwear1);
    unsortedList.add(belt2);
    unsortedList.add(potion2);

    List<Equipment> equipmentList = new ArrayList<>();

    equipmentList.add(headGear1);
    equipmentList.add(potion1);
    equipmentList.add(potion2);
    equipmentList.add(belt1);
    equipmentList.add(belt2);
    equipmentList.add(footwear1);

    assertEquals(equipmentList, arena.getSortedGearList(unsortedList));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testForNullListForSort() {
    arena.getSortedGearList(null);
  }

  @Test
  public void testFor25percentNegativeValues() {
    int count = 0;
    for (int i = 0; i < arena.getGearBag().size(); i++) {
      if (arena.getGearBag().get(i).getEffectValue() < 0) {
        count++;
      }
    }
    assertEquals(10, count); //25% of 40 is 10, values for 10 objects in a bag are negative
  }

  @Test
  public void getArmory() {
    assertEquals(5, arena.getWeaponArmory().size());
    assertTrue(arena.getWeaponArmory().get(0) instanceof Axe);
    assertTrue(arena.getWeaponArmory().get(1) instanceof Flail);
    assertTrue(arena.getWeaponArmory().get(2) instanceof Katana);
    assertTrue(arena.getWeaponArmory().get(3) instanceof Broadsword);
    assertTrue(arena.getWeaponArmory().get(4) instanceof TwoHandedSword);
  }

  @Test
  public void startBattle() {
    String s = "Players are ready for Battle\n" +
            "Charisma same! Game Draw, No one Wins";
    assertEquals(s, arena.startBattle());
  }

  @Test
  public void getPlayerDescription() {
    String s = "************** Player Description **************\n"
            + "Player Name: Tom\n"
            + "Charisma: 1, Constitution: 5, Dexterity: 0, Strength: 1\n"
            + "Gears - \n" +
            "HeadgearA\n"
            + "BeltA\n"
            + "BeltB\n"
            + "BeltC\n"
            + "BeltD\n"
            + "BeltE\n"
            + "FootwearA\n"
            + "Weapon: Flail\n"
            + "************** Player Description **************\n"
            + "Player Name: Jerry\n"
            + "Charisma: 1, Constitution: 21, Dexterity: 1, Strength: 1\n"
            + "Gears - \n"
            + "PotionA\n"
            + "PotionB\n"
            + "PotionC\n"
            + "PotionD\n"
            + "PotionE\n"
            + "PotionF\n"
            + "PotionG\n"
            + "PotionH\n"
            + "PotionI\n"
            + "PotionJ\n"
            + "PotionK\n"
            + "PotionL\n"
            + "PotionM\n"
            + "PotionN\n"
            + "PotionO\n"
            + "BeltK\n"
            + "BeltL\n"
            + "BeltM\n"
            + "BeltN\n"
            + "BeltO\n"
            + "Weapon: Flail";
    assertEquals(s, arena.getPlayerDescription());
  }

  @Test
  public void getPlayer1() {
    arena = new BattleArena("Tom", "Jerry", randomGenerator);
    assertEquals("Tom", arena.getPlayer1().getName());
  }

  @Test
  public void getPlayer2() {
    arena = new BattleArena("Tom", "Jerry", randomGenerator);
    assertEquals("Jerry", arena.getPlayer2().getName());
  }

  @Test
  public void testForRandomGears() {
    arena = new BattleArena("Tom", "Jerry", randomGenerator);
    //get original List of 40 equipments
    List<Equipment> originalList = arena.getGearBag();

    List<Equipment> playerGears = new ArrayList<>();
    //get equipment list for Player 1;
    for (Map.Entry<EquipmentType, List<Equipment>> entry :
            arena.getPlayer1().getPlayerBag().entrySet()) {
      playerGears.addAll(entry.getValue());
    }
    //get equipment list for player2
    for (Map.Entry<EquipmentType, List<Equipment>> entry :
            arena.getPlayer2().getPlayerBag().entrySet()) {
      playerGears.addAll(entry.getValue());
    }

    //Check if original list is same as merger of 2 player lists.
    assertNotEquals(originalList, playerGears);
  }

  @Test
  public void testForRandomWeapon() {
    randomGenerator = new FixedRandGenerator(1, 2, 3, 2);
    arena = new BattleArena("Karan", "Arjun", randomGenerator);
    arena.requestWeaponForPlayer();
    Weapon weapon1 = arena.getPlayer1().getCurrentWeapon();
    arena.requestWeaponForPlayer();
    Weapon weapon2 = arena.getPlayer1().getCurrentWeapon();
    //both the weapons are different as it is randomly assigned
    assertNotEquals(weapon1, weapon2);
  }

}