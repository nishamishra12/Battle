package equipmenttest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import equipment.Equipment;
import equipment.EquipmentType;
import equipment.HeadGear;
import player.Ability;
import randomizer.FixedRandGenerator;

import static org.junit.Assert.assertEquals;

/**
 * Test class to check all the implementation of equipment type headgear.
 */
public class HeadGearTest {

  private Equipment headGear;

  @Before
  public void setUp() throws Exception {
    this.headGear = new HeadGear("Headgear N", new FixedRandGenerator(2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testForNullHeadgearName() {
    new HeadGear(null, new FixedRandGenerator(2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testForNullRandomizer() {
    new HeadGear("Headgear", null);
  }

  @Test
  public void compareTo() {
    Equipment headGear1 = new HeadGear("Headgear M", new FixedRandGenerator(3));
    assertEquals(-1, headGear1.compareTo(this.headGear));
  }

  @Test
  public void getEffectValue() {
    assertEquals(2, this.headGear.getEffectValue());
  }

  @Test
  public void getEffectAbility() {
    assertEquals(Ability.CONSTITUTION, this.headGear.getEffectAbility().get(0));
  }

  @Test
  public void testEffectingAbilityOnlyDexterity() {
    Equipment headgear1 = new HeadGear("HG", new FixedRandGenerator(3));
    Equipment headgear2 = new HeadGear("HG", new FixedRandGenerator(2));
    Equipment headgear3 = new HeadGear("HG", new FixedRandGenerator(1));
    assertEquals(Ability.CONSTITUTION, headgear1.getEffectAbility().get(0));
    assertEquals(Ability.CONSTITUTION, headgear2.getEffectAbility().get(0));
    assertEquals(Ability.CONSTITUTION, headgear3.getEffectAbility().get(0));
  }

  @Test
  public void getBeltSize() {
    assertEquals(-1, this.headGear.getBeltSize());
  }

  @Test
  public void setEffectValueNegative() {
    this.headGear.setEffectValueNegative();
    assertEquals(-2, this.headGear.getEffectValue());
  }

  @Test
  public void getEquipmentType() {
    Assert.assertEquals(EquipmentType.HEADGEAR, this.headGear.getEquipmentType());
  }

  @Test
  public void getName() {
    assertEquals("Headgear N", this.headGear.getName());
  }

  @Test
  public void getMove() {
    assertEquals(2, this.headGear.getMove());
  }
}