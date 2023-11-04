public class Region <T extends Comparable<T>>{

    
    private T lowerBound; //inclusive
    private T upperBound; //inclusive


    /**
     * Constructs a new region with the given bounds.
     * @param bound1
     * @param bound2
     */
    public Region(T bound1, T bound2) {
        if(bound1.compareTo(bound2)<=0)
        {
            lowerBound = bound1;
            upperBound = bound2;
        }
        else{
            upperBound = bound1;
            lowerBound = bound2;
        }
    }


    /**
     * Compares the two regions and returns whether this region is completed encompased by region r
     * If the upper and lower bounds for both regions have the same value, returns true.
     * @param r Tests if this region is completed enclosed by region r
     * @return  True if this region is completety within r false if not.
     */
    public boolean isWithin(Region<T> r)
    {
         return lowerBound.compareTo(r.lowerBound)<= 0 && upperBound.compareTo(r.upperBound)>= 0;
    }

    /**
     * Compares the two regions and returns whether region r is completed encompased by region this region
     * If the upper and lower bounds for both regions have the same value, returns true.
     * @param r Tests if region r is completed enclosed by  this region
     * @return  True if region r is completety within this region false if not.
     */
    public boolean isEnclosing(Region<T> r)
    {
        return lowerBound.compareTo(r.lowerBound)>= 0 && upperBound.compareTo(r.upperBound)>= 0;
    }

    /**
     * Returns this entire region is less than the entire region r.
     * @param r
     * @return
     */
    public boolean isLessThan(Region<T> r)
    {
        return upperBound.compareTo(r.lowerBound)<0;
    }

    public boolean isLowerBoundLessThanOrEqualTo(Region<T> r)
    {
        return lowerBound.compareTo(r.lowerBound)<=0;
    }


    /**
     * Returns whether this entire region is greater than the entire region r
     * @param r
     * @return
     */
    public boolean isGreaterThan(Region<T> r)
    {
        return lowerBound.compareTo(r.upperBound)>0;
    }

    /**
     * Returns Whether this region overlaps region r.
     * @param r
     * @return
     */
    public boolean isOverlapping(Region<T> r)
    {
        /*
         * First line of return statement catches these two possible overlapping cases
         * 
         * this         |           |
         * r      |          |
         * Or
         * 
         * this         |           |
         * r      |                    |
         * 
         * second line catches this overlapping case
         * 
         * this          |          |
         * r                    |         |
         * 
         * Third line catches the overlapping case where r is contained completely within this
         * 
         * this    |                       |
         * r            |           |
         * 
         */

        //Store compare to results to remove need for multiple calls in return statement
        int l12l2 = lowerBound.compareTo(r.lowerBound);
        int l12u2 = lowerBound.compareTo(r.upperBound);
        int u12l2 = upperBound.compareTo(r.lowerBound);
        int u12u2 = upperBound.compareTo(r.upperBound);


            return (l12l2>=0 && l12u2<=0) 
                || (u12l2>=0 && u12u2<=0)
                || (l12l2<=0 && u12u2>=0);
    }


    /**
     * Merges the region r into this one
     * the new region will be between min(this.lowerBound,r.lowerBound) and max(this.upperBound,r.upperBound).
     * eg for the regions:
     * 
     * this          |         |
     * r        |         |
     * 
     * after merge
     * this     |              |
     * 
     * 
     * and for
     * 
     * this                   |            |
     * r        |          |
     * 
     * after merge
     * this     |                          |
     * 
     * 
     * 
     * @param r //The region to merge into this one.
     */
    public void merge(Region<T> r)
    {
        if(lowerBound.compareTo(r.lowerBound) >0)
        {
            lowerBound = r.lowerBound;
        }
        if(upperBound.compareTo(r.upperBound) <0)
        {
            upperBound = r.upperBound;
        }
    }

    /**
     * Get the inclusive lower bound of the region.
     *
     * @return The lower bound of the region. It is included in the region itself
     */
    public T getLowerBound() {
        return lowerBound;
    }
    /**
     * Gets the inclusive upper bound of the region.
     * @return The upper bound of the region. It is included in the region itself.
     */
    public T getUpperBound() {
        return upperBound;
    }

    
    
    
}
