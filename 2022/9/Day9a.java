import java.io.FileInputStream;
import java.util.Scanner;

public class Day9a{

  public static void main(String[] args) {
    world w = new world();
    try{
      FileInputStream fis = new FileInputStream("input.txt");
      Scanner scanner = new Scanner(fis);

      while(scanner.hasNextLine())
      {
        String dir = scanner.next();
        int steps = scanner.nextInt();
        
        switch (dir.charAt(0)){
          case 'D':
            w.moveDown(steps);
            break;
          case 'U':
            w.moveUp(steps);
            break;
          case 'L':
            w.moveLeft(steps);
            break;
          case 'R':
            w.moveRight(steps);
            break;
        }

      }

      System.out.println(w.visitedCount());

      scanner.close();
    }catch(Exception e)
    {
      e.printStackTrace();
    }
  }
}