//************************************************************
// PigGame.java    Author:  Gabriel Limberg
//
// A game in which a player and computer roll two dies each until either reaches a score // of 100
//************************************************************

import java.util.Scanner;

class Main {
  
  public static void main(String[] args) {
    String input;
    boolean isValid = true; // input is used for the scanner and isValid used to control the while-loop

    Scanner scan = new Scanner(System.in);
    Pig pig = new Pig();                        // creates scanner and pig object

    ClearConsole();
    System.out.println("Welcome to the game of Pig! \n");
    System.out.println("Hit [Enter] to continue."); // line introduction
    scan.nextLine();
    ClearConsole();

    System.out.println("Whichever player reaches a score of 100 points wins.\n");
    System.out.println("You can roll multiple times per turn, but rolling a 1 means you lose all your points in that round and you lose your turn. \n" );
    System.out.println("Rolling consecutive 1's sets your overall score to zero and you lose your turn.\n"); // second introduction
    System.out.println("Would you like to play? [Y] or [N]");
    input = scan.next();        // prompts for user input
    ClearConsole();
    while (isValid){
      if (input.equalsIgnoreCase("y")) {
        isValid = false;
        pig.getPlayerTurn(true);  // getter to set the private boolean playerTurn in Pig.java to true
        pig.getComputerTurn(false); // getter to set the private boolean computerTurn in Pig.java to false
      } else if (input.equalsIgnoreCase("n")) {
        System.exit(0);
      } else {
        System.out.println("Please input either [Y] or [N]");
        input = scan.next();
        ClearConsole();
      }
    }

    pig.play(); // calls on the method in the Pig.java class

  }

  public static void ClearConsole() { // clears the console
    System.out.print("\033[H\033[2J");  
    System.out.flush();  
  }
}