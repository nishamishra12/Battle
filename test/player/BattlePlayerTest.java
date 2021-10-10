package player;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BattlePlayerTest {

  Player player;

  @Before
  public void setUp() throws Exception {
   player = new BattlePlayer(10,14,16,18);
  }

  @Test
  public void getStrength() {
    assertEquals(10,player.getStrength());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testForInvalidStrengthLowerBound() {
    new BattlePlayer(1,14,16,18);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testForInvalidStrengthUpperBound() {
    new BattlePlayer(19,14,16,18);
  }

  @Test
  public void getConstitution() {
    assertEquals(14,player.getConstitution());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testForInvalidConstitutionLowerBound() {
    new BattlePlayer(10,1,16,18);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testForInvalidConstitutionUpperBound() {
    new BattlePlayer(10,19,16,18);
  }

  @Test
  public void getDexterity() {
    assertEquals(16,player.getDexterity());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testForInvalidDexterityLowerBound() {
    new BattlePlayer(10,14,1,18);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testForInvalidDexterityUpperBound() {
    new BattlePlayer(10,14,19,18);
  }

  @Test
  public void getCharisma() {
    assertEquals(18,player.getCharisma());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testForInvalidCharismaLowerBound() {
    new BattlePlayer(10,14,16,1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testForInvalidCharismaUpperBound() {
    new BattlePlayer(10,14,16,19);
  }
}