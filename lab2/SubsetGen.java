import java.util.ArrayList;
import java.util.Collections;

//	This class generates subsets of a set represented as a string in Java

public class SubsetGen   {

   public ArrayList<String> getSubsets(String word)   {
      ArrayList<String> subsets = new ArrayList<String>();

      //   Put your algorithm here

      // create temp list for storing cropped word and last for the cropped out letter 
      ArrayList<String> temp = new ArrayList<String>();
      ArrayList<String> last = new ArrayList<String>();

      // cycle until all of the characters in the word have been last
      if (word.length() > 0) {

         // recursive call to fill temp with last character cropped
         temp = getSubsets(word.substring(0, word.length() - 1));
         // save the last character
         last.add(word.substring(word.length() - 1, word.length()));

         // add all of the combinations without last 
         for (int i = 0; i < temp.size(); i++) {
            subsets.add(temp.get(i));
         }
         // add all of the combinations with last
         for (int i = 0; i < temp.size(); i++) {
            subsets.add(temp.get(i) + last.get(0));
         }
         return subsets;
      }
      else {
         // base case
         subsets.add("");
      }
      // return subsets;
      return subsets;
   }

   public ArrayList<String> getGrayCode(int n)   {
      //   Put your algorithm here
      ArrayList<String> graycodes = new ArrayList<String>();

      // check if only graycode in list is 1, if it is make graycodes two bits 
      if (n == 1) {
         graycodes.add("0");
         graycodes.add("1");
      }
      else {
         // recurse until there is only one bit left
         if (n != 1) {
            graycodes = getGrayCode(n - 1);
         }
         // clone graycodes and store in codes
         ArrayList<String> codes = new ArrayList<String>();
         codes.addAll(graycodes);
         // reverse graycodes
         Collections.reverse(graycodes);
         // add the reversed graycodes to the end of the regular graycodes
         codes.addAll(graycodes);
         // append 0 to the front of the first n bits
         for (int i = 0; i < n; i++) {
            codes.set(i, "0" + codes.get(i));
         }
         // append 1 to the front of the last n bits
         for (int x = n; x < (2 * n); x++) {
            codes.set(x, "1" + codes.get(x));
         }
         // set real graycodes to temp
         graycodes = codes;
      }
      //return the ArrayList of Gray codes;
      return graycodes;
   }
}
