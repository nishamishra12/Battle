package weapon;

import randomizer.Randomizer;

public class TwoHandedSword extends Sword {

  public TwoHandedSword() {
    super(new Randomizer(6,10).getRandomValue());
  }
}
