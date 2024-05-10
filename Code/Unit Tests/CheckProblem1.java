// FREEZE CODE BEGIN

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static org.junit.Assert.*;
import java.lang.reflect.Modifier;
import org.junit.Test;
import java.awt.*;
import GameLogic.Car;

public class CheckProblem1 {

  // 1 - Car class, Constructor and Getters
  @Test
  public void check_constructorAndGetters_Car() {
    Boolean flag = false;
    StringBuilder output = new StringBuilder();

    Car car = new Car(new Point(10,20),
                    Color.red, 20,30,10,true);

    //Well use reflection to break encapsulation
    if(!car.getCurrentPosition().equals(new Point(10,20))){
        output.append("Car current position should be 10, 20 as initialized in the constructor");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    if(!car.getBodyColour().equals(Color.red)){
        output.append("The car's colour should be red");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    if(car.getMovementSpeed() != 20){
        output.append("The cars movement speed should be 20 as initialized in the constructor");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    if(car.getXSize() != 30 || car.getYSize() != 10){
        output.append("The car's size is incorrect");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    if(!car.isMoveLeft()){
        output.append("The car created above should move from let for right");
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    flag = true;


    assertTrue(output.toString(), flag);
  }



  // 1 - Car class, move method
  @Test
  public void check_move_Car() {
    Boolean flag = false;
    StringBuilder output = new StringBuilder();

    Point startPoint = new Point(0,10);
    Car car = new Car(startPoint,
            Color.GREEN, 20,30,10,true);

    //We'll call move and see how much the car moved

    Point expectedPosition = new Point(-20,10);
    car.move();
    if(!expectedPosition.equals(car.getCurrentPosition())){
        output.append(String.format("Your move method is incorrect.\nExpected position after move: %s\n" +
                "Current position after move: %s\n", expectedPosition,car.getCurrentPosition()));
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    //Another car, this one moves from left to right
    startPoint = new Point(15,12);
    car = new Car(startPoint,
            Color.GREEN, 40,30,10,false);

    //We'll call move and see how much the car moved

    expectedPosition = new Point(55,12);
    car.move();
    if(!expectedPosition.equals(car.getCurrentPosition())){
        output.append(String.format("Your move method is incorrect.\nExpected position after move: %s\n" +
                "Current position after move: %s\n", expectedPosition,car.getCurrentPosition()));
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    flag = true;

    assertTrue(output.toString(), flag);
  }


}

// FREEZE CODE END