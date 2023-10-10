import java.util.ArrayList;

public class Monkey{
  private Operation op;
  private Test test;
  private int inspections = 0;


  private Monkey testPassRecMonk, testFailRecMonk;

  private ArrayList<Integer> worrys = new ArrayList<>();

  public Monkey(Operation op, Test test, Monkey testPassReceiver, Monkey testFailReciever)
  {
    this.op = op;
    this.test = test;
    this.testPassRecMonk = testPassReceiver;
    this.testFailRecMonk = testFailReciever;
  }

  /**
   * Constructor. Reciever monkeys will need to be set before using.
   * @param op
   * @param test
   */
  public Monkey(Operation op, Test test)
  {
    this.op = op;
    this.test = test;
  }

  public void setRecMonks(Monkey testPassReciever, Monkey testFailReciever)
  {
    this.testFailRecMonk = testFailReciever;
    this.testPassRecMonk = testPassReciever;
  }

  public int performOp(int worryIndex)
  {
    int newWorry = op.operation(worrys.get(worryIndex));
    worrys.set(worryIndex, newWorry);
    inspections++;
    return newWorry;
  }

  private boolean performTest(int worryIndex)
  {
    
    if(worrys.size() <= worryIndex)
    {
      System.err.println("Monk does is not holding enough items");
      return false;
    }
    int worry = worrys.get(worryIndex);
    boolean t = test.test(worry);

    if(t){
      testPassRecMonk.throwI(testPassRecMonk,worry);
    }

    else{
      testFailRecMonk.throwI(testFailRecMonk,worry);
    }
    worrys.remove(worryIndex);
    return t;
  }

  private void throwI(Monkey rec, int worry)
  {
    rec.catchI(worry);
  }

  public void catchI(int worry)
  {
    worrys.add(worry);
  }

  public void takeTurn()
  {
    while(worrys.size()>0)
    {
      //operation on worry level as monkey inspects
      performOp(0);

      //Relief that monkey has not damaged item
      worrys.set(0, worrys.get(0)/3);
      
      //Test and throw item to new monkey
      performTest(0);
    }
  }

  public int getInspections(){
    return inspections;
  }
}