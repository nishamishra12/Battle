package weapontest;

import org.junit.Before;
import org.junit.Test;

import randomizer.FixedRandGenerator;
import weapon.Axe;
import weapon.Flail;
import weapon.Weapon;

import static org.junit.Assert.*;

public class FlailTest {

  Weapon flail;

  @Before
  public void setUp() throws Exception {
    this.flail = new Flail(new FixedRandGenerator(3));
  }

  @Test
  public void getDamage() {
    assertEquals(3,this.flail.getDamage());
  }
}