package weapontest;

import org.junit.Before;
import org.junit.Test;

import randomizer.FixedRandGenerator;
import randomizer.Randomizer;
import weapon.Flail;
import weapon.Weapon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class to check all the implementation of the Weapon type Flail.
 */
public class FlailTest {

  private Weapon flail;

  @Before
  public void setUp() throws Exception {
    this.flail = new Flail(new FixedRandGenerator(3));
  }

  @Test
  public void getDamage() {
    assertEquals(3, this.flail.getDamage());
  }

  @Test
  public void checkRangeOfDamage() {
    flail = new Flail(new Randomizer());
    assertTrue(flail.getDamage() >= 8 && flail.getDamage() <= 12);
  }
}