package weapon;

import randomizer.Randomizer;

public class Kantana extends Sword {

  public Kantana() {
    super(new Randomizer(6,10).getRandomValue());
  }
}
