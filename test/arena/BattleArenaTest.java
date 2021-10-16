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
  public void testForAttackerHasGreaterCharisma() {
    randomGenerator = new FixedRandGenerator(2, 3);
    arena = new BattleArena("Jin", "Jun", randomGenerator);
    String s = "Players are ready for Battle\n" +
            "\n" +
            "-------------------------- Move No 1 --------------------------\n" +
            "Jun is attacking Jin\n" +
            "\n" +
            "Striking Power of Attacker 21\n" +
            "Defender Avoidance Ability 2\n" +
            "Health of the attacker Jun: 59\n" +
            "Health of the defendant Jin: 18\n" +
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
    String s = "Players are ready for Battle\n" +
            "\n" +
            "-------------------------- Move No 1 --------------------------\n" +
            "Jun is attacking Jin\n" +
            "\n" +
            "Striking Power of Attacker 21\n" +
            "Defender Avoidance Ability 2\n" +
            "Health of the attacker Jun: 59\n" +
            "Health of the defendant Jin: 18\n" +
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
    //As striking power of Attacker is lesser than Defender avoidance ability,
    // hence the attack is successful and no damage is incurred
    assertTrue(arena.startBattle().contains("Attack was unsuccessful"));
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
    assertEquals("Axe", arena.getWeaponArmory().get(0).getClass().getSimpleName());
    assertEquals("Flail", arena.getWeaponArmory().get(1).getClass().getSimpleName());
    assertEquals("Katana", arena.getWeaponArmory().get(2).getClass().getSimpleName());
    assertEquals("Broadsword", arena.getWeaponArmory().get(3).getClass().getSimpleName());
    assertEquals("TwoHandedSword", arena.getWeaponArmory().get(4).getClass().getSimpleName());
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
  public void testToCheckPlayersAreAssignedRandomGears() {
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
    assertNotEquals(originalList,playerGears);

  }
}