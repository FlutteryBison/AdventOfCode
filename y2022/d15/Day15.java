import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day15
{




    public static void main(String[] args) {

        

        //Read Input

        try{
        FileInputStream fis = new FileInputStream("input.txt");
        Scanner scanner = new Scanner(fis);

        Pattern pat = Pattern.compile("-?\\d+");
        ArrayList<Sensor> sensors = new ArrayList<>();


        while(scanner.hasNextLine()){
            String line = scanner.nextLine();

            Matcher m = pat.matcher(line);
            int[] coords = new int[4];
            int i=0;
            while(m.find())
            {
                coords[i] = Integer.parseInt(m.group());
                i++;
            }

            sensors.add(new Sensor(coords[0], coords[1], coords[2], coords[3]));


        }

        //______________//
        //    PART A    //
        //______________//

        //Store all the positions that cannot be a beacon
        RegionGroup<Long> rg = new RegionGroup<>();

        int yRow = 2000000;

        //Store all the location on the y row that we is a beacon.
        HashSet<Integer> beaconX = new HashSet<>();

        for (Sensor s : sensors) {
            int ydist = Math.abs(yRow-s.getY());
            int halfWidth = s.getManhattanDistance()-ydist;

            if(halfWidth>0)
            {
                rg.addRegion((long)s.getX()-halfWidth, (long)s.getX()+halfWidth);
            }

            if(s.getBeaconY() == yRow)
            {
                beaconX.add(s.getBeaconX());
            }

        }

        //sum the widths of all the regions
        int cumtot = 0;
        for (int i = 0; i < rg.getNumRegions(); i++) {
            Region<Long> reg = rg.getRegionAt(i);

            //add 1 as upper and lower are inlusive
            cumtot += reg.getUpperBound()-reg.getLowerBound()+1;
        }
        //remove any beacon that has been counted as not a beacon
      
        System.out.println(cumtot - beaconX.size());



        //______________//
        //    PART B    //
        //______________//


        int limit = 4000000;
        //limit = 20;
        boolean found = false;
        for(int i=0; i<=limit && !found; i++)
        {
            rg = new RegionGroup<>();

            yRow = i;


            for (Sensor s : sensors) {
                int ydist = Math.abs(yRow-s.getY());
                int halfWidth = s.getManhattanDistance()-ydist;

                if(halfWidth>0)
                {
                    int left  = s.getX()-halfWidth;
                    int right = s.getX()+halfWidth;

                    if(left<=limit&&right>=0)
                        rg.addRegion((long)Math.max(left, 0), (long)Math.min(right, limit));
                }

                

            }

            
            for(int j = 0; j<rg.getNumRegions()-1; j++)
            {
                //
                if((rg.getRegionAt(j+1).getLowerBound() - rg.getRegionAt(j).getUpperBound()) >1)
                {
                    //System.out.println("Row Found: " +i);
                    //System.out.println("Row is " + (i/(float)limit)*100 +"% through all rows");
                    long x = rg.getRegionAt(j).getUpperBound()+1;
                    System.out.println("Beacon at ("+x + "," + i + ")");
                    System.out.println("Tuning Freq: " + (x*4000000 + i));
                    found = true;
                    break;

                }
            }
            
            

            
        }


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }



    
}