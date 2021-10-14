package equipment;

public enum BeltSize {

  SMALL("SMALL", 1),
  MEDIUM("MEDIUM", 2),
  LARGE("LARGE", 4);

  private final String sizeName;
  private final int sizeValue;

  BeltSize(String sizeName, int sizeValue) {
    this.sizeName = sizeName;
    this.sizeValue = sizeValue;
  }

  public String getSizeName() {
    return sizeName;
  }

  public int getSizeValue() {
    return sizeValue;
  }
}
