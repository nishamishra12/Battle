package player;

import org.junit.Before;
import org.junit.Test;

import randomizer.Randomizer;

import static org.junit.Assert.*;

public class RandomizerTest {
  Randomizer dice = new Randomizer();

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void rollMyDiceFourTimes() {
    assertTrue(dice.rollMyDiceFourTimes()<=18 && dice.rollMyDiceFourTimes()>=6);
  }
}