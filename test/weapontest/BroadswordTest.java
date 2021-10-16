package weapontest;

import org.junit.Before;
import org.junit.Test;

import randomizer.FixedRandGenerator;
import randomizer.Randomizer;
import weapon.Broadsword;
import weapon.Weapon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class to check all the implementation of the Weapon type Broadsword.
 */
public class BroadswordTest {

  private Weapon broadsword;

  @Before
  public void setUp() throws Exception {
    this.broadsword = new Broadsword(new FixedRandGenerator(3));
  }

  @Test
  public void getDamage() {
    assertEquals(3, this.broadsword.getDamage());
  }

  @Test
  public void checkRangeOfDamage() {
    broadsword = new Broadsword(new Randomizer());
    assertTrue(broadsword.getDamage() >= 6 && broadsword.getDamage() <= 10);
  }
}