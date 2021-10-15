package weapon;

import randomizer.RandomGenerator;
import randomizer.Randomizer;

public class Kantana extends Sword {

  public Kantana(RandomGenerator randomGenerator) {
    super(2*randomGenerator.getNextInt(6,10));
  }
}
