import java.io.FileInputStream;
import java.util.Scanner;

public class CaloryCount{


  public static void main(String[] args) {
    try{
      FileInputStream fis = new FileInputStream("input.txt");
      Scanner scanner = new Scanner(fis);
      int curCum = 0;
      int[] max = new int[3];
      while(scanner.hasNext())
      {
        String in = scanner.nextLine();
        if(in.isEmpty())
        {
          int temp;
          for(int i = 0; i < 3; i++)
          {
            if(curCum > max[i])
            {
              temp = max[i];
              max[i] = curCum;
              curCum = temp;
            }
          }
          
            curCum = 0;
            
        }
        else{
          curCum += Integer.parseInt(in);
        }
      }

      System.out.println(max[0] + max[1] + max[2]);
    }catch(Exception e)
    {e.printStackTrace();}


  }
}