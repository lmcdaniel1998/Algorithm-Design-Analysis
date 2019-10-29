import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Queue;

class toptester {

   public static void main(String[] args) {
      TopSorter mytop = new TopSorter();
      String file = "topSortTest2.txt";
      ArrayList<Integer> list = mytop.topSortGenerator(file);
      for (int i = 0; i < list.size(); i++) {
         System.out.print(list.get(i) + " ");
      }
   }
}
