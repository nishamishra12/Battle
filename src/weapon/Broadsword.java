package weapon;

import randomizer.RandomGenerator;

public class Broadsword extends Sword {

  public Broadsword(RandomGenerator randomGenerator) {
    super(randomGenerator.getNextInt(6,10));
  }

}
