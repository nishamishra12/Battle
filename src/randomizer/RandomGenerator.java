package randomizer;

import java.util.List;

import equipment.Equipment;

public interface RandomGenerator {

 int getNextInt(int min, int max);
 List<Equipment> shuffleList(List<Equipment> arraylist);
}
