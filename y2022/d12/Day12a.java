
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

import loc.Point;


public class Day12a {

    


    public static void main(String[] args) {
        

        try
        {
            FileInputStream fis = new FileInputStream("input.txt");
            Scanner scanner = new Scanner(fis);
            ArrayList<String> lines = new ArrayList<String>();

            while(scanner.hasNextLine())
            {
                lines.add(scanner.nextLine());
            }

            PriorityQueue<Node> queue = new PriorityQueue<>();

            int[][] map = new int[lines.get(0).length()][lines.size()];

            System.out.println(map.length);
            System.out.println(map[0].length);

            Point start = null;
            Point end = null;


            for(int y = 0; y<map[0].length; y++)
            {
                String line = lines.get(y);

                for(int x = 0; x<map.length; x++)
                {
                    char c = line.charAt(x);
                    if(c == 'S')
                    {
                        start = new Point(x, y);
                        c='a';
                    }
                    if(c == 'E')
                    {
                        end = new Point(x, y);
                        c='z';
                    }
                    map[x][y] = c - 'a';
                }
            }

            HeightMap hm = new HeightMap(map);

            int steps = hm.minStepsBetween(start, end);

            System.out.println("Steps from (" + start.x + "," + start.y + ") to (" + end.x + "," + end.y + "): " + steps);

            int[][] distances = hm.minStepsBetweenAll(end);

            int minStepsTo1 = Integer.MAX_VALUE;
            Point p = new Point(-1, -1);

            for (int i = 0; i < distances[0].length; i++) {
                for (int j = 0; j < distances.length; j++) {
                    if(map[j][i] == 0 && distances[j][i] < minStepsTo1)
                    {
                        minStepsTo1 = distances[j][i];
                        p.x = j;
                        p.y = i;
                    }
                }
            }

            System.out.println("Shortest path from a lowest point to highest point:");
            System.out.println("From ("+p.x+","+p.y+") to ("+end.x+","+end.y+") at " + minStepsTo1  + " steps");






            scanner.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        
    }


    
}
