public class File {
  private boolean isDir;
  private int size;
  private String name;


  public File(boolean isDir, int size, String name){
    this.isDir = isDir;
    this.size = size;
    this.name = name;
  }

  public void setSize(int size)
  {
    this.size = size;
  }
  public int getSize()
  {
    return size;
  }
  public boolean isDir()
  {
    return isDir;
  }

  public String getName()
  {
    return name;
  }

}
