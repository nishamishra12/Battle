package weapon;

import randomizer.RandomGenerator;

public class TwoHandedSword extends Sword {

  public TwoHandedSword(RandomGenerator randomGenerator) {
    super(randomGenerator.getNextInt(6,10));
  }
}
