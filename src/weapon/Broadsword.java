package weapon;

import randomizer.Randomizer;

public class Broadsword extends Sword {

  public Broadsword() {
    super(new Randomizer(6,10).getRandomValue());
  }

}
