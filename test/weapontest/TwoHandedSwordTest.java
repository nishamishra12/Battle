package weapontest;

import org.junit.Before;
import org.junit.Test;

import randomizer.FixedRandGenerator;
import weapon.TwoHandedSword;
import weapon.Weapon;

import static org.junit.Assert.*;

public class TwoHandedSwordTest {

  Weapon twoHandedSword;

  @Before
  public void setUp() throws Exception {
    this.twoHandedSword = new TwoHandedSword(new FixedRandGenerator(3));
  }

  @Test
  public void getDamage() {
    assertEquals(3, this.twoHandedSword.getDamage());
  }
}