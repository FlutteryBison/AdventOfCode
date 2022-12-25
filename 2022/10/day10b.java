import java.io.FileInputStream;
import java.util.Scanner;

public class day10b {
  public static void main(String[] args) {
    int pc = 0;
    int x = 2;
    char[] crt = new char[240];
    for(int i = 0; i < crt.length; i++)
    {
      crt[i] = '.';
    }
    try{
      FileInputStream fis = new FileInputStream("input.txt");
      Scanner scanner = new Scanner(fis);
      

      while(scanner.hasNextLine())
      {
        
        String line = scanner.nextLine();
        String[] s = line.split(" ");
        //System.out.println("pc: " + pc + " pos: " + (pc - (40 * (pc/40))));
        
        //pc is using index from 0,
        //crt is indexing from 1
        //check if between pc-1 and pc+1
        int loc = pc - (40 * (pc/40));
        if(x >= loc && x <= loc+2)
        {
          crt[pc] = '#';
        }
        
        pc++;
      
        if(s[0].equals("addx")){
          loc = pc - (40 * (pc/40));
          if(x >= loc && x <= loc+2)
          {
            crt[pc] = '#';
          }
          x += Integer.parseInt(s[1]);
          pc++;
        }
      }

      scanner.close();
    }
    catch(Exception e){
      e.printStackTrace();
    }
    for(int i = 0; i < 6; i++)
    {
      for(int j = 0; j<40; j++)
      {
        System.out.print(crt[i*40 + j]);
      }
      //System.out.flush();
      System.out.println();
    }

    System.out.println();

  }
}
