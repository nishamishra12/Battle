package equipment;

/**
 * This is an enumeration for size of the belt.
 */
public enum BeltSize {

  SMALL(1),
  MEDIUM(2),
  LARGE(4);

  private final int sizeValue;

  BeltSize(int sizeValue) {
    this.sizeValue = sizeValue;
  }

  public int getSizeValue() {
    return sizeValue;
  }
}
