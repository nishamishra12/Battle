package weapon;

import randomizer.RandomGenerator;
import randomizer.Randomizer;

public class Katana extends Sword {

  public Katana(RandomGenerator randomGenerator) {
    super(2*randomGenerator.getNextInt(6,10));
  }
}
