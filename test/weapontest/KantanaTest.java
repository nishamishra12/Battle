package weapontest;

import org.junit.Before;
import org.junit.Test;

import randomizer.FixedRandGenerator;
import weapon.Kantana;
import weapon.TwoHandedSword;
import weapon.Weapon;

import static org.junit.Assert.*;

public class KantanaTest {

  Weapon katana;

  @Before
  public void setUp() throws Exception {
    this.katana = new Kantana(new FixedRandGenerator(3));
  }

  @Test
  public void getDamage() {
    assertEquals(6, this.katana.getDamage());
  }
}