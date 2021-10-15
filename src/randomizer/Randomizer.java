package randomizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import equipment.Equipment;

public class Randomizer implements RandomGenerator{

  @Override
  public int getNextInt(int min, int max) {
    Random rn = new Random();
    return rn.nextInt(max-min)+min;
  }

  @Override
  public List<Equipment> shuffleList(List<Equipment> arraylist){
    List<Equipment> equipmentList = new ArrayList<>(arraylist);
    Collections.shuffle(equipmentList);
    return equipmentList;
  }
}
