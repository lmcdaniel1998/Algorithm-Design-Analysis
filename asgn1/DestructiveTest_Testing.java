import java.util.*;
import java.lang.*;
import java.io.*;

public class DestructiveTest_Testing
{
	public static void main(String[] args)
	{

		DestructiveTest generator = new DestructiveTest();

		int height = 100;

		ArrayList<Integer> answers = generator.findHighestSafeRung(height, height - 3);

		if (answers.get(1).equals(answers.get(2)))
		{
			System.out.println("found correct drop height for (h - 3) with drops: " + answers.get(3));
		}
		else
		{
			System.out.println("Incorrect Drop Height");
		}
		
		answers = generator.findHighestSafeRung(height, (height / 2) - 2);

		if (answers.get(1).equals(answers.get(2)))
		{
			System.out.println("found correct drop height for (h/2 - 2) with drops: " + answers.get(3));
		}
		else
		{
			System.out.println("Incorrect Drop Height");
		}
		
		answers = generator.findHighestSafeRung(height, 2);

		if (answers.get(1).equals(answers.get(2)))
		{
			System.out.println("found correct drop height for 2 with drops: " + answers.get(3));
		}
		else
		{
			System.out.println("Incorrect Drop Height");
		}
	}
}
