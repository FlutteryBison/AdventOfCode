import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Scanner;

import Tree.Node;

public class day7a {
  private Node<File> fileTreeRoot;
  private Node<File> curFile;
  


  public static void main(String[] args) {
    day7a dir = new day7a();

    dir.fileTreeRoot = new Node<>("/");
    dir.curFile = dir.fileTreeRoot;
    

    try{
    FileInputStream fis = new FileInputStream("input.txt");
    Scanner scanner = new Scanner(fis);
    scanner.nextLine();
    

    while(scanner.hasNextLine())
    {
      String in = scanner.nextLine();
      in = in.replaceFirst("\\$ ", "");
      String[] subs = in.split(" ");

      switch(subs[0])
      {
        case "cd":
          dir.cd(subs[1]);
          break;
        case "ls":
          break;
        case "dir":
          File f = new File(true, 0, subs[1]);
          dir.curFile.addChild(subs[1],f);
          break;
        default:
          int size = Integer.parseInt(subs[0]);
          f = new File(false, size, subs[1]);
          dir.curFile.addChild(subs[1],f);

      }
      
    }
    
    dir.calcFileSizes(dir.fileTreeRoot);
    dir.printTree(dir.fileTreeRoot, 0);
    System.out.println(dir.smallFileTotalSize(dir.fileTreeRoot));






    scanner.close();
   }catch(FileNotFoundException e)
   {
    //Should be there
    e.printStackTrace();
   }
  }

  private void cd(String dir)
  {
    if(dir.equals(".."))
    {
      curFile = curFile.getParent();
    }
    else
    {
      //Directory does not currently exist, create it
      if(!curFile.isChild(dir))
      {
        curFile.addChild(dir);
      }
      
      curFile = curFile.getChild(dir);
      
    }
  }

  private int smallFileTotalSize(Node<File> start)
  {
    Collection<Node<File>> list = start.getChildren();

    File f = start.getData();
    int tot = 0;
    
    for (Node<File> child : list) {
      tot += smallFileTotalSize(child);
      
    }
    if(f.isDir() && f.getSize() <= 100000)
    {
      return tot + f.getSize();
    }

    return tot;
    
    
  }



  private int calcFileSizes(Node<File> start)
  {
    Collection<Node<File>> list = start.getChildren();

    File f = start.getData();
    int tot = 0;
    if(f != null)
    {
      tot = f.getSize();
    }
    
    for (Node<File> child : list) {
      tot += calcFileSizes(child);
    }

    if(f != null)
    {
      f.setSize(tot);
    }
    else{
      f = new File(true, tot, start.getName());
      start.setData(f);
    }
    
    return tot;

  }
  

  private void printTree(Node<File> start, int level)
  {

    for(int i=-1; i<level; i++)
    {
      System.out.print("-");
      
    }
    System.out.print(start.getName());

    Collection<Node<File>> list = start.getChildren();

    File f = start.getData();
    if(f != null)
      System.out.print(" " + f.getSize());

    System.out.println();

    
    for (Node<File> child : list) {
      printTree(child, level + 1);
    }
    

  }
  
}
