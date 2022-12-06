import java.io.FileInputStream;
import java.util.Scanner;

public class Day4a{
  

  public static void main(String[] args) {
    try{

      FileInputStream fis = new FileInputStream("input.txt");
      Scanner scanner = new Scanner(fis);

      int tot =0;

      while(scanner.hasNextLine())
      {

        String input = scanner.nextLine();
        input = input.replaceAll("[^0-9]", " ");
        String[] numbers = input.split(" ");
        

        int[] bounds = new int[4];

        for(int i = 0; i < 4; i++)
        {
          bounds[i] = Integer.parseInt(numbers[i]);
        }

        if((bounds[0] <= bounds[2] && bounds[1] >= bounds[3]) || (bounds[2] <= bounds[0] && bounds[3] >= bounds[1]))
        {
          tot++;
        }

        
      }
      System.out.println(tot);
    }

    catch(Exception e)
    {
      e.printStackTrace();
    }

  }
}