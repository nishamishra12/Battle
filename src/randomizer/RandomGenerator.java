package randomizer;

import java.util.List;

import equipment.Equipment;

public interface RandomGenerator {

 public int getNextInt();
 public int getNextInt(int min, int max);
 public  List<Equipment> shuffleList(List<Equipment> arraylist);
}
