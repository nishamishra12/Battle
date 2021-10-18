package randomizer;

import java.util.List;

import equipment.Equipment;

/**
 * This interface represents generation of actual and fixed random numbers.
 */
public interface RandomGenerator {

  /**
   * This method provides a random number between minimum and maximum values.
   *
   * @param min this parameter takes the minimum value
   * @param max this parameter takes the maximum value
   * @return an integer between minimum and maximum value
   */
  public int getNextInt(int min, int max);

  /**
   * This method provides a shuffled list of equipment based on whether the random or fixed
   * randomizer class is called.
   *
   * @param arraylist this parameter takes the not shuffled array list
   * @return the shuffled list of equipments
   */
  public List<Equipment> shuffleList(List<Equipment> arraylist);
}
