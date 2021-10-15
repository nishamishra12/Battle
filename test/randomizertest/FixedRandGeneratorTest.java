package randomizertest;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import equipment.Belt;
import equipment.Equipment;
import equipment.Footwear;
import equipment.HeadGear;
import randomizer.FixedRandGenerator;
import randomizer.RandomGenerator;

import static org.junit.Assert.*;

public class FixedRandGeneratorTest {

  RandomGenerator fixedRandGenerator;

  @Before
  public void setUp() throws Exception {
    fixedRandGenerator =  new FixedRandGenerator(2);
  }

  @Test
  public void getNextInt() {
    assertEquals(2, fixedRandGenerator.getNextInt(2,4));
  }

  @Test
  public void shuffleList() {
    List<Equipment> equipmentList = new ArrayList<>();
    equipmentList.add(new HeadGear("HeadgearA", fixedRandGenerator));
    equipmentList.add(new Footwear("FootwearA", fixedRandGenerator));
    equipmentList.add(new Belt("BeltA", fixedRandGenerator));
    equipmentList.add(new HeadGear("PotionA", fixedRandGenerator));
    equipmentList.add(new HeadGear("HeadgearB", fixedRandGenerator));
    equipmentList.add(new Footwear("FootwearB", fixedRandGenerator));
    equipmentList.add(new Belt("BeltB", fixedRandGenerator));
    equipmentList.add(new HeadGear("PotionB", fixedRandGenerator));
    assertEquals(equipmentList,fixedRandGenerator.shuffleList(equipmentList));
  }
}