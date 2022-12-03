import java.io.FileInputStream;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Scanner;

public class Backpacks{



public static void main(String[] args) {

  BitSet[] seen = new BitSet[3];
  seen[0] = new BitSet(52);
  seen[1] = new BitSet(52);
  seen[2] = new BitSet(52);

  try{
    FileInputStream fis = new FileInputStream("input.txt");
    Scanner scanner = new Scanner(fis);

    int tot = 0;

    while(scanner.hasNextLine())
    {
      seen[0].clear();
      seen[1].clear();
      seen[2].clear();

      String[] in = new String[3];
      int[] length = new int[3];
      for(int i =0; i<3; i++)
      {
        in[i] = scanner.nextLine();
        length[i] = in[i].length();
      }


      for(int i = 0; i < 3; i++)
      {
        for(int j = 0; j < length[i]; j++)
        {
          char c = in[i].charAt(j);
          int seenInd = c>90 ? c-97:c-39;
          seen[i].set(seenInd);
        }
      }

      seen[0].and(seen[1]);
      seen[0].and(seen[2]);

      tot += seen[0].nextSetBit(0) +1;
      

    }

    System.out.println(tot);

  }

  catch(Exception e)
  {
    e.printStackTrace();
  }
}

}