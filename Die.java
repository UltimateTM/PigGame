//************************************************************
// Die.java    Author:  Gabriel Limberg
//
// Contains the Die objects for use in the pig class
//************************************************************

public class Die {
  private final int MIN_FACES = 4;
  private int numFaces;  // variables for the Die class

  public Die() { // constructor that creates the Die object with 6 faces
    numFaces = 6;
  }

  public Die (int faces) { // can create a die with custom amount of faces
    if (faces < MIN_FACES) {
      numFaces = 6;
    } else {
      numFaces = faces;
    }
  }

  public int roll() { // roll method that returns a random number between 1-6
    return (int) (Math.random() * numFaces + 1);
  }
}