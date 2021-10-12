package driver;

import arena.Arena;
import arena.BattleArena;

public class Driver {

 Arena arena;
  public static void main (String[] args) {

    System.out.println("****** Starting Game ******"+'\n'+"****** Creating Battle Arena ******");
    new BattleArena("Nisha", "Varun");

  }
}
