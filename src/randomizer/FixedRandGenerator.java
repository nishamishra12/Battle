package randomizer;

import java.util.Collections;
import java.util.List;

import equipment.Equipment;

public class FixedRandGenerator implements  RandomGenerator {

  private int[] arr;
  private int index;

  public FixedRandGenerator(int... arr) {
    this.arr = arr;
    this.index = 0;
  }

  @Override
  public int getNextInt(int min, int max) {
    int val = arr[index++];
    index = index >= arr.length ? 0 : index;
    return val;
  }

  @Override
  public List<Equipment> shuffleList(List<Equipment> arraylist){
    return arraylist;
  }
}
