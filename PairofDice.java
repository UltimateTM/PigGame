public class PairofDice {
  public int roll1;
  public int roll2;
  Die die = new Die();

  public int PairRoll() {
    Die die1 = new Die(6);
    Die die2 = new Die(6);
    roll1 = die1.roll();
    roll2 = die2.roll();
    return roll1 + roll2;
  }
}