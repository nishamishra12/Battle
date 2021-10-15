package weapontest;

import org.junit.Before;
import org.junit.Test;

import randomizer.FixedRandGenerator;
import weapon.Broadsword;
import weapon.Kantana;
import weapon.Weapon;

import static org.junit.Assert.*;

public class BroadswordTest {

  Weapon broadsword;

  @Before
  public void setUp() throws Exception {
    this.broadsword = new Broadsword(new FixedRandGenerator(3));
  }

  @Test
  public void getDamage() {
    assertEquals(3, this.broadsword.getDamage());
  }
}