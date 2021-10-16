package weapontest;

import org.junit.Before;
import org.junit.Test;

import randomizer.FixedRandGenerator;
import randomizer.Randomizer;
import weapon.Axe;
import weapon.Weapon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class to check all the implementation of the Weapon type Axe.
 */
public class AxeTest {

  private Weapon axe;

  @Before
  public void setUp() throws Exception {
    this.axe = new Axe(new FixedRandGenerator(3));
  }

  @Test
  public void getDamage() {
    assertEquals(3, this.axe.getDamage());
  }

  @Test
  public void checkRangeOfDamage() {
    axe = new Axe(new Randomizer());
    assertTrue(axe.getDamage() >= 6 && axe.getDamage() <= 10);
  }
}
