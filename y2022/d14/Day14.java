
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Day14 {

    
    private HashSet<Point> ocupied = new HashSet<>();
    private Point spawn = new Point(500, 0);
    private int maxY = 0;

    


    public static void main(String[] args) {
        Day14 d14 = new Day14();
        try
        {
            FileInputStream fis = new FileInputStream("input.txt");
            Scanner scanner = new Scanner(fis);
            ArrayList<Point> points = new ArrayList<>();

            while(scanner.hasNextLine())
            {
                String[] pointStr = scanner.nextLine().split(" -> ");
                for (String s : pointStr) {
                    String[] coord = s.split(",");

                    points.add(new Point(Integer.valueOf(coord[0]),Integer.valueOf(coord[1])));

      
                }

                for (int i = 0; i < points.size()-1; i++) {
                    d14.addWall(points.get(i), points.get(i+1));
                }
                points.clear();


            }
            //add line along bottom

            d14.addWall(new Point(-2500,d14.getMaxY()+2), new Point(3500,d14.getMaxY()+2));

            System.out.println(d14.runSimulation());

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }

    public void addWall(Point start, Point end){

        //maintain width
        maxY = Math.max(maxY, start.y);
        maxY = Math.max(maxY, end.y);



        boolean horz = false;
        int length;
        if(start.x != end.x)
        {
            horz = true;
            if(end.x < start.x)
            {
                Point copy = end;
                end = start;
                start = copy;
            }
            length = end.x -start.x;
        }
        else if(start.y !=end.y) 
        {
            if(end.y < start.y)
            {
                Point copy = end;
                end = start;
                start = copy;
            }
            length = end.y -start.y;
        }
        else{ //handle case of wall being single point
            ocupied.add(new Point(start));
            return;
        }

        for (int i = 0; i <= length; i++) {
            if(horz)
            {
                ocupied.add(new Point(start.x+i,start.y));
            }
            else
            {
                ocupied.add(new Point(start.x,start.y+i));
            }
        }
    }

    public int runSimulation()
    {
        int sandCount = 0;
        while(true)
        {
            //spawn sand
            Point curLoc = new Point(spawn);

            Point next;
            //Get the final position of the sand
            while(!((next = nextLoc(curLoc)).equals(curLoc)) && next.y>0)
            {
                curLoc = next;
            }

            

            if(next.y==0)
            {
                return sandCount+1;
            }


            ocupied.add(next);
            sandCount++;
        }
    }

    private Point nextLoc(Point curLoc)
    {
        //check if can move down
        Point next = new Point(curLoc.x,curLoc.y+1);
        
        if(!ocupied.contains(next))
        {
            return next;
        }

        //cannot move down, check down left
        next.x--;
        if(!ocupied.contains(next))
        {
            return next;
        }

        //cannot move down left, check down right
        next.x +=2;
        if(!ocupied.contains(next))
        {
            return next;
        }


        //cannot move in any direction, settle where is
        return new Point(curLoc);

    }

    public void setSpawn(Point spawn)
    {
        this.spawn = spawn;
    }


    public int getMaxY()
    {
        return maxY;
    }
}



