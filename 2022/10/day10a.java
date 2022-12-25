import java.io.FileInputStream;
import java.util.Scanner;

public class day10a {
  public static void main(String[] args) {
    int pc = 0;
    int x = 1;
    int[] sig = new int[225];
    try{
      FileInputStream fis = new FileInputStream("input.txt");
      Scanner scanner = new Scanner(fis);
      

      while(scanner.hasNextLine())
      {
        if(pc >217)
        {
          System.out.println("pause");
        }
        String line = scanner.nextLine();
        String[] s = line.split(" ");
        
        
        
        sig[pc] = x * (pc+1);
        pc++;
      
        if(s[0].equals("addx")){
          
          sig[pc] = x * (pc+1) ;
          x += Integer.parseInt(s[1]);
          pc++;
        }
      }

      scanner.close();
    }
    catch(Exception e){
            e.printStackTrace();
    }

    System.out.println(sig[19] + sig[59] + sig[99] + sig[139]+sig[179]+sig[219]);

  }
}
