package playertest;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
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
import randomizer.FixedRandGenerator;
import randomizer.RandomGenerator;
import randomizer.Randomizer;
import weapon.Broadsword;
import weapon.Flail;
import weapon.TwoHandedSword;
import weapon.Weapon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class to check all the implementation of the player playing in the battle arena.
 */
public class BattlePlayerTest {

  private Player player;
  private RandomGenerator randomGenerator = new FixedRandGenerator(2);
  private List<Equipment> equipmentList;
  private Weapon weapon;

  @Before
  public void setUp() throws Exception {
    player = new BattlePlayer("tom", randomGenerator);
    equipmentList = new ArrayList<>();
    equipmentList.add(new Belt("Belt", randomGenerator));
    equipmentList.add(new Potion("Potion", randomGenerator));
    equipmentList.add(new HeadGear("Headgear", randomGenerator));
    equipmentList.add(new Footwear("Footwear", randomGenerator));
    weapon = new Broadsword(randomGenerator);
  }

  @Test
  public void getName() {
    assertEquals("tom", player.getName());
  }

  @Test
  public void getStrength() {
    assertEquals(2, player.getStrength());
  }

  @Test
  public void getConstitution() {
    assertEquals(2, player.getConstitution());
  }

  @Test
  public void getDexterity() {
    assertEquals(2, player.getConstitution());
  }

  @Test
  public void getCharisma() {
    assertEquals(2, player.getCharisma());
  }

  @Test
  public void calculateHealth() {
    player.calculateInitialHealth();
    assertEquals(8, player.getHealth());
  }

  @Test
  public void testForBasicHealth() {
    player = new BattlePlayer("tom", randomGenerator);
    player.calculateInitialHealth();
    assertEquals(player.getConstitution() + player.getDexterity() + player.getCharisma()
            + player.getStrength(), player.getHealth());
  }

  @Test
  public void testForHalfDamageForFlail() {
    assertTrue(player.getDexterity() < 14);
    Weapon flail = new Flail(randomGenerator);
    player.setCurrentWeapon(flail);
    player.calculatePlayerPowers(player);
    int playerDamage = player.getPotentialDamage() - player.getStrength();
    assertEquals(playerDamage, flail.getDamage() / 2);
  }

  @Test
  public void testForHalfDamageForTwoHandedSword() {
    assertTrue(player.getStrength() < 14);
    Weapon twoHandedSword = new TwoHandedSword(randomGenerator);
    player.setCurrentWeapon(twoHandedSword);
    player.calculatePlayerPowers(player);
    int playerDamage = player.getPotentialDamage() - player.getStrength();
    assertEquals(playerDamage, twoHandedSword.getDamage() / 2);
  }

