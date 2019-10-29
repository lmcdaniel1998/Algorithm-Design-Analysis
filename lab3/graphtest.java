import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Queue;

class graphtest {

   public static void main(String[] args) {
      MyGraph mygraph = new MyGraph();
      try {
         String file = "Bfstest1.txt";
         mygraph.readfile_graph(file);
      }
      catch(FileNotFoundException exception) {
         System.out.println("fuck");
      }
      //mygraph.getVertices();
      ArrayList<HashSet<Integer>> hello = new ArrayList<HashSet<Integer>>();
      //GraphStart3.MyGraph getgraph = mygraph.new MyGraph();
      hello = mygraph.connectCheck();
      if (mygraph.bipartiteCheck() == true) {
         System.out.println("true");
      }
      else {
         System.out.println("false");
      }
      //mygraph.getVertices();
   }
}
