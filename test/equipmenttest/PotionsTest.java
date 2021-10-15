package equipmenttest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import equipment.Equipment;
import equipment.EquipmentType;
import equipment.HeadGear;
import equipment.Potions;
import player.Ability;
import randomizer.FixedRandGenerator;

import static org.junit.Assert.*;

public class PotionsTest {

  Equipment potions;

  @Before
  public void setUp() throws Exception {
    this.potions =  new Potions("potions N", new FixedRandGenerator(2));
  }

  @Test
  public void compareTo() {
    Equipment potions1 = new Potions("potions M",new FixedRandGenerator(3));
    assertEquals(-1, potions1.compareTo(this.potions));
  }

  @Test
  public void getEffectValue() {
    assertEquals(2, this.potions.getEffectValue());
  }

  @Test
  public void getEffectAbility() {
    assertEquals(Ability.CHARISMA, this.potions.getEffectAbility().get(0));
  }

  @Test
  public void getBeltSize() {
    assertEquals(-1, this.potions.getBeltSize());
  }

  @Test
  public void setEffectValueNegative() {
    this.potions.setEffectValueNegative();
    assertEquals(-2, this.potions.getEffectValue());
  }

  @Test
  public void getEquipmentType() {
    Assert.assertEquals(EquipmentType.POTION,this.potions.getEquipmentType());
  }

  @Test
  public void getName() {
    assertEquals("potions N", this.potions.getName());
  }
}
