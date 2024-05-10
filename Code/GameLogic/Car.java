/*
* Nishika Solanki | B00953260
* CSCI 1100
* Assignment 3 | October 29, 2023
* This Car class is about moving the cars horizontally on the game,
* along with storing object' current position, color and size.
*/

package GameLogic;
import java.awt.*;

public class Car {

  //declaration of instance variables for the Car class
  private Point currentPosition; 
  private Color bodyColour;      
  private int movementSpeed;     
  private int xSize;             
  private int ySize;             
  private boolean moveLeft;      


  /**
	 * Constructor Car(Point, Color, int, int, int, boolean)
	 * 
	 * @param currentP stores the current position of the Car object 
   * @param bodyColour the color of the car 
   * @param speed  how fast (in pixels) the cars move
   * @param xSize the size of the car in the x(horizontal) direction
   * @param ySize  the size of the car in the y direction
   * @param moveLeft by default cars move from right to left. 
	*/
  public Car(Point currentP, Color bodyColour, int speed, int xSize, int ySize, boolean moveLeft){
    this.currentPosition = currentP;
    this.bodyColour = bodyColour;
    this.movementSpeed = speed;
    this.xSize = xSize;
    this.ySize = ySize;
    this.moveLeft = moveLeft;
  }
  
  /**
  * getCurrentPosition() -> Point
  * Returns the current position of the Car object in the game
  * @return A Point defining the current position(X and Y coordinate) of the car
  */
  public Point getCurrentPosition(){
    return currentPosition;
  }
  
  /**
  * getBodyColour() -> Color
  * Returns the color of the car
  * @return A Color defining color of the car(rgb)
  */
  public Color getBodyColour(){
    return bodyColour;
  }

  /**
  * getXSize) -> int
  * get method for the size of the car 
  * @return an int representing the xSize of the car in the horizontal direction
  */
  public int getXSize(){
    return xSize;
  }

  /**
  * getYSize) -> int
  * get method for the size of the car 
  * @return an int representing the ySize of the car in the horizontal direction
  */
  public int getYSize(){
    return ySize;
  }
  
  /**
  * getMovementSpeed() -> int
  * get method for the speed of the car
  * @return an int representing how fast (in pixels) the cars move when someone calls the move method.
  */
  public int getMovementSpeed(){
    return movementSpeed;
  }

  /**
  * isMoveLeft() -> boolean
  * check if a car is moving right to left(true) or left to right(false)
  * @return true or false based on the input for moveLeft variable
  */
  
  public boolean isMoveLeft(){
    return moveLeft;
  }

  //moves the car in the X direction(either right to left or left to right) using movementSpeed as the main/step value.
  public void move(){
    if(isMoveLeft()){
      currentPosition.x = currentPosition.x - movementSpeed;
    } else{
      currentPosition.x = currentPosition.x + movementSpeed;          
    }
  }

  /**
  * Returns the bounds of the car object which can be used to detect collision
  * @return A Rectangle object defining the bounds of the car
  */
  public Rectangle getCollisionBounds(){
    return new Rectangle(currentPosition.x, currentPosition.y,xSize,ySize);
  } 
}
