package weapon;

import randomizer.Randomizer;

abstract class Sword implements Weapon {

  private final int damage;

  public Sword(int damage) {
    this.damage = damage;
  }

  @Override
  public int getDamage() {
    return this.damage;
  }
}
