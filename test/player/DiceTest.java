package player;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiceTest {
  Dice dice = new Dice();

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void rollMyDiceFourTimes() {
    assertTrue(dice.rollMyDiceFourTimes()<=18 && dice.rollMyDiceFourTimes()>=6);
  }
}