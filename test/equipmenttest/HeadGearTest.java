package equipmenttest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import equipment.Equipment;
import equipment.EquipmentType;
import equipment.HeadGear;
import player.Ability;
import randomizer.FixedRandGenerator;
import randomizer.Randomizer;

import static org.junit.Assert.*;

public class HeadGearTest {

  Equipment headGear;

  @Before
  public void setUp() throws Exception {
    this.headGear =  new HeadGear("Headgear N", new FixedRandGenerator(2));
  }

  @Test
  public void compareTo() {
    Equipment headGear1 = new HeadGear("Headgear M",new FixedRandGenerator(3));
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
    Assert.assertEquals(EquipmentType.HEADGEAR,this.headGear.getEquipmentType());
  }

  @Test
  public void getName() {
    assertEquals("Headgear N", this.headGear.getName());
  }
}