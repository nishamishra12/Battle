package equipment;

import java.util.ArrayList;
import java.util.List;

import player.Ability;
import randomizer.RandomGenerator;

/**
 * This class represents the Equipment type footwear and extends the abstract class.
 */
public class Footwear extends EquipmentAbstract {

  private final String name;
  private final List<Ability> effectAbility = new ArrayList<>();
  private int effectValue;
  private int move;

  /**
   * Constructs an object of Footwear class with the name of the footwear, and the random generator.
   *
   * @param name this parameter takes the name of the equipment footwear
   * @param randomGenerator this parameter takes the random generator
   * @throws IllegalArgumentException when name or random generator passed is null
   */
  public Footwear(String name, RandomGenerator randomGenerator) throws IllegalArgumentException {

    if (name == null) {
      throw new IllegalArgumentException("Footwear name cannot be null");
    }
    if (randomGenerator == null) {
      throw new IllegalArgumentException("Randomizer cannot be null");
    }
    this.name = name;
    this.effectValue = randomGenerator.getNextInt(1,4);
    this.effectAbility.add(Ability.DEXTERITY);
    this.move = randomGenerator.getNextInt(5,10);
  }

  /**
   * This method compares the object of the equipment with the abstract class object.
   *
   * @param s this parameter takes the object of the equipment
   * @return an integer depending on the result of the comparison
   */
  @Override
  public int compareTo(Equipment s) {
    if (s instanceof EquipmentAbstract) {
      EquipmentAbstract abstractGear = (EquipmentAbstract) s;
      return abstractGear.compareToFootwear(this);
    }
    return -1;
  }

  @Override
  protected int compareToBelt(Equipment s) {
    return -1;
  }

  @Override
  protected int compareToFootwear(Equipment s) {
    return s.getName().compareTo(this.getName());
  }

  @Override
  protected int compareToHeadgear(Equipment s) {
    return -1;
  }

  @Override
  protected int compareToPotion(Equipment s) {
    return -1;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getEffectValue() {
    return this.effectValue;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Ability> getEffectAbility() {
    List<Ability> effectAbilityCopy = new ArrayList<>(this.effectAbility);
    return effectAbilityCopy;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getBeltSize() {
    return -1;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEffectValueNegative() {
    this.effectValue = -1 * this.effectValue;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EquipmentType getEquipmentType() {
    return EquipmentType.FOOTWEAR;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getMove() {
    return this.move;
  }
}
