/*
* Nishika Solanki | B00953260
* CSCI 1100
* Assignment 3 | October 29, 2023
* This Game class models the overall logic of the game.This class will move the frog based on keyboard input events, 
* control the cars in the game and compute if the player won the game or was hit by a car.
*/

package GameLogic;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class Game {

  //declaration of instance variables for the Game class
  private Frog player;      // the frog object representing the player
  ArrayList <Car> cars;     // an ArrayList containing the cars in the game.


  /**
  * Constructor Game(String, Point)
  * Creates a new Frogger game, instantiates a new player given its name 
  * and starting position and add 4 cars to Cars ArrayList. 
  *
  * @param name representing the frog name
  * @param startingPoint represents the start point of the player
  */
  public Game(String name, Point startingPoint){
    this.player = new Frog(name, startingPoint); 
    cars = new ArrayList<>();
    cars.add(new Car(new Point(100,400),new Color(0,0,0),10,120,75,false));
    cars.add(new Car(new Point(0, 300), new Color(0,0,255), 30,200, 75, false));
    cars.add(new Car(new Point(100,150),new Color(0,0,0), 10, 120, 75, true));
    cars.add(new Car(new Point(0, 80),new Color(0,0,0), 20, 120, 75, true));
  }

  /**
  * getPlayer() -> Frog
  * Returns the frog player in the game
  * @return the player(name, starting point) in the game
  */
  public Frog getPlayer(){
    return this.player;
  }

  /**
  * getCars() -> ArrayList<Car>
  * Returns the car information in the game
  * @return the ArrayList of cars containing this information: currentPosition, bodyColour, movementSpeed, xSize, ySize, left in the game
  */
  public ArrayList<Car> getCars(){
    return this.cars;
  }

  /**
  * movePlayer(int, int) -> void
  * responds to keyboard events by moving the player int in the x or y direction.
  * 
  * @param event tells how much the player has to move in the x or y direction using the move method
  * @param pixels tells how much the player is going to move at each keypress
  */
  public void movePlayer(int event, int pixels){
    Point currPos = player.getCurrentPosition();
    if(event == KeyEvent.VK_UP ){
      player.move(0,-pixels);
    }

    if(event == KeyEvent.VK_DOWN ){
      player.move(0,pixels);
    }

    if(event == KeyEvent.VK_LEFT ){
      player.move(-pixels, 0);
    }

    if(event == KeyEvent.VK_RIGHT ){
      player.move(pixels, 0);
    }   
  }

  /**
  * moveCars(int) -> void
  * Moves every car in the game by calling Car class move method
  * 
  * @param edgePos an int representing the edge of the game area
  */
  public void moveCars(int edgePos){    
    for(int i = 0; i < cars.size(); i++){
      Car car = cars.get(i);
      Point currentPosition = car.getCurrentPosition();
      car.move();

      //If a car moves left to right goes past the int value(edgePos),the car moves back to zero
      if(!car.isMoveLeft() && currentPosition.x >= edgePos){
        car.getCurrentPosition().x = 0;
      }
      // If a car that moves right to left, reaches the 0 coordinate (left edge), the car moves back to the right edge.
      else if(currentPosition.x <= 0){
        car.getCurrentPosition().x = edgePos;
      }
    }
  }

  /**
  * isPlayerHit() -> boolean
  * calls the checkCarHit method to see if a player was hit by a car
  *
  * @returns  true if the player was hit by a car.
  */
  public boolean isPlayerHit(){

     for(int i = 0; i < cars.size(); i++){
        Car car = cars.get(i);
        if(checkCarHit(car,player))
          return true;
      }

      return false;
  }

  /**
  * checkCarHit(Car, Frog) -> boolean
  * responds to keyboard events by moving the player int in the x or y direction.
  * 
  * @param car uses Car's currentPosition to determine if a player was hit by a car
  * @param player uses Frog's currentPosition to determine if it hits a car
  * @returns true or false depending on whether the player hits the car or not.
  */
  public boolean checkCarHit(Car car,Frog player){
    Point carPosition = car.getCurrentPosition();
    Point playerPosition = player.getCurrentPosition();
    int carStartX = carPosition.x;
    int carStartY = carPosition.y;
    int carEndX = car.getXSize() + carStartX;
    int carEndY = car.getYSize() + carStartY;
    if(
      (playerPosition.x < carStartX || playerPosition.x > carEndX ) && 
      (playerPosition.y < carStartY || playerPosition.y > carEndY ) 
     ){
      return false;
    }
    return true; 
  }


  /**
  * hasPlayerWon() -> boolean
  * checks if the y coordinate of the frog is less than 20, then the player wins the game.
  *
  * @returns true if the player won the game.
  */
  public boolean hasPlayerWon(){
    Point currentPosition = player.getCurrentPosition();

    if(currentPosition.y <= 20){
      return true;
    } else{
      return false;
    }

  }
}
