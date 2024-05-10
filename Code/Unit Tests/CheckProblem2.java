// FREEZE CODE BEGIN

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static org.junit.Assert.*;
import java.lang.reflect.Modifier;
import org.junit.Test;
import java.awt.*;
import GameLogic.Frog;

public class CheckProblem2 {

  // 2 - Frog class, move method
  @Test
  public void check_move_Frog() {
    Boolean flag = false;
    StringBuilder output = new StringBuilder();

    Point startPosition = new Point(10,25);
    Frog frog = new Frog("Charlie", startPosition);

    frog.move(23,-3);
    Point expectedPosition = new Point(33,22);
    if(!frog.getCurrentPosition().equals(expectedPosition)){
        output.append(String.format("Your move method is incorrect.\nExpected position after move: %s\n" +
                "Current position after move: %s\n", expectedPosition,frog.getCurrentPosition()));
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    flag = true;

    assertTrue(output.toString(), flag);
  }



  // 2 - Frog class, toString method
  @Test
  public void check_toString_Frog() {
    Boolean flag = false;
    StringBuilder output = new StringBuilder();

    Frog frog = new Frog("Arthur", new Point(0,10));

    String expected = "The frog named Arthur is at (0,10)";
    String result = frog.toString();

    //We remove the spaces and ignore in case to make testing easier
    if(!expected.replaceAll("\\s+","").equalsIgnoreCase(
            result.replaceAll("\\s+","")
    )){
        output.append("Your toString is incorrect");
        output.append("Expected: " + expected);
        output.append("Actual: " + result);
        flag = false;
        assertTrue(output.toString(), flag);
        return;
    }

    flag = true;

    assertTrue(output.toString(), flag);
  }

}

// FREEZE CODE END