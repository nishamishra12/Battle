package weapontest;

import org.junit.Before;
import org.junit.Test;

import randomizer.FixedRandGenerator;
import weapon.Axe;
import weapon.Weapon;

import static org.junit.Assert.*;

public class AxeTest {

  Weapon axe;

  @Before
  public void setUp() throws Exception {
    this.axe = new Axe(new FixedRandGenerator(3));
  }

  @Test
  public void getDamage() {
    assertEquals(3,this.axe.getDamage());
  }
}
