//************************************************************
// Pig.java    Author:  Gabriel Limberg
//
// Contains most of the methods and variables used in the Main class
//************************************************************

import java.util.Scanner;

public class Pig {
  private final int MAX_SCORE = 100;
  private static int playerScore = 0;
  private static int computerScore = 0;
  private int localplayerScore;               // variabes used in the Pig.java class
  private int localcomputerScore;
  private String input;

  private boolean playerTurn;            // determines the turn of each player
  private boolean computerTurn;

  Scanner scan = new Scanner(System.in);
  Die die = new Die();                 // creates the die object and scanner object
  PairofDice pair = new PairofDice();

  public void play() { // play method for use in the main class
    boolean playControl = true; // keeps the user in the loop

    while (playControl) { // loop to keep the player in the game, controled by the scoreCheck, and turnIndicator methods
      
      if (playerTurn == true) {
        playerPlay();
        scoreCheck();
      } else if (computerTurn == true) {
        computerPlay();
        scoreCheck();
      }

    }   

  }

  public void playerPlay() {
    boolean isNotOne = true;

    DisplayScore(); 
    System.out.println("Enter [any] key to roll your die.");
    scan.next(); // prompts user for input
    Main.ClearConsole(); // calls on a method from the main class
    do {
      DisplayScore();
      pair.PairRoll();
      localplayerScore += (pair.PairRoll()); // adds to the round score

      System.out.println("Round score: " + localplayerScore); // diplay of round score
      System.out.println("You have rolled a [" + pair.roll1 + "] and a [" + pair.roll2 + "]\n"); // displays the output of the dies

      if (pair.roll1 == 1 && pair.roll2 == 1) { // determines if a die roll are both equal to one, ends player turn
        System.out.println("You have rolled a consecutive 1! \nYour score is now reset.\n Enter [any] key to continue.");
        scan.next();
        playerScore = 0;
        localplayerScore = 0;
        isNotOne = false;
        playerTurn = false;
        computerTurn = true;
      } else if (pair.roll1 == 1 || pair.roll2 == 1) { // determines if either die is a one, sets the round score equal to zero and ends turn
        System.out.println("One of your dices has rolled a 1! \nEnter [any] key to continue.");
        scan.next();
        localplayerScore = 0;
        isNotOne = false;
        playerTurn = false;
        computerTurn = true;
      }

      if (isNotOne == true) { // evaluates if the If-else statement ran above in 50-64
        turnIndicator();
      }
      Main.ClearConsole();
    } while (playerTurn == true); 
    
  }

  public void computerPlay() {
    boolean isNotOne = true;
    do {
      pair.PairRoll();
      localcomputerScore += (pair.PairRoll());

      if (pair.roll1 == 1 && pair.roll2 == 1) {
        localcomputerScore= 0;
        computerScore = 0;
        System.out.println("The computer has rolled consecutive ones! \n");

        isNotOne = false;
        playerTurn = true;
        computerTurn = false;
      } else if (pair.roll1 == 1 || pair.roll2 == 1) {
        localcomputerScore = 0;
        System.out.println("The computer has rolled a one! \n");

        isNotOne = false;
        playerTurn = true;
        computerTurn = false;
      }

      if (localcomputerScore >= 20 && isNotOne) { // if the score in the round is greater than or equal to 20 and the if-else statement above was not ran, then this is run
        computerScore += localcomputerScore;

        DisplayScore();
  
        System.out.println("The computer has rolled a " + localcomputerScore + "\n");
        localcomputerScore = 0;
      
        System.out.println("Press [any] key to go into your turn.");
        scan.next();
        computerTurn = false;
        playerTurn = true;
        Main.ClearConsole();
      }
    } while (computerTurn == true);
  }

  public void scoreCheck() { // checks the score each time a player ends their turn
    boolean isValid = true;

    if (playerScore >= MAX_SCORE) { // player wins
      DisplayScore();
      System.out.println("Congratulations! You win! Enter [Y] to play again or [N] to exit.");
      input = scan.next();

      while (isValid){
        if (input.equalsIgnoreCase("y")) {
          isValid = false;
          playerScore = 0;
          computerScore = 0;
          Main.ClearConsole();
        } else if (input.equalsIgnoreCase("n")){
          System.exit(0);
        }
      }
    } else if (computerScore >= MAX_SCORE) { // computer wins
      System.out.println("You lost by " + (computerScore - playerScore) + " points. Enter [Y] to play again or [N] to exit.");
      input = scan.next();
      while (isValid) {
        if (input.equalsIgnoreCase("y")) {
          isValid = false;
          Main.ClearConsole();
          playerScore = 0;
          computerScore = 0;
        } else if (input.equalsIgnoreCase("n")){
          System.exit(0);
        }
      }
    }
  }

  public void turnIndicator() { // determines if the player wants to end their turn
    boolean isValid = true;

    System.out.println("Enter [Y] to end your turn or [N] to roll again. \nIf you would like to exit, enter [Q]");
    input = scan.next();
    while (isValid){
      if (input.equalsIgnoreCase("y")) {
        isValid = false;
        playerTurn = false;
        computerTurn = true;
        playerScore += localplayerScore; // score from the localplayerScore is added to the playerScore variable after the player finishes their turn
        localplayerScore = 0;
      } else if (input.equalsIgnoreCase("n")) {
        isValid = false;
        playerTurn = true;
        computerTurn = false;
      } else if (input.equalsIgnoreCase("q")) {
        isValid = false;
        Main.ClearConsole(); 
        System.out.println("Thank you for playing!");
        System.exit(0);      // closes the program
      } else {
        System.out.println("Please input either [Y] or [N]");
        input = scan.next(); // catches any non-validated characters
      }
    } 
  }

  public void DisplayScore() { // displays score
    System.out.println("Player score: " + playerScore + "\t \t Computer score: " + computerScore + "\n");
  }

  public boolean getPlayerTurn(boolean playerTurn) { // getter for access outside the class
    this.playerTurn = playerTurn;
    return playerTurn;
  }

  public boolean getComputerTurn(boolean computerTurn) { // getter for access outside the class
    this.computerTurn = computerTurn;
    return computerTurn;
  }

}