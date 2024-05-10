// FREEZE CODE BEGIN

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static org.junit.Assert.*;
import java.lang.reflect.Modifier;
import org.junit.Test;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import GameLogic.Car;
import GameLogic.Game;

public class CheckProblem3 {

  // 3 - Game class, constructors and getters
  @Test
  public void check_constructorAndGetters_Game() {
    Boolean flag = false;
    StringBuilder output = new StringBuilder();

    Game game = new Game("Charlie", new Point(300,500));

    ArrayList<Car> cars = game.getCars();
    if(cars.size() != 4){
        output.append("The constructor should have added 4 cars to the cars list");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    if(!game.getPlayer().getName().equals("Charlie")){
        output.append("The frog name should be Charlie");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    if(!game.getPlayer().getCurrentPosition().equals(new Point(300,500))){
        output.append("Charlie should be at 300,500");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }
    flag = true;

    assertTrue(output.toString(), flag);
  }

  // 3 - Game class, moveCars method
  @Test
  public void check_moveCars_Game() {
    Boolean flag = false;
    StringBuilder output = new StringBuilder();

    Game game = new Game("Charlie", new Point(300,500));

    //OK, I'll cheat a bit here.
    // First I'll move two cars to the edges so when they move we can test the "wrap around" functionality

    int rightEdge = 600;

    ArrayList<Car> cars = game.getCars();
    //make sure you added the cars in the same order as described in the document
    cars.get(0).getCurrentPosition().x = 595;
    cars.get(3).getCurrentPosition().x = 5;

    //now lets call move.
    //here is what we expect: cars  1 and 2 should move without hitting and edge and wrapping
    //Cars 0 and 3 should hit the right edge and left edge respectively.
    //The move method will then move cars 0 and 3 to the other side of the map

    game.moveCars(rightEdge);
    if(cars.get(0).getCurrentPosition().x != 0){
        output.append("Car zero went past the right edge, " +
                "the method must place it at coordinate 0 (left edge)");
        output.append("Car zero coordinate: " + cars.get(0).getCurrentPosition());
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    if(cars.get(1).getCurrentPosition().x != 30){
        output.append("Car one has a speed of 30 and was initially at 0,300" +
                "The car should have moved to 30,300");
        output.append("Car one coordinate: " + cars.get(1).getCurrentPosition());
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    if(cars.get(2).getCurrentPosition().x != 90){
        output.append("Car two has a speed of 10 and was initially at 100,150" +
                "The car should have moved to 90,150 as it moves left");
        output.append("Car two coordinate: " + cars.get(2).getCurrentPosition());
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }
    if(cars.get(3).getCurrentPosition().x != 600){
        output.append("Car three went past the left edge (it moves left), " +
                "the method must place it at coordinate 600 (right edge)");
        output.append("Car zero coordinate: " + cars.get(3).getCurrentPosition());
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    flag = true;

    assertTrue(output.toString(), flag);
  }

  // 3 - Game class, movePlayer method
  @Test
  public void check_movePlayer_Game() {
    Boolean flag = false;
    StringBuilder output = new StringBuilder();

    Game game = new Game("Charlie", new Point(300,500));

    //Lets add a combo of movements to Charlie and test it.
    //REMEMBER: the origin of the coordinates is at top left.
    // If we want to go up (arrow up) we should decrease the value of y

    int stepSize = 10;
    game.movePlayer(KeyEvent.VK_UP,stepSize);
    if(!game.getPlayer().getCurrentPosition().equals(new Point(300,490))){
        output.append("We move the player up, we should have decreased the y coordinate");
        output.append("Expected: (300,490)");
        output.append("Actual: " + game.getPlayer().getCurrentPosition());
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    game.movePlayer(KeyEvent.VK_RIGHT,30);
    if(!game.getPlayer().getCurrentPosition().equals(new Point(330,490))){
        output.append("We move the player right, we should have increased the x coordinate");
        output.append("Expected: (330,490)");
        output.append("Actual: " + game.getPlayer().getCurrentPosition());
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    game.movePlayer(KeyEvent.VK_DOWN,10);
    if(!game.getPlayer().getCurrentPosition().equals(new Point(330,500))){
        output.append("We move the player Down, we should have increased the y coordinate");
        output.append("Expected: (330,500)");
        output.append("Actual: " + game.getPlayer().getCurrentPosition());
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    game.movePlayer(KeyEvent.VK_LEFT,100);
    if(!game.getPlayer().getCurrentPosition().equals(new Point(230,500))){
        output.append("We move the player Left, we should have decreased the x coordinate");
        output.append("Expected: (230,500)");
        output.append("Actual: " + game.getPlayer().getCurrentPosition());
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    flag = true;

    assertTrue(output.toString(), flag);
  }

  // 3 - Game class, playerHit method
  @Test
  public void check_playerHit_Game() {
    Boolean flag = false;
    StringBuilder output = new StringBuilder();

    Game game = new Game("Charlie", new Point(300,500));

    if(game.isPlayerHit()){
        output.append("Player is safe at the start position, it should not be hit");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    //I'll move the player into a car :)

    Point playerPos = game.getPlayer().getCurrentPosition();
    playerPos.setLocation(game.getCars().get(0).getCurrentPosition());

    if(!game.isPlayerHit()){
        output.append("Player is inside a car (ran over), it should be hit");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }
    flag = true;


    assertTrue(output.toString(), flag);
  }

  // 3 - Game class, playerWon method
  @Test
  public void check_playerWon_Game() {
    Boolean flag = false;
    StringBuilder output = new StringBuilder();

    Game game = new Game("Charlie", new Point(300,500));

    if(game.hasPlayerWon()){
        output.append("Player is at the start position it should not have won");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    //I'll move the player into the win area

    Point playerPos = game.getPlayer().getCurrentPosition();
    playerPos.setLocation(new Point(300,10));

    if(!game.hasPlayerWon()){
        output.append("Player is inside the win are, it shuold have won the game");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }
    flag = true;

    assertTrue(output.toString(), flag);
  }

}

// FREEZE CODE END