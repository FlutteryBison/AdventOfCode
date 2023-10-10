import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class day5a{


  public static void main(String[] args) {
    int buckets = 9;
    try{
      FileInputStream fis = new FileInputStream("input.txt");
      Scanner scanner = new Scanner(fis);

      LinkedList<Character>[] lists = new LinkedList[buckets];

      for(int i = 0; i < buckets; i++)
      {
        lists[i] = new LinkedList<Character>();
      }
       
      for(int i = 0; i < 8; i++)
      {
        String in = scanner.nextLine();
        for(int j = 0; j < buckets; j++)
        {
          char c = in.charAt((j*4)+1);
          if(c != ' ')
            lists[j].add(0, c);
        }
      }
      scanner.nextLine();
      scanner.nextLine();


      while(scanner.hasNext())
      {
        scanner.next();
        int n = scanner.nextInt();
        scanner.next();
        int from = scanner.nextInt();
        scanner.next();
        int to = scanner.nextInt();

        for(int i =0; i<n; i++)
        {
          char c = lists[from-1].removeLast();
          lists[to-1].add(c);
        }
      }

      for(int i = 0; i<buckets; i++)
      {
        System.out.print(lists[i].getLast());
      }

    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
}