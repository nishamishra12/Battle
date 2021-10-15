package driver;

import java.util.Scanner;

import arena.Arena;
import arena.BattleArena;
import randomizer.FixedRandGenerator;
import randomizer.RandomGenerator;
import randomizer.Randomizer;

public class Driver {

  public static void main(String[] args) {
    RandomGenerator randomGenerator = new Randomizer();

    System.out.println("****** Welcome to Jumptastic Games!!! ******"); //point 1

    Arena battleArena = new BattleArena("Nick", "Rick", randomGenerator);
    System.out.println("Player 1 created" + '\n' + "Player 2 created");
//
//    battleArena.equipPlayerWithGear();
//    battleArena.requestWeapon();
    System.out.println(battleArena.getPlayerDescription());
    System.out.println(battleArena.startBattle());
    System.out.println("Do you want to reset game?");
    Scanner sc = new Scanner(System.in);
    String val = sc.nextLine();
    while (!val.equalsIgnoreCase("N")) {
      System.out.println(battleArena.getPlayerDescription());
      System.out.println(battleArena.startBattle());
      System.out.println("Do you want to reset game?");
      val = sc.nextLine();
    }
  }
}
