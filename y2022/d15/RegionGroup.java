import java.util.ArrayList;


/**
    Class representing groups of integer regions
    A region is a section of a number line bounded be an upper and lower limit.
    Class can store multiple disconnected regions along a number line
    Eg. the 3 disconnected regions: -2.6 - 8.3, 12.1 - 15.0, 47.66 - 165.3

    If working with discrete data, adjacent regions may not be merged.
    eg, when using Integers groups 1 - 5 and 6 - 10 are not considered overlapping and will not be merged.
 */
public class RegionGroup<T extends Comparable<T>> {


    //sorted list of  disconnected regions
    private ArrayList<Region<T>> regions = new ArrayList<>();

    public RegionGroup()
    {
        
    }

    public void addRegion(T bound1, T bound2)
    {

        Region<T> r = new Region<>(bound1, bound2);

        int idx = getInsertIndex(r);
        
        //found where lower bound is positioned
        //Now either insert it if it is disconnected from neighbours or merge it

        //Insert regardless of whether it overlapps other regions
        regions.add(idx,r);
        

        //Store the idx of the first region that potentially overlaps and needs to be merged
        //This is either the one that was inserted
        //or the one before the one that was inserted.
        int cur = idx;
        if(idx>0 && r.isOverlapping(regions.get(idx-1)))
        {
            cur =idx-1;
        }

        //check if the current region overlaps the one to its right, if it does, merge them and remove the right one.
        //Continue untill the right region no longer overlaps
        while(cur+1<regions.size() && regions.get(cur).isOverlapping(regions.get(cur+1)))
        {
            regions.get(cur).merge(regions.get(cur+1));
            regions.remove(cur+1);
        }
    }

    
    /**
     * Get the number of disconnected regions
     * This may be misleading for discrete data.
     * E.g if the group 1-5 is added then 6-10.
     * These are considered seperate groups.
     * @return //The number of disconnected groups
     */
    public int getNumRegions()
    {
        return regions.size();
    }

    /**
     * Returns the region at the specified index.
     * @param i The index of the region.
     * @return  The region at the index i.
     * @throws  IndexOutOfBoundsException if index is out of bounds
     */
    public Region<T> getRegionAt(int i)
    {
        return regions.get(i);
    }

    /**
     * Get the index r should be inserted at.
     * This is the lower bound is greater than than the lower bound of the preceeding region but less than the lower bound of the following region.
     * Groups may still need to be merged folling insertion.
     * @param r The region to get insertion idx of
     * @return  idx the region should be inserted into the group.
     */
    private int getInsertIndex(Region<T> r)
    {
        //perform binary search to insert
        int l=0;
        int h=regions.size();

        while(l<h)
        {
            int mid = (l+h)/2;
            if(regions.get(mid).isLowerBoundLessThanOrEqualTo(r))
            {
                l=mid+1;
            }
            else
            {
                h=mid;
            }
        }

        return l;

    }
}
