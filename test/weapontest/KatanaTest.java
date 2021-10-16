package weapontest;

import org.junit.Before;
import org.junit.Test;

import randomizer.FixedRandGenerator;
import randomizer.Randomizer;
import weapon.Katana;
import weapon.Weapon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class to check all the implementation of the Weapon type Katana.
 */
public class KatanaTest {

  private Weapon katana;

  @Before
  public void setUp() throws Exception {
    this.katana = new Katana(new FixedRandGenerator(3));
  }

  @Test
  public void getDamage() {
    assertEquals(6, this.katana.getDamage());
  }

  @Test
  public void getDoubleDamage() {
    Weapon katana2 = new Katana(new FixedRandGenerator(5));
    assertEquals(10, katana2.getDamage()); //damage is doubled, as Katana's are in pair
  }

  @Test
  public void checkRangeOfDamage() {
    katana = new Katana(new Randomizer());
    //2 katanas range double
    assertTrue(katana.getDamage() >= 8 && katana.getDamage() <= 12);
  }
}