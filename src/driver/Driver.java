package driver;

import java.util.Scanner;

import arena.Arena;
import arena.BattleArena;
import randomizer.RandomGenerator;
import randomizer.Randomizer;

/**
 * This a driver class which acts like a user input for the project.
 */
public class Driver {

  /**
   * This is a main class which will be used to start the driver class.
   *
   * @param args Args can be provided as any
   */
  public static void main(String[] args) {
    RandomGenerator randomGenerator = new Randomizer();
    Arena battleArena;

    System.out.println("****** Welcome to Jumptastic Games!!! ******"); //point 1

    try {
      //Players to enter arena with barehanded
      battleArena = new BattleArena("Nick", "Rick", randomGenerator);
      System.out.println("Player 1 created" + '\n' + "Player 2 created");
      //Equip players with Gears
      battleArena.equipPlayerWithGears();
      //Give Weapon to Players
      battleArena.requestWeaponForPlayer();
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
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }
}
