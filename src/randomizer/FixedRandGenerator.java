package randomizer;

public class FixedRandGenerator implements  RandomGenerator {

  int[] arr;
  int index;
  public FixedRandGenerator(int... arr) {
    this.arr = arr;
    this.index = 0;
  }

  @Override
  public int getNextInt() {
    int val = arr[index++];
    index = index >= arr.length ? 0 : index;
    return val;
  }

  @Override
  public int getNextInt(int min, int max) {
    int f = min + index;
    return f;
  }
}