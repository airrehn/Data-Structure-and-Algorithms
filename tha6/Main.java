import java.lang.Math;
import java.io.*;
import java.io.IOException;
import java.util.*;

// Every vertex in this BST is a Java Class
class BSTVertex {
  // all these attributes remain public to slightly simplify the code
  public BSTVertex parent, left, right;
  public String key;
  public int height; // will be used in lecture on AVL
  public int size; // will be used in lecture on AVL
  public int balanceFactor;

  BSTVertex(String v) {
    key = v;
    parent = left = right = null;
    height = 0;
    balanceFactor = 0;
    size = 1;
  }

}

// This is just a sample implementation
// There are other ways to implement BST concepts...
class BST {
  public BSTVertex root;

  public BST() {
    root = null;
  }

  // public method called to search for a value v.
  // Return v if it is found in the BST otherwise return -1.
  // Here the assumption is that -1 is never a valid key value.
  public BSTVertex search(String v) {
    BSTVertex res = search(root, v);
    return res;
  }

  // helper method to perform search
  public BSTVertex search(BSTVertex T, String v) {

    if (T == null)
      return null; // not found
    else if (T.key.indexOf(v)==0)
      return T; // found
    else if (T.key.compareTo(v) < 0)
      return search(T.right, v); // search to the right
    else
      return search(T.left, v); // search to the left
  }

  // public method called to perform inorder traversal
  public void inorder() {
    inorder(root);
    System.out.println();
  }

  // helper method to perform inorder traversal
  public void inorder(BSTVertex T) {
    if (T == null)
      return; // base case

    inorder(T.left); // recursively go to the left
    System.out.printf(" %d", T.key); // visit this BST node
    inorder(T.right); // recursively go to the right
  }

  // public method called to insert a new key with value v into BST
  public void insert(String v) {
    root = insert(root, v);
  }

  // helper recursive method to perform insertion of new vertex into BST
  public BSTVertex insert(BSTVertex T, String v) {
    if (T == null)
      return new BSTVertex(v); // insertion point is found

    if (T.key.compareTo(v) < 0) { // search to the right
      T.right = insert(T.right, v);
      T.right.parent = T;
    } else { // search to the left
      T.left = insert(T.left, v);
      T.left.parent = T;
    }

    T = update(T);

    return T; // return the updated BST
  }

  public BSTVertex update(BSTVertex T) {

    T.height = Math.max(T.left == null ? -1 : T.left.height, T.right == null ? -1 : T.right.height) + 1;

    T.size = (T.left == null ? 0 : T.left.size) + (T.right == null ? 0 : T.right.size) + 1;

    if ((T.left == null) && (T.right == null)) {
      T.balanceFactor = 0;
    } else if (T.left == null) {
      T.balanceFactor = -1 - T.right.height;
    } else if (T.right == null) {
      T.balanceFactor = T.left.height - (-1);
    } else {
      T.balanceFactor = T.left.height - T.right.height;
    }

    if ((T.balanceFactor == 2) && (T.left.balanceFactor <= 1 && T.left.balanceFactor >= 0)) {
      T = rightRotate(T);
    } else if ((T.balanceFactor == 2) && (T.left.balanceFactor == -1)) {
      T.left = leftRotate(T.left);
      T = rightRotate(T);
    } else if ((T.balanceFactor == -2) && (T.right.balanceFactor <= 0 && T.right.balanceFactor >= -1)) {
      T = leftRotate(T);
    } else if ((T.balanceFactor == -2) && (T.right.balanceFactor == 1)) {
      T.right = rightRotate(T.right);
      T = leftRotate(T);
    }

    return T;

  }

  public BSTVertex leftRotate(BSTVertex T) {
    BSTVertex w = T.right;
    w.parent = T.parent;
    T.parent = w;
    T.right = w.left;
    if (w.left != null)
      w.left.parent = T;
    w.left = T;

    T = update(T);
    w = update(w);

    return w;

  }

  public BSTVertex rightRotate(BSTVertex T) {
    BSTVertex w = T.left;
    w.parent = T.parent;
    T.parent = w;
    T.left = w.right;
    if (w.right != null)
      w.right.parent = T;
    w.right = T;

    T = update(T);
    w = update(w);

    return w;
  }


  public int calcNames(String nickname) {
    return calcNames(0, root, nickname);
  } 


  public int calcNames(int count, BSTVertex Node, String nickname) {

    BSTVertex node = search(Node, nickname);
    if (node == null) {
      return count;
    } else {
      count ++;
      count = calcNames(count, node.left, nickname);
      count = calcNames(count, node.right, nickname);
    } 
    return count;
  }
}


public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    BST T = new BST();

    HashMap<String,Integer> map = new HashMap<String, Integer>();
    int linesName = Integer.parseInt(br.readLine());

    for(int i =0; i<linesName; i++) {
      String name = br.readLine();
      T.insert(name);
    }
    int linesNick = Integer.parseInt(br.readLine());

    for(int i =0; i<linesNick; i++) {
      int count;
      String nick = br.readLine();
      if (map.containsKey(nick)) {
        pw.println(map.get(nick));
      } else {
       count = T.calcNames(nick);
       map.put(nick,count);
       pw.println(count);
      }
    }

    br.close();
    pw.close();
  }
}
