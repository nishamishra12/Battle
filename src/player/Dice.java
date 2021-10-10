package player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Dice {

  private final Random RANDOM = new Random();

  private int rollMyDice() {
    int number = 1 + RANDOM.nextInt(6);
    if (number == 1) {
      rollMyDice();
    }
    return number;
  }

  public int rollMyDiceFourTimes() {
    List<Integer> a = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      a.add(rollMyDice());
    }
    Collections.sort(a);
    return a.get(3) + a.get(2) + a.get(1);
  }
}
