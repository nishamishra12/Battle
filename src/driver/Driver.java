package driver;

import arena.BattleArena;
import randomizer.FixedRandGenerator;
import randomizer.RandomGenerator;
import randomizer.Randomizer;

public class Driver {

  public static void main (String[] args) {
//    RandomGenerator randomGenerator =  new Randomizer();
    RandomGenerator randomGenerator =  new FixedRandGenerator(1,2,2,1,2);

    System.out.println("****** Starting Game ******");
    BattleArena battleArena = new BattleArena("Nisha", "Varun",randomGenerator);
    battleArena.equipPlayerWithGear();
    battleArena.requestWeapon();
    System.out.println(battleArena.getPlayerDescription());
    battleArena.startBattle();

  }
}
