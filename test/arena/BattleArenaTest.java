package arena;

import org.junit.Before;
import org.junit.Test;

import equipment.EquipmentType;
import randomizer.FixedRandGenerator;
import randomizer.RandomGenerator;

import static org.junit.Assert.*;

public class BattleArenaTest {

  Arena arena;
  RandomGenerator randomGenerator = new FixedRandGenerator(1);

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
  public void getGearBag() {
    assertEquals(40, arena.getGearBag().size());
    assertEquals(EquipmentType.HEADGEAR, arena.getGearBag().get(0).getEquipmentType());
    assertEquals(EquipmentType.FOOTWEAR, arena.getGearBag().get(5).getEquipmentType());
    assertEquals(EquipmentType.BELT, arena.getGearBag().get(10).getEquipmentType());
    assertEquals(EquipmentType.POTION, arena.getGearBag().get(25).getEquipmentType());
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
  String s ="Players are ready for Battle\n" +
          "Charisma same! Game Draw, No one Wins";
  assertEquals(s, arena.startBattle());
  }

  @Test
  public void getPlayerDescription() {
    String s = "************** Player Description **************\n" +
            "Player Name: Tom\n" +
            "Charisma: 1, Constitution: 5, Dexterity: 0, Strength: 1\n" +
            "Gears - \n" +
            "HeadgearA\n" +
            "BeltA\n" +
            "BeltB\n" +
            "BeltC\n" +
            "BeltD\n" +
            "BeltE\n" +
            "FootwearA\n" +
            "Weapon: Flail\n" +
            "************** Player Description **************\n" +
            "Player Name: Jerry\n" +
            "Charisma: 1, Constitution: 21, Dexterity: 1, Strength: 1\n" +
            "Gears - \n" +
            "PotionA\n" +
            "PotionB\n" +
            "PotionC\n" +
            "PotionD\n" +
            "PotionE\n" +
            "PotionF\n" +
            "PotionG\n" +
            "PotionH\n" +
            "PotionI\n" +
            "PotionJ\n" +
            "PotionK\n" +
            "PotionL\n" +
            "PotionM\n" +
            "PotionN\n" +
            "PotionO\n" +
            "BeltK\n" +
            "BeltL\n" +
            "BeltM\n" +
            "BeltN\n" +
            "BeltO\n" +
            "Weapon: Flail";
    assertEquals(s, arena.getPlayerDescription());
  }
}