  @Test
  public void calculatePlayerPowers() {
    player.setCurrentWeapon(weapon);
    player.calculatePlayerPowers(player);

    assertEquals(4, player.getStrikingPower());
    assertEquals(4, player.getAvoidanceAbility());
    assertEquals(4, player.getPotentialDamage());
    assertEquals(2, player.getActualDamage());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testForNullObject() {
    player.calculatePlayerPowers(null);
  }

  @Test
  public void addHeadgearChangeAbility() {
    assertEquals(2, player.getConstitution());
    player.addEquipment(new HeadGear("HG", randomGenerator));
    player.calculateInitialHealth();
    assertEquals(4, player.getConstitution());
  }

  @Test
  public void addFootwearChangeAbility() {
    assertEquals(2, player.getDexterity());
    player.addEquipment(new Footwear("FW", randomGenerator));
    player.calculateInitialHealth();
    assertEquals(4, player.getDexterity());
  }

  @Test
  public void addPotionChangeAbility() {
    assertEquals(2, player.getStrength());
    randomGenerator = new FixedRandGenerator(3);
    player.addEquipment(new Potion("PT", randomGenerator));
    player.addEquipment(new Potion("PT2", randomGenerator));
    player.calculateInitialHealth();
    assertEquals(8, player.getStrength());
  }

  @Test
  public void addBeltChangeAbility() {
    assertEquals(2, player.getCharisma());
    randomGenerator = new FixedRandGenerator(2, 2);
    player.addEquipment(new Belt("Belt2", randomGenerator));
    player.calculateInitialHealth();
    assertEquals(6, player.getCharisma());
  }

  @Test
  public void removeHeadgearChangeAbility() {
    //original Constitution
    assertEquals(2, player.getConstitution());
    player.addEquipment(new HeadGear("HG", randomGenerator));
    player.calculateInitialHealth();
    //constitution after adding gear
    assertEquals(4, player.getConstitution());
    //remove headgear, constitution should go back to original
    player.removeGears(2);
    assertEquals(2, player.getConstitution());
  }

  @Test
  public void removeFootwearChangeAbility() {
    //original Dexterity
    assertEquals(2, player.getDexterity());
    player.addEquipment(new Footwear("FW", randomGenerator));
    player.calculateInitialHealth();
    //dexterity after adding gear
    assertEquals(4, player.getDexterity());
    //remove footwear, dexterity should go back to original
    player.removeGears(2);
    assertEquals(2, player.getDexterity());
  }

  @Test
  public void removePotionChangeAbility() {
    //original Constitution
    assertEquals(2, player.getStrength());
    randomGenerator = new FixedRandGenerator(3);
    player.addEquipment(new Potion("PT", randomGenerator));
    player.calculateInitialHealth();
    //constitution after adding gear
    assertEquals(5, player.getStrength());
    //remove headgear, constitution should go back to original
    player.removeGears(3);
    assertEquals(2, player.getStrength());
  }

  @Test
  public void removeBeltChangeAbility() {
    //original Constitution
    assertEquals(2, player.getCharisma());
    randomGenerator = new FixedRandGenerator(2, 2);
    player.addEquipment(new Belt("Belt", randomGenerator));
    player.calculateInitialHealth();
    //constitution after adding gear
    assertEquals(6, player.getCharisma());
    //remove headgear, constitution should go back to original
    player.removeGears(2);
    assertEquals(2, player.getCharisma());
  }

  @Test
  public void removeGears() {
    Equipment headGear1 = new HeadGear("HeadGear 1", new FixedRandGenerator(2));
    Equipment potion1 = new Potion("Potion 1", new FixedRandGenerator(3));

    player.addEquipment(headGear1);
    player.addEquipment(potion1);

    Map<EquipmentType, List<Equipment>> equipmentTypeList = new HashMap<>();

    List<Equipment> headGearList = new ArrayList<>();
    headGearList.add(headGear1);
    List<Equipment> potionList = new ArrayList<>();
    potionList.add(potion1);

    equipmentTypeList.put(EquipmentType.HEADGEAR, headGearList);
    equipmentTypeList.put(EquipmentType.POTION, potionList);

    assertEquals(equipmentTypeList, player.getPlayerBag());

    equipmentTypeList.get(EquipmentType.HEADGEAR).remove(0);
    player.removeGears(2);

    assertEquals(equipmentTypeList, player.getPlayerBag());

    equipmentTypeList.get(EquipmentType.POTION).remove(0);
    player.removeGears(3);

    assertEquals(equipmentTypeList, player.getPlayerBag());
  }

  @Test
  public void getStrikingPower() {
    player.setCurrentWeapon(weapon);
    player.calculatePlayerPowers(player);
    assertEquals(4, player.getStrikingPower());
  }

  @Test
  public void updateHealth() {
    player.updateHealth(4);
    assertEquals(4, player.getHealth());
  }

  @Test
  public void getAvoidanceAbility() {
    player.setCurrentWeapon(weapon);
    player.calculatePlayerPowers(player);
    assertEquals(4, player.getAvoidanceAbility());
  }

  @Test
  public void getPotentialDamage() {
    player.setCurrentWeapon(weapon);
    player.calculatePlayerPowers(player);
    assertEquals(4, player.getPotentialDamage());
  }

  @Test
  public void getActualDamage() {
    player.setCurrentWeapon(weapon);
    player.calculatePlayerPowers(player);
    assertEquals(2, player.getActualDamage());
  }

  @Test
  public void getHealth() {
    player.calculateInitialHealth();
    assertEquals(8, player.getHealth());
  }

  @Test
  public void getPlayerBag() {
    List<Equipment> equipmentList2 = new ArrayList<>();
    equipmentList2.add(new Belt("BeltA", randomGenerator));
    equipmentList2.add(new Potion("PotionB", randomGenerator));
    equipmentList2.add(new HeadGear("HeadgearC", randomGenerator));
    equipmentList2.add(new Footwear("FootwearD", randomGenerator));
    equipmentList2.add(new Belt("BeltB", randomGenerator));
    equipmentList2.add(new Potion("PotionV", randomGenerator));
    equipmentList2.add(new HeadGear("HeadgearS", randomGenerator));
    equipmentList2.add(new Footwear("FootwearU", randomGenerator));
    player.addEquipment(equipmentList2.get(0));
    player.addEquipment(equipmentList2.get(1));
    player.addEquipment(equipmentList2.get(2));
    player.addEquipment(equipmentList2.get(3));
    player.addEquipment(equipmentList2.get(4));
    player.addEquipment(equipmentList2.get(5));
    player.addEquipment(equipmentList2.get(6));
    player.addEquipment(equipmentList2.get(7));

    List<Equipment> list1 = new ArrayList<>();
    list1.add(equipmentList2.get(0));
    list1.add(equipmentList2.get(4));
    List<Equipment> list2 = new ArrayList<>();
    list2.add(equipmentList2.get(1));
    list2.add(equipmentList2.get(5));
    List<Equipment> list3 = new ArrayList<>();
    list3.add(equipmentList2.get(2));
    List<Equipment> list4 = new ArrayList<>();
    list4.add(equipmentList2.get(3));

    Map<EquipmentType, List<Equipment>> gearBag = new HashMap<>();
    gearBag.put(EquipmentType.BELT, list1);
    gearBag.put(EquipmentType.POTION, list2);
    gearBag.put(EquipmentType.HEADGEAR, list3);
    gearBag.put(EquipmentType.FOOTWEAR, list4);
    assertEquals(gearBag, player.getPlayerBag());
  }

  @Test
  public void getCurrentWeapon() {
    player.setCurrentWeapon(weapon);
    assertEquals(weapon, player.getCurrentWeapon());
  }

  @Test
  public void setCurrentWeapon() {
    player.setCurrentWeapon(weapon);
    assertEquals(weapon, player.getCurrentWeapon());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testForNullCurrentWeapon() {
    player.setCurrentWeapon(null);
  }

  @Test
  public void addEquipment() {
    player.addEquipment(this.equipmentList.get(0));
    player.addEquipment(this.equipmentList.get(1));
    player.addEquipment(this.equipmentList.get(2));
    player.addEquipment(this.equipmentList.get(3));

    List<Equipment> list1 = new ArrayList<>();
    list1.add(this.equipmentList.get(0));
    List<Equipment> list2 = new ArrayList<>();
    list2.add(this.equipmentList.get(1));
    List<Equipment> list3 = new ArrayList<>();
    list3.add(this.equipmentList.get(2));
    List<Equipment> list4 = new ArrayList<>();
    list4.add(this.equipmentList.get(3));

    Map<EquipmentType, List<Equipment>> gearBag = new HashMap<>();
    gearBag.put(EquipmentType.BELT, list1);
    gearBag.put(EquipmentType.POTION, list2);
    gearBag.put(EquipmentType.HEADGEAR, list3);
    gearBag.put(EquipmentType.FOOTWEAR, list4);
    assertEquals(gearBag, player.getPlayerBag());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testForNullEquipmentPassed() {
    player.addEquipment(null);
  }

  @Test
  public void testForOnlyOneHeadgear() {
    player.addEquipment(new HeadGear("Headgear abc", randomGenerator));
    player.addEquipment(new HeadGear("Headgear xyz", randomGenerator));
    //only one headgear should be added
    assertEquals(1, player.getPlayerBag().get(EquipmentType.HEADGEAR).size());
  }

  @Test
  public void testForOnlyOneFootwear() {
    player.addEquipment(new Footwear("Footwear abc", randomGenerator));
    player.addEquipment(new Footwear("Footwear xyz", randomGenerator));
    //only one headgear should be added
    assertEquals(1, player.getPlayerBag().get(EquipmentType.FOOTWEAR).size());
  }

  @Test
  public void testForBeltLessThanTenUnits() {
    randomGenerator = new FixedRandGenerator(2);
    player = new BattlePlayer("Bob", randomGenerator);
    player.addEquipment(new Belt("Belt A", randomGenerator));
    player.addEquipment(new Belt("Belt B", randomGenerator));
    player.addEquipment(new Belt("Belt C", randomGenerator));
    //last object not added as size after adding will be greater than 10
    assertEquals(2, player.getPlayerBag().get(EquipmentType.BELT).size());
  }

  @Test
  public void testForBeltExactlyTenUnits() {
    randomGenerator = new FixedRandGenerator(2);
    player = new BattlePlayer("Bob", randomGenerator);
    player.addEquipment(new Belt("Belt A", randomGenerator));
    player.addEquipment(new Belt("Belt B", randomGenerator));
    randomGenerator = new FixedRandGenerator(1);
    player.addEquipment(new Belt("Belt C", randomGenerator));
    int count = 0;
    for (int i = 0; i < player.getPlayerBag().get(EquipmentType.BELT).size(); i++) {
      count = count + player.getPlayerBag().get(EquipmentType.BELT).get(i).getBeltSize();
    }
    //only 10 units of belts are added to the bag
    assertEquals(3, player.getPlayerBag().get(EquipmentType.BELT).size());
    assertEquals(10, count);
    //last object not added as size after adding will be greater than 10
    assertEquals(3, player.getPlayerBag().get(EquipmentType.BELT).size());
  }

  @Test
  public void testForBasicStrengthInRange() {
    randomGenerator = new Randomizer();
    player = new BattlePlayer("tom", randomGenerator);
    assertTrue(player.getStrength() >= 6 && player.getStrength() <= 18);
  }

  @Test
  public void testForBasicCharismaInRange() {
    randomGenerator = new Randomizer();
    player = new BattlePlayer("tom", randomGenerator);
    assertTrue(player.getCharisma() >= 6 && player.getCharisma() <= 18);
  }

  @Test
  public void testForBasicDexterityInRange() {
    randomGenerator = new Randomizer();
    player = new BattlePlayer("tom", randomGenerator);
    assertTrue(player.getDexterity() >= 6 && player.getDexterity() <= 18);
  }

  @Test
  public void testForBasicConstitutionInRange() {
    randomGenerator = new Randomizer();
    player = new BattlePlayer("tom", randomGenerator);
    assertTrue(player.getConstitution() >= 6 && player.getConstitution() <= 18);
  }
}