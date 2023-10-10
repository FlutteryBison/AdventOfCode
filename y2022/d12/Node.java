

import loc.Point;

public class Node implements Comparable<Node>{
    loc.Point p;
    int cost= Integer.MAX_VALUE;
    Node prev;

    public Node(Point p){
        this.p = p;
    }

    public Node(Point p,int cost, Node prev){
        this.p = p;
        this.cost = cost;
        this.prev = prev;
    }

    /**
     * If the cost is less than the current cost, the cost and previous node are set to the new values.
     * @param cost
     * @param prev
     * @return True if the cost is less and values updated, false if no change ocured
     */
    public boolean updateCost(int cost,Node prev)
    {
        if(cost<this.cost){
            this.cost = cost;
            this.prev = prev;
            return true;
        }

        return false;
    }


    @Override
    public int compareTo(Node n) {
        return n.cost - this.cost;
    }

}
