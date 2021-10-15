package randomizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import equipment.Equipment;

public class Randomizer implements RandomGenerator{
//
//  private final Random RANDOM = new Random();
//  private int randomValue;
//
//  public Randomizer(int low, int high) {
//     this.randomValue =  low + RANDOM.nextInt(high-low);
//  }
//
//  public Randomizer() {
//  }
//
//  private int rollMyDice() {
//    int number = 1 + RANDOM.nextInt(5);
//    if (number == 1) {
//      rollMyDice();
//    }
//    return number;
//  }
//
//  public int rollMyDiceFourTimes() {
//    List<Integer> a = new ArrayList<>();
//    for (int i = 0; i < 4; i++) {
//      a.add(rollMyDice());
//    }
//    Collections.sort(a);
//    return a.get(3) + a.get(2) + a.get(1);
//  }
//
//  public int getRandomValue() {
//    return this.randomValue;
//  }

  @Override
  public int getNextInt() {
    Random rn = new Random();
    return rn.nextInt(20);
  }

  @Override
  public int getNextInt(int min, int max) {
    Random rn = new Random();
    return rn.nextInt(max-min)+min;
  }

  @Override
  public List<Equipment> shuffleList(List<Equipment> arraylist){
    Collections.shuffle(arraylist);
    return arraylist;
  }
}
