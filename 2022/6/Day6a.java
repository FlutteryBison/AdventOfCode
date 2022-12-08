import java.io.FileInputStream;
import java.util.Scanner;

public class Day6a{
  public static void main(String[] args) {
    try{
      FileInputStream fis = new FileInputStream("input.txt");
      Scanner scanner = new Scanner(fis);

      String input = scanner.next();

      char[] chars = input.toCharArray();

      // for(int i=3; i< chars.length; i++)
      // {
      //   int highestRepeat = -1;
      //   for(int j = i; j>i-3 && j > highestRepeat+1; j--)
      //   {
      //     for(int c = j-1; c>i-4 && c > highestRepeat; c--)
      //     {
      //       if(chars[j] == chars[c])
      //       {
      //         highestRepeat = c;
      //       }
      //     }
      //   }

      //   i += i
          
      // }
      int numChars = 0;

      for(int i=0; i<chars.length-4; i++)
      {
        boolean match = false;
        for(int comp1 = i; comp1 < i + 3 && !match; comp1++)
        {
          for(int comp2 = comp1+1; comp2 < i+4 && !match; comp2++)
          {
            //System.out.println("comparing: " + chars[comp1] + " and " + chars[comp2]);
            if(chars[comp1] == chars[comp2])
            {
              match = true;
            }
          }
        }
        if(!match)
        {
          numChars = i+4;
          break;
        }
      }
      System.out.println(numChars);

    }
    catch(Exception e){
      e.printStackTrace();
    }
    
  }
}