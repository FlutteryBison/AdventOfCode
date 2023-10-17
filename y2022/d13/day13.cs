using System;
using list;

namespace prog
{
  class Program
  {
    static void Main(string[] args)
    {

      try
      {
          //Pass the file path and file name to the StreamReader constructor
          StreamReader sr = new StreamReader("input.txt");

          StringList list1;
          StringList list2;
          char[] validElems = {'0','1','2','3','4','5','6','7','8','9'};

          int i =1; //the idx of the current pair. AOC specifies it starts at one
          long runtot = 0;

          while(true)
          {
            list1 = new StringList(sr.ReadLine(),true,validElems);
            list2 = new StringList(sr.ReadLine(),true,validElems);
            Console.WriteLine("Attempting:" + list1.GetList());
            Console.WriteLine("Attempting:" + list2.GetList());
            Console.WriteLine();


            compareResult res = compare(list1,list2);

            if(res == compareResult.noSwap)
            {
              runtot+=i;
            }

            //Console.WriteLine(list1.GetList());
            //Console.WriteLine(list2.GetList());
            //Console.WriteLine(res);
            //Console.WriteLine();



            if(sr.ReadLine() == null)
            {
              break;
            }

            i++;
          }

          //close the file
          sr.Close();
          Console.WriteLine("Sum of idx of rows that are in correct order: " +runtot);

      }
      catch(Exception e)
      {
          Console.WriteLine("Exception: " + e.Message);
      }
      finally
      {
          Console.WriteLine("Executing finally block.");
      }
    }

    public static compareResult compare(StringList s1, StringList s2)
    {
      compareResult compRes = compareResult.indetermined;

      int i =0;
      while(compRes == compareResult.indetermined)
      {

        
        string elem1 = s1.getElementAt(i);
        bool iss1Int = int.TryParse(elem1,out int res1);

        

        string elem2 = s2.getElementAt(i);
        bool iss2Int = int.TryParse(elem2,out int res2);

        //if both are integers compare
        if(iss1Int && iss2Int)
        {
          if(res1 < res2)
          {
            return compareResult.noSwap;
          }
          if(res1==res2)
          {
            return compareResult.indetermined;
          }
          return compareResult.swap;
        }

        //if one is null, the list is empty so result can be determined
        if((elem1=="" || elem1 == null) && elem2!=null)
        {
          return compareResult.noSwap;
        }
        if(elem1 != null && (elem2==null || elem2==""))
        {
          return compareResult.swap;
        }
        if((elem1 == null || elem1=="") && (elem2==null || elem2 ==""))
        {
          return compareResult.indetermined;
        }


        //both are not integers, If one is an integer convert it to a list
        if(iss1Int)
        {
          elem1 = '[' +elem1+']';
        }
        //both are not integers, If one is an integer convert it to a list
        if(iss2Int)
        {
          elem2 = '[' +elem2+']';
        }

        compRes = compare(new StringList(elem1,false),new StringList(elem2,false));

        i++;
      }

      return compRes;


      
    }
  }

  public enum compareResult{
    swap,
    indetermined,
  noSwap
  }

  

}