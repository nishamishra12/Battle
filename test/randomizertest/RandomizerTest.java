package randomizertest;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import equipment.Belt;
import equipment.Equipment;
import equipment.Footwear;
import equipment.HeadGear;
import randomizer.RandomGenerator;
import randomizer.Randomizer;

import static org.junit.Assert.*;

public class RandomizerTest {

  RandomGenerator randomGenerator;

  @Before
  public void setUp() throws Exception {
    randomGenerator =  new Randomizer();
  }

  @Test
  public void getNextInt() {
    assertTrue(2<=randomGenerator.getNextInt(2,4) && randomGenerator.getNextInt(2,4)<4);
  }

  @Test
  public void shuffleList() {
    List<Equipment> equipmentList = new ArrayList<>();
    equipmentList.add(new HeadGear("HeadgearA", randomGenerator));
    equipmentList.add(new Footwear("FootwearA", randomGenerator));
    equipmentList.add(new Belt("BeltA", randomGenerator));
    equipmentList.add(new HeadGear("PotionA", randomGenerator));
    equipmentList.add(new HeadGear("HeadgearB", randomGenerator));
    equipmentList.add(new Footwear("FootwearB", randomGenerator));
    equipmentList.add(new Belt("BeltB", randomGenerator));
    equipmentList.add(new HeadGear("PotionB", randomGenerator));
    assertNotEquals(equipmentList,randomGenerator.shuffleList(equipmentList));
  }
}