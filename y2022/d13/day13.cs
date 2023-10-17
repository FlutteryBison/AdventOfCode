using System;
using System.Collections;
using System.Runtime.InteropServices;
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

          List<StringList> input = new();

          //add the items as specified by AOC.
          StringList mark1 = new StringList("[[2]]",false);
          StringList mark2 = new StringList("[[6]]",false);
          input.Add(mark1);
          input.Add(mark2);


          char[] validElems = {'0','1','2','3','4','5','6','7','8','9'};

          int i =1; //the idx of the current pair. AOC specifies it starts at one
          long runtot = 0;

          //add all inputs to the list
          while(true)
          {
            input.Add(new StringList(sr.ReadLine(),true,validElems));
            input.Add(new StringList(sr.ReadLine(),true,validElems));


            if(sr.ReadLine() == null)
            {
              break;
            }

            i++;
          }

          
          input.Sort();
          Predicate<StringList> mark1Finder = (StringList sl) =>{return sl == mark1;};
          Predicate<StringList> mark2Finder = (StringList sl) =>{return sl == mark2;};

          int idx1 = input.FindIndex(mark1Finder);
          int idx2 = input.FindIndex(mark2Finder);

          //AOC uses 1 based idx so add 1 to results
          //aoc wants idx of sorted mark1 * idx sorted mark2
          
          Console.WriteLine("Result = " + (idx1+1)*(idx2+1));

          

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

  }

    

 

  

}