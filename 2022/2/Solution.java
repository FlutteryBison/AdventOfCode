import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Scanner;

public class Solution{

  private HashMap<String, Integer> score = new HashMap<>();

  public static final char ROCK = 'A';
  public static final char PAPER = 'B'; 
  public static final char SCISSORS = 'C'; 

  

  public static final int ASCII_GAP = 'X' -'A';

  public Solution()
  {
    score.put(ROCK     + " " + ROCK,     4);
    score.put(ROCK     + " " + PAPER,    8);
    score.put(ROCK     + " " + SCISSORS,  3);
    score.put(PAPER    + " " + ROCK,     1);
    score.put(PAPER    + " " + PAPER,    5);
    score.put(PAPER    + " " + SCISSORS,  9);
    score.put(SCISSORS + " " + ROCK,     7);
    score.put(SCISSORS + " " + PAPER,    2);
    score.put(SCISSORS + " " + SCISSORS,  6);
  }


  public static void main(String[] args) {
    Solution game = new Solution();
    try{
      FileInputStream fis = new FileInputStream("input.txt");
      Scanner scanner = new Scanner(fis);
      int curCum = 0;
      while(scanner.hasNextLine())
      {
        String match = scanner.nextLine();
        char result = match.charAt(2);
        char move = match.charAt(0);

        //lose
        if(result == 'X')
        {
          move = (char)(move - 1);
        }
        //win
        if(result == 'Z')
        {
          move = (char)(move + 1);
        }

        if(move == 'A' - 1){
          move += 3;
        }
        if(move == 'C' + 1){
          move -= 3;
        }

        


        match = match.substring(0,2);

        match = match + move;

        curCum += game.score.get(match);
      }

      System.out.println(curCum);

    }catch(Exception e)
    {
      e.printStackTrace();
    }
  }


}