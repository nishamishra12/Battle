package equipmenttest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import equipment.Equipment;
import equipment.EquipmentType;
import equipment.Footwear;
import player.Ability;
import randomizer.FixedRandGenerator;

import static org.junit.Assert.assertEquals;

/**
 * Test class to check all the implementation of equipment type footwear.
 */
public class FootwearTest {

  private Equipment footwear;

  @Before
  public void setUp() throws Exception {
    this.footwear = new Footwear("footwear N", new FixedRandGenerator(2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testForNullFootwearName() {
    new Footwear(null, new FixedRandGenerator(2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testForNullRandomizer() {
    new Footwear("Footwear", null);
  }

  @Test
  public void compareTo() {

    Equipment footwear1 = new Footwear("footwear M", new FixedRandGenerator(3));
    assertEquals(-1, footwear1.compareTo(this.footwear));
  }

  @Test
  public void getEffectValue() {
    assertEquals(2, this.footwear.getEffectValue());
  }

  @Test
  public void getEffectAbility() {
    assertEquals(Ability.DEXTERITY, this.footwear.getEffectAbility().get(0));
  }

  @Test
  public void testEffectingAbilityOnlyDexterity() {
    Equipment footwear1 = new Footwear("FW", new FixedRandGenerator(3));
    Equipment footwear2 = new Footwear("FW", new FixedRandGenerator(2));
    Equipment footwear3 = new Footwear("FW", new FixedRandGenerator(1));
    assertEquals(Ability.DEXTERITY, footwear1.getEffectAbility().get(0));
    assertEquals(Ability.DEXTERITY, footwear2.getEffectAbility().get(0));
    assertEquals(Ability.DEXTERITY, footwear3.getEffectAbility().get(0));
  }

  @Test
  public void getBeltSize() {
    assertEquals(-1, this.footwear.getBeltSize());
  }

  @Test
  public void setEffectValueNegative() {
    this.footwear.setEffectValueNegative();
    assertEquals(-2, this.footwear.getEffectValue());
  }

  @Test
  public void getEquipmentType() {
    Assert.assertEquals(EquipmentType.FOOTWEAR, this.footwear.getEquipmentType());
  }

  @Test
  public void getName() {
    assertEquals("footwear N", this.footwear.getName());
  }

  @Test
  public void getMove() {
    assertEquals(2, this.footwear.getMove());
  }
}