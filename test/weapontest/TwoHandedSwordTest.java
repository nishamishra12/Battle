package weapontest;

import org.junit.Before;
import org.junit.Test;

import randomizer.FixedRandGenerator;
import randomizer.Randomizer;
import weapon.TwoHandedSword;
import weapon.Weapon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test to check all the implementation of the Weapon type Two Handed Sword.
 */
public class TwoHandedSwordTest {

  private Weapon twoHandedSword;

  @Before
  public void setUp() throws Exception {
    this.twoHandedSword = new TwoHandedSword(new FixedRandGenerator(3));
  }

  @Test
  public void getDamage() {
    assertEquals(3, this.twoHandedSword.getDamage());
  }

  @Test
  public void checkRangeOfDamage() {
    twoHandedSword = new TwoHandedSword(new Randomizer());
    assertTrue(twoHandedSword.getDamage() >= 8 && twoHandedSword.getDamage() <= 12);
  }
}