package arena;

import player.BattlePlayer;
import player.Dice;
import player.Player;

public class BattleArena implements Arena {

  private Player player1;
  private Player player2;

  public BattleArena() {
    this.player1 = new BattlePlayer(new Dice().rollMyDiceFourTimes(), new Dice().rollMyDiceFourTimes(),
            new Dice().rollMyDiceFourTimes(), new Dice().rollMyDiceFourTimes());
    this.player2 = new BattlePlayer(new Dice().rollMyDiceFourTimes(), new Dice().rollMyDiceFourTimes(),
            new Dice().rollMyDiceFourTimes(), new Dice().rollMyDiceFourTimes());
    System.out.println("Player 1 - "+this.player1.getStrength() + this.player1.getConstitution()
            +this.player1.getDexterity() + this.player1.getCharisma());
    System.out.println("Player 2 - "+this.player2.getStrength() + this.player2.getConstitution()
            +this.player2.getDexterity() + this.player2.getCharisma());


  }
}
