package equipmenttest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import equipment.Belt;
import equipment.Equipment;
import equipment.EquipmentType;
import player.Ability;
import randomizer.FixedRandGenerator;

import static org.junit.Assert.assertEquals;

/**
 * Test class to check all the implementation of equipment type belt.
 */
public class BeltTest {

  private Equipment belt;

  @Before
  public void setUp() throws Exception {
    this.belt =  new Belt("belt N", new FixedRandGenerator(2));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testForNullBeltName() {
    new Belt(null, new FixedRandGenerator(2));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testForNullRandomizer() {
    new Belt("Belt", null);
  }


  @Test
  public void compareTo() {
    Equipment belt1 = new Belt("belt M",new FixedRandGenerator(1));
    assertEquals(-1, belt1.compareTo(this.belt));
  }

  @Test
  public void getEffectValue() {
    assertEquals(2, this.belt.getEffectValue());
  }

  @Test
  public void testForTwoEffectAbility() {
    assertEquals(Ability.CHARISMA, this.belt.getEffectAbility().get(0));
    assertEquals(Ability.CHARISMA, this.belt.getEffectAbility().get(1));
  }

  @Test
  public void getEffectAbility() {
    assertEquals(Ability.CHARISMA, this.belt.getEffectAbility().get(0));
  }

  @Test
  public void getBeltSize() {
    assertEquals(4, this.belt.getBeltSize());
  }

  @Test
  public void setEffectValueNegative() {
    this.belt.setEffectValueNegative();
    assertEquals(-2, this.belt.getEffectValue());
  }

  @Test
  public void getEquipmentType() {
    Assert.assertEquals(EquipmentType.BELT,this.belt.getEquipmentType());
  }

  @Test
  public void getName() {
    assertEquals("belt N", this.belt.getName());
  }

  @Test
  public void getMove() {
    assertEquals(2,this.belt.getMove());
  }
}