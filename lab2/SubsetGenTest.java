import java.util.ArrayList;

/**
   This program demonstrates the permutation generator.
*/
public class SubsetGenTest
{
   public static void main(String[] args)   {
   
      // SubsetGen generator = new SubsetGen();

     ArrayList<String> answer = new ArrayList<String>();
     answer.add("");
     answer.add("a"); 
     answer.add("b"); 
     answer.add("ab"); 
     answer.add("c");
     answer.add("ac"); 
     answer.add("bc"); 
     answer.add("abc");  
     
     SubsetGen generator = new SubsetGen();
     ArrayList<String> subsets = generator.getSubsets("abc");

      boolean match = true;
      int i = 0; 
		while ((match == true)&&(i<8))   {         
         if (!answer.get(i).equals(subsets.get(i)))
            match = false;
         i++;
      }
      if (match == true)
         System.out.println("Congratulations all the strings match." );
      else
         System.out.println("your string at position  " + i  + " should be -" + answer.get(i-1)+ "- you returned -" + subsets.get(i-1)+"-");

      // tests for getGrayCode

      // base case test
      ArrayList<String> ganswer1 = new ArrayList<String>();
      ganswer1.add("0");
      ganswer1.add("1");
      
      ArrayList<String> gcode1 = generator.getGrayCode(1);

      match = true;
      i = 0;
      while ((match == true) && (i < 2)) {
         if (!ganswer1.get(i).equals(gcode1.get(i))) {
           match = false;
         }
         i++;
      }
      if (match == true) {
         System.out.println("All strings match for greycode 1");
      }
      else {
         System.out.println("your string at position " + i + " should be -" + ganswer1.get(i-1) + "- you returned -" + gcode1.get(i-1) + "-");
      }

      // two bit
      ArrayList<String> ganswer2 = new ArrayList<String>();
      ganswer2.add("00");
      ganswer2.add("01");
      ganswer2.add("10");
      ganswer2.add("11");
      
      ArrayList<String> gcode2 = generator.getGrayCode(2);

      match = true;
      i = 0;
      while ((match == true) && (i < 2)) {
         if (!ganswer2.get(i).equals(gcode2.get(i))) {
           match = false;
         }
         i++;
      }
      if (match == true) {
         System.out.println("All strings match for greycode 2");
      }
      else {
         System.out.println("your string at position " + i + " should be -" + ganswer1.get(i-1) + "- you returned -" + gcode1.get(i-1) + "-");
      }

      // three bit
      ArrayList<String> ganswer3 = new ArrayList<String>();
      ganswer3.add("000");
      ganswer3.add("001");
      ganswer3.add("010");
      ganswer3.add("011");
      ganswer3.add("100");
      ganswer3.add("101");
      ganswer3.add("110");
      ganswer3.add("111");
      
      ArrayList<String> gcode3 = generator.getGrayCode(3);

      match = true;
      i = 0;
      while ((match == true) && (i < 2)) {
         if (!ganswer3.get(i).equals(gcode3.get(i))) {
           match = false;
         }
         i++;
      }
      if (match == true) {
         System.out.println("All strings match for greycode 3");
      }
      else {
         System.out.println("your string at position " + i + " should be -" + ganswer1.get(i-1) + "- you returned -" + gcode1.get(i-1) + "-");
      }
   }
}

