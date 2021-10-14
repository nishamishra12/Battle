package driver;

import arena.BattleArena;

public class Driver {

  public static void main (String[] args) {

    System.out.println("****** Starting Game ******");
    BattleArena battleArena = new BattleArena("Nisha", "Varun");
    battleArena.equipPlayerWithGear();
    battleArena.requestWeapon();
    System.out.println(battleArena.getPlayerDescription());
//    battleArena.startBattle();

  }
}
