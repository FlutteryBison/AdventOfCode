import java.io.FileInputStream;
import java.util.Scanner;


public class Day11b {

  //Return val for parsing
  private class item{
    int id;
    int val;
    int heldBy;
    public item(int id, int val, int heldBy)
    {
      this.id = id;
      this.val = val;
      this.heldBy = heldBy;
    }
  }

  private int freeID = 0;

  Monkey[] monks = new Monkey[4];
  int[] testPassRecMonk = new int[monks.length*2];
  int[] testFailRecMonk = new int[monks.length*2];
  
  public static void main(String[] args) {
    Day11b day = new Day11b();

    try{
      FileInputStream fis = new FileInputStream("input.txt");
      Scanner scanner = new Scanner(fis);

      //jagged array of items for each monk (again hard coded num monkeys)
      item items[][] = new item[4][];

      int monkIndex = 0;
      while(scanner.hasNextLine())
      {
        String[] lines = new String[6];
        //clear monkey number
        lines[0] = scanner.nextLine();

        lines[1] = scanner.nextLine();
        lines[2] = scanner.nextLine();
        lines[3] = scanner.nextLine();
        lines[4] = scanner.nextLine();
        lines[5] = scanner.nextLine();

        items[monkIndex] = day.parseInputMonk(lines);

        //clear blank line
        if(scanner.hasNextLine())
          scanner.nextLine();

          monkIndex++;
      }

      //give monkeys items
      //iterate through each monkey to set
      for(int i =0; i< day.monks.length; i++)
      {
        //iterate through each monkeys items
        for(int j = 0; j<items.length; j++)
        {
          boolean isHolding = false;
          if(i == j)
          {
            isHolding = true;
          }
          //iterate through each item
          for(int c = 0; c < items[j].length; c++)
          {
            day.monks[i].newItem(items[j][c].id, items[j][c].val, isHolding);
          }
        }
      }

      //add reciever monkeys
      for(int i=0; i<day.monks.length; i++)
      {
        day.monks[i].setRecMonks(
          day.monks[day.testPassRecMonk[i]],
          day.monks[day.testFailRecMonk[i]]
        );
      }

      for(int round = 0; round<20; round++)
      {
        for(int i = 0; i<day.monks.length; i++)
        {
          day.monks[i].takeTurn();
        }
      }

      int[] highest = new int[2];

      for(int i = 0; i < day.monks.length; i++)
      {
        int inspections = day.monks[i].getInspections();
        if(inspections > highest[0]){
          highest[1] = highest[0];
          highest[0] = inspections;
          continue;
        }
        if(inspections > highest[1])
        {
          highest[1] = inspections;
        }

        
      }
      System.out.println(highest[0] * highest[1]);

    }catch(Exception e)
    {
      e.printStackTrace();
    }

  }



  /**
   * Takes a string in the format:
   * Monkey n:
   * Starting items: i0, i1, ..., in
   * Operation: new = old * p1
   * Test: divisible by t1
   *  If true: throw to monkey mt
   *  If false: throw to monkey mf
   * 
   * For example:
   * 
   * Monkey 0:
   * Starting items: 52, 60, 85, 69, 75, 75
   * Operation: new = old * 17
   * Test: divisible by 13
   *  If true: throw to monkey 6
   *  If false: throw to monkey 7
   * 
   * And creates a new monkey with the given operation, test and starting items
   * Reciever monkeys are not added as they may not yet have been created.
   * The recievers are stored to be added later.
   * 
   * @param s //The input string to parse
   */
  public item[] parseInputMonk(String[] lines)
  {
    //Just hard code number of monkeys
    item[] items = null;
    
    try{
      //get Monk number
      Integer monkIndex = Integer.valueOf( 
        lines[0].substring(7, lines[0].length()-1)
      );

      //create operation
      String[] subs = lines[2].split(" ");
      
      Operation op = null;
      if(IsNumeric(subs[7]))
      {
        final int p1 = Integer.parseInt(subs[7]);

        if(subs[6].equals("+"))
        {
          op = (p2) -> {return p2+p1;};
        }
        else if(subs[6].equals("*"))
        {
          op = (p2) -> {return p2*p1;};
        }
      }
      else
      {
        if(subs[6].equals("+"))
        {
          op = (p2) -> {return p2+p2;};
        }
        else if(subs[6].equals("*"))
        {
          op = (p2) -> {return p2*p2;};
        }
      }

      //Create test
      Test test;
      String[] testSub =lines[3].split(" ");
      final int t1 = Integer.parseInt(testSub[5]);
      test = (t2) -> {return t2%t1 == 0;};

      monks[monkIndex] = new Monkey(op, test,t1);


      //get starting items
      
      String sub = lines[1].substring(18);
      String[] itemsStr = sub.split(", ");

      items = new item[itemsStr.length];
      
      for(int i=0; i < itemsStr.length; i++)
      {
        int worry = Integer.parseInt(itemsStr[i]);
        items[i] = new item(freeID, worry, monkIndex);
        freeID++;
      }

      //parse recieving monkekys
      testPassRecMonk[monkIndex] = Integer.parseInt(lines[4].split(" ")[9]);
      testFailRecMonk[monkIndex] = Integer.parseInt(lines[5].split(" ")[9]);
      

    }
    catch(NumberFormatException e)
    {
      e.printStackTrace();
    }

      
    
 
    

    return items;



  }

  public boolean IsNumeric(String s)
  {
    try{
      Integer.parseInt(s);
      return true;
    }
    catch(Exception e)
    {
      return false;
    }
  }
}
