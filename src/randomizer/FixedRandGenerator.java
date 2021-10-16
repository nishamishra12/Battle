package randomizer;

import java.util.ArrayList;
import java.util.List;

import equipment.Equipment;

/**
 * This class represents the fixed random generator class which implements all the methods
 * of the interface and is used to calculate fixed random numbers.
 */
public class FixedRandGenerator implements RandomGenerator {

  private int[] arr;
  private int index;

  /**
   * Constructs a fixed random generator by taking varargs as the input.
   *
   * @param arr this parameter takes varargs as integers
   */
  public FixedRandGenerator(int... arr) {
    this.arr = arr;
    this.index = 0;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getNextInt(int min, int max) {
    int val = arr[index++];
    index = index >= arr.length ? 0 : index;
    return val;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Equipment> shuffleList(List<Equipment> arraylist) {
    List<Equipment> equipmentList = new ArrayList<>(arraylist);
    return equipmentList;
  }
}
