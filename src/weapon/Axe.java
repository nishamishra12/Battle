package weapon;

import java.util.Random;

import randomizer.Randomizer;

public class Axe implements Weapon {

  private final int damage;

  public Axe() {
    this.damage = new Randomizer(6,10).getRandomValue();
  }

  @Override
  public int getDamage() {
    return this.damage;
  }

}
