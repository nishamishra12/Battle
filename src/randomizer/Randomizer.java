package randomizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import equipment.Equipment;

/**
 * This class represents the random generator class which implements all the methods
 * of the interface and is used to calculate random numbers.
 */
public class Randomizer implements RandomGenerator {

  /**
   * {@inheritDoc}
   */
  @Override
  public int getNextInt(int min, int max) {
    Random rn = new Random();
    return rn.nextInt(max - min) + min;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Equipment> shuffleList(List<Equipment> arraylist) {
    List<Equipment> equipmentList = new ArrayList<>(arraylist);
    Collections.shuffle(equipmentList);
    return equipmentList;
  }
}
