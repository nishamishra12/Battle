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
import weapon.Broadsword;
import weapon.Weapon;

import static org.junit.Assert.assertEquals;

public class BattlePlayerTest {

  Player player;
  RandomGenerator randomGenerator = new FixedRandGenerator(2);
  List<Equipment> equipmentList;
  Weapon weapon;

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
  public void calculateInitialHealth() {
    player.calculateInitialHealth();
    assertEquals(8, player.getHealth());
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
    assertEquals(weapon,player.getCurrentWeapon());
  }

  @Test
  public void setCurrentWeapon() {
    player.setCurrentWeapon(weapon);
    assertEquals(weapon,player.getCurrentWeapon());
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
}