package equipment;

/**
 * An abstract class that contains code that is shared by all types
 * of equipments.
 */
abstract class EquipmentAbstract implements Equipment {

  protected int compareToHeadgear(Equipment s) {
    return -1;
  }

  protected int compareToPotion(Equipment s) {
    return -1;
  }

  protected int compareToBelt(Equipment s) {
    return -1;
  }

  protected int compareToFootwear(Equipment s) {
    return -1;
  }
}
