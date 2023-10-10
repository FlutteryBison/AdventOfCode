import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Monkey{
  private Operation op;
  private Test test;
  private int inspections = 0;
  private int testDivisor;


  private Monkey testPassRecMonk, testFailRecMonk;


  //Maps itemID to this monkeys remainder for that item
  private HashMap<Integer,Integer> allWorryRemainder = new HashMap<>();

  private ArrayList<Integer> curHeld = new ArrayList<Integer>();




  public Monkey(Operation op, Test test,int testDivisor, Monkey testPassReceiver, Monkey testFailReciever)
  {
    this.op = op;
    this.test = test;
    this.testPassRecMonk = testPassReceiver;
    this.testFailRecMonk = testFailReciever;
    this.testDivisor = testDivisor;
  }

  /**
   * Constructor. Reciever monkeys will need to be set before using.
   * @param op
   * @param test
   */
  public Monkey(Operation op, Test test, int testDivisor)
  {
    this.op = op;
    this.test = test;
    this.testDivisor = testDivisor;
  }

  public void setRecMonks(Monkey testPassReciever, Monkey testFailReciever)
  {
    this.testFailRecMonk = testFailReciever;
    this.testPassRecMonk = testPassReciever;
  }

  public void performOp()
  {
    Set<Integer> keys = allWorryRemainder.keySet();
    for (Integer id : keys) {
      
      allWorryRemainder.put(id, op.operation(allWorryRemainder.get(id))%testDivisor);
    }
  }

  private void performTest()
  {
    try{
      Iterator<Integer> i = curHeld.iterator();
      while(i.hasNext()){
        inspections++;

        int id = i.next();
        i.remove();

        boolean res = test.test(allWorryRemainder.get(id));
        if(res)
        {
          testPassRecMonk.throwI(testPassRecMonk,id);
        }
        else{
          testFailRecMonk.throwI(testFailRecMonk,id);
        }
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

  }

  private void throwI(Monkey rec, int itemID)
  {
    rec.catchI(itemID);
  }

  public void catchI(int itemID)
  {
    curHeld.add(itemID);
  }

  public void newItem(int id, int value, boolean isHolding)
  {
    allWorryRemainder.put(id, value);
    if(isHolding)
    {
      curHeld.add(id);
    }
  }

  public void takeTurn()
  {
    
    //operation on worry level as monkey inspects
    performOp();

    //Relief that monkey has not damaged item
    
    //Test and throw item to new monkey
    performTest();
    
  }

  public int getInspections(){
    return inspections;
  }
}