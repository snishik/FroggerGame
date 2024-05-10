/*
* Nishika Solanki | B00953260
* CSCI 1100
* Assignment 3 | October 29, 2023
* This Frog class is about a player who is a frog in the game,
* and has player's position along with a method that moves the player in the X and/or Y direction .
*/

package GameLogic;
import java.awt.*;

public class Frog {

  //declaration of instance variables for the Frog class
  private String name;
  private Point currentPosition;


  /**
  * Constructor Frog(String, Point)
  * Creates a new frog object given a name and starting position
  *
  * @param name stores the name of the Frog player
  * @param currentPosition stores the current position of the Frog object as it moves through the game
  */
  public Frog(String name, Point currentPosition){
    this.name = name;
    this.currentPosition = currentPosition;
  }

  /**
  * Constructor Frog(String)
  * Creates a new frog object given a name and at position (0,0)
  * @param name stores the name of the player
  */
  public Frog(String name){
    this.name = name;
    this.currentPosition = new Point(0,0); 
  }

  /**
  * getCurrentPosition() -> Point
  * Returns the current position of the frog player in the game
  * @return A Point returning the current position of the frog object
  */
  public Point getCurrentPosition(){
    return currentPosition;
  }

  /**
  * getName()-> String
  * get method for the name of the player(Frog)
  * @return a String representing the name of the player
  */
   public String getName(){
    return name;
  }

  /**
  * move(int, int) -> Point
  * translates the frog position given an x and y coordinates, respectively
  * 
  * @param xCoord tells how much the frog has to go in the horizontal direction from its currentPosition
  * @param yCoord tells how much the frog has to go in the vertical direction from its currentPosition
  * @return Point denoting the currentPosition(x,y) of the player after it moves
  */
  public Point move(int xCoord, int yCoord){
    currentPosition.translate(xCoord,yCoord);
    return currentPosition;
  }
  
  /**
  * distanceFromPoint(Point) -> double
  * computes the distance between the frog object and the point received as an argument
  * 
  * @param distancePoint tells the point from where the distance needs to be calculated
  * @return double denoting the distance between the frog object and the point.
  */
  public double distanceFromPoint(Point distancePoint){
    return currentPosition.distance(distancePoint);
  }


  /**
  * Returns the bounds of the car object which can be used to detect collision
  * @return A Rectangle object defining the bounds of the frog
  */
  public Rectangle getCollisionBounds(){
    return new Rectangle(currentPosition.x,currentPosition.y,30,30);
  }

  /**
  * toString()-> String
  * @return the String representation of the statement to be printed for the player(Frog object)
  */
  public String toString(){
    return "The frog named " + name + " is at (" + (currentPosition.x) + ", " + (currentPosition.y) + ")";
  }


}
