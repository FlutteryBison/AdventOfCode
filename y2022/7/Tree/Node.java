package Tree;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Node<T>{
  private T data;
  private String name;

  private Node<T> parent;
  private HashMap<String,Node<T>> children = new HashMap<>();

  public Node(String name)
  {
    this.name = name;
  }
  public Node(String name, T data)
  {
    this.name = name;
    this.data = data;
  }

  public void setParent(Node<T> parent)
  {
    this.parent = parent;
  }

  public void addChild(Node<T> child)
  {
    child.setParent(this);
    children.put(child.getName(),child);
  }

  public void addChild(String name)
  {
    Node<T> child = new Node<>(name);
    child.setParent(this);
    children.put(name, child);
  }

  public void addChild(String name, T data)
  {
    Node<T> child = new Node<>(name,data);
    child.setParent(this);
    children.put(name, child);
  }

  public void setData(T data)
  {
    this.data = data;
  }

  public Node<T> getChild(String name)
  {
    return children.get(name);
  }

  public Collection<Node<T>> getChildren()
  {
    return children.values();
  }

  public Node<T> getParent()
  {
    return parent;
  }

  public T getData()
  {
    return data;
  }

  public String getName()
  {
    return name;
  }

  public boolean isChild(String name)
  {
    return children.containsKey(name);
  }






}