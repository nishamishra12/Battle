package equipmenttest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import equipment.Equipment;
import equipment.EquipmentType;
import equipment.Potion;
import player.Ability;
import randomizer.FixedRandGenerator;

import static org.junit.Assert.assertEquals;

/**
 * Test class to check all the implementation of equipment type potion.
 */
public class PotionTest {

  private Equipment potion;

  @Before
  public void setUp() throws Exception {
    this.potion = new Potion("potions N", new FixedRandGenerator(2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testForNullPotionName() {
    new Potion(null, new FixedRandGenerator(2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testForNullRandomizer() {
    new Potion("Potion", null);
  }

  @Test
  public void compareTo() {
    Equipment potions1 = new Potion("potions M", new FixedRandGenerator(3));
    assertEquals(-1, potions1.compareTo(this.potion));
  }

  @Test
  public void getEffectValue() {
    assertEquals(2, this.potion.getEffectValue());
  }

  @Test
  public void getEffectAbility() {
    assertEquals(Ability.CHARISMA, this.potion.getEffectAbility().get(0));
  }

  @Test
  public void getBeltSize() {
    assertEquals(-1, this.potion.getBeltSize());
  }

  @Test
  public void setEffectValueNegative() {
    this.potion.setEffectValueNegative();
    assertEquals(-2, this.potion.getEffectValue());
  }

  @Test
  public void getEquipmentType() {
    Assert.assertEquals(EquipmentType.POTION, this.potion.getEquipmentType());
  }

  @Test
  public void getName() {
    assertEquals("potions N", this.potion.getName());
  }

  @Test
  public void getMove() {
    assertEquals(2, this.potion.getMove());
  }
}
