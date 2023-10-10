
import java.util.HashMap;
import java.util.LinkedList;

import loc.Point;

public class HeightMap {

    private final static int NORTH=0,WEST=1,SOUTH=2,EAST=3;

    private int[][] map;

    

    public HeightMap(int[][] map)
    {
        this.map = map;
    }


    
    /*
     * Uses  dijksras algorithm to calculate minimum distance between two points.
     * 
     * 
     * As weight is always equal between connected node the first route found to a node is always the fastest.
     * 
     * Therefore once a node has been reached its cost from the start will not need to be altered.
     * 
     * It therefore does not need to be considered if reaching it again so is stored in a "Node seen structure"
     * 
     * once a cost is found for a node it is added to a queue for nodes to be considered next. Because all weights are equal,
     * the node can always be placed at the back of the queue, and as its cost is final, its postion will never need to be re-evaluated
     * 
     * Then when getting the node with the lowest weight to consider next, its a simple matter of retrieving the node at the front of the queue,
     * 
     */
   



    
    /**
     * Minimum steps between the given steps and the end. considers contraints set on advent of code
     * 
     * @param start
     * @param end
     * @return
     */
    public int minStepsBetween(Point start, Point end)
    {
        //queue
        LinkedList<Node> next = new LinkedList<>();
        HashMap<Point,Node> distanceIsCalculated = new HashMap<Point,Node>();

        Node n = new Node(start);
        n.updateCost(0, null);

        next.add(n);
        distanceIsCalculated.put(n.p, n);

        int[] dirs = {NORTH,SOUTH,EAST,WEST};


        boolean endReached = false;

        while(!next.isEmpty() && !endReached)
        {
            Node cur = next.remove();

            for (int dir : dirs) {
                if(canMoveInDir(cur.p, dir,false))
                {
                    Point nextPoint = getAdjecentPointInDir(cur.p, dir);

                    if(!distanceIsCalculated.containsKey(nextPoint))
                    {
                        Node found = new Node(new Point(nextPoint),cur.cost+1,cur);
                        distanceIsCalculated.put(new Point(nextPoint), found);
                        next.add(found);

                        if(nextPoint.equals(end))
                        {
                            endReached = true;
                            break;
                        }
                    }
                }
                
            }
            //printMap(distanceIsCalculated);

            

        }

        return distanceIsCalculated.get(end).cost;

    }  



    //Minimum steps between the end point and all other squares
    //just copied and pasted to mostly to quickly find the solution
    public int[][] minStepsBetweenAll(Point end)
    {

        int[][] distances = new int[map.length][map[0].length];
        for (int i = 0; i < distances[0].length; i++) {
            for (int j = 0; j < distances.length; j++) {
                distances[j][i] = Integer.MAX_VALUE;
            }
            
        }
        //queue
        LinkedList<Node> next = new LinkedList<>();
        HashMap<Point,Node> distanceIsCalculated = new HashMap<Point,Node>();

        Node n = new Node(end);
        n.updateCost(0, null);

        next.add(n);
        distanceIsCalculated.put(n.p, n);

        distances[n.p.x][n.p.y] = n.cost;

        int[] dirs = {NORTH,SOUTH,EAST,WEST};


        boolean endReached = false;

        while(!next.isEmpty() && !endReached)
        {
            Node cur = next.remove();

            for (int dir : dirs) {
                if(canMoveInDir(cur.p, dir,true))
                {
                    Point nextPoint = getAdjecentPointInDir(cur.p, dir);

                    if(!distanceIsCalculated.containsKey(nextPoint))
                    {
                        Node found = new Node(new Point(nextPoint),cur.cost+1,cur);
                        distanceIsCalculated.put(new Point(nextPoint), found);
                        next.add(found);

                        distances[found.p.x][found.p.y] = found.cost;
                        
                    }
                }
                
            }
            

            

        }

        return distances;
    }


    /**
     * Checks if it is possible to move from point p in direction dir
     * if reverse is true:
     *  checks if is possible to move from direction dir to point p
     * @param p
     * @param dir
     * @param reverse
     * @return
     */
    private boolean canMoveInDir(Point p, int dir, boolean reverse)
    {
        Point next = getAdjecentPointInDir(p, dir);
        if(next == null)
        {
            return false;
        }

        //check point is on map
        if(!(next.x>=0 && next.y>=0 && next.y<map[0].length && next.x<map.length))
        {
            return false;
        }


        //check isnt to big a step up
        if(!reverse)
        {
            if(map[next.x][next.y] > map[p.x][p.y]+1)
            {
                return false;
            }
        }
        else //test if can move from next to p
        {
            if(map[p.x][p.y] > map[next.x][next.y]+1)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the point adjacent to p in the direction given by dir
     * If dir is not a cardinal direction as given by static members, returns null
     * @param p
     * @param dir
     * @return
     */
    private Point getAdjecentPointInDir(Point p, int dir)
    {
        Point next = null;
        switch(dir)
        {
            case NORTH:
                next = new Point(p.x, p.y-1);
                break;

            case EAST:
                next = new Point(p.x+1, p.y);
                break;

            case SOUTH:
                next = new Point(p.x, p.y+1);
                break;

            case WEST:
                next = new Point(p.x-1, p.y);
                break;
        }

        return next;
    }

}
