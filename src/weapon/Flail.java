package weapon;

import randomizer.Randomizer;

public class Flail implements Weapon {

  private final int damage;

  public Flail() {
    this.damage = new Randomizer(6,10).getRandomValue();
  }

  @Override
  public int getDamage() {
    return this.damage;
  }
}
