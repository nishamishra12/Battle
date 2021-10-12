package bag;

public enum EquipmentType {
  BELT("Belt"),
  FOOTWEAR("Footwear"),
  HEADGEAR("Headgear"),
  POTION("Potion");

  private final String equipmentName;

  EquipmentType(String equipmentName) {
    this.equipmentName = equipmentName;
  }

  public String equipmentName() {
    return equipmentName;
  }
}