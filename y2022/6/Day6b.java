import java.io.FileInputStream;
import java.util.Scanner;

public class Day6b{
  public static void main(String[] args) {
    try{
      FileInputStream fis = new FileInputStream("input.txt");
      Scanner scanner = new Scanner(fis);

      String input = scanner.next();

      char[] chars = input.toCharArray();

     
      int numChars = 0;

      for(int i=0; i<chars.length-14; i++)
      {
        boolean match = false;
        int matchIndex = -1;
        for(int comp1 = i + 13; comp1 >= i+1 && !match; comp1--)
        {
          for(int comp2 = comp1-1; comp2 >=i  && !match; comp2--)
          {
            System.out.println("comparing: " + chars[comp1] + " and " + chars[comp2]);
            if(chars[comp1] == chars[comp2])
            {
              match = true;
              matchIndex = comp2;
            }
          }
        }
        if(!match)
        {
          numChars = i+14;
          break;
        }
        i = matchIndex;
      }
      System.out.println(numChars);

    }
    catch(Exception e){
      e.printStackTrace();
    }
    
  }
}