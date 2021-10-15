package weapontest;

import org.junit.Before;
import org.junit.Test;

import randomizer.FixedRandGenerator;
import weapon.Katana;
import weapon.Weapon;

import static org.junit.Assert.assertEquals;

public class KatanaTest {

  Weapon katana;

  @Before
  public void setUp() throws Exception {
    this.katana = new Katana(new FixedRandGenerator(3));
  }

  @Test
  public void getDamage() {
    assertEquals(6, this.katana.getDamage());
  }
}