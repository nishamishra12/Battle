package equipmenttest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import equipment.Equipment;
import equipment.EquipmentType;
import equipment.Footwear;
import equipment.HeadGear;
import player.Ability;
import randomizer.FixedRandGenerator;

import static org.junit.Assert.*;

public class FootwearTest {

  Equipment footwear;
  @Before
  public void setUp() throws Exception {
    this.footwear =  new Footwear("footwear N", new FixedRandGenerator(2));
  }

  @Test
  public void compareTo() {

    Equipment footwear1 = new Footwear("footwear M",new FixedRandGenerator(3));
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
    Assert.assertEquals(EquipmentType.FOOTWEAR,this.footwear.getEquipmentType());
  }

  @Test
  public void getName() {
    assertEquals("footwear N", this.footwear.getName());
  }
}