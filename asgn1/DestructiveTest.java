import java.util.*;
import java.lang.*;
import java.io.*;

public class DestructiveTest
{
	public ArrayList<Integer> findHighestSafeRung(int height, int safest)
	{
		// initialize output list
		ArrayList<Integer> outputs = new ArrayList<Integer>();
		outputs.add(height);
		outputs.add(safest);
		outputs.add(-1);
		outputs.add(0);
		outputs.add(-1);
		outputs.add(-1);
		outputs.add(-1);
		outputs.add(-1);
		outputs.add(-1);

		// find square root of height
		int sqroot = (int)Math.round(Math.sqrt(height));
		int root_height = 1;

		// divide ladder into sqroot number of sections
		// and drop at the top of each section until device one breaks
		while (outputs.get(7) == -1) {
			if (root_height == sqroot) {
				// drop from highest rung if last section (due to rounding)
				outputs = drop(height, safest, outputs);
				root_height++;
				if (outputs.get(7) == -1) {
					outputs.set(2, height);
					return outputs;
				}
			}
			else {
				// drop from highest point in each section
				outputs = drop(sqroot * root_height, safest, outputs);
				root_height++;
			}
		}

		// start dropping from the bottom rung of section first device broke on
		int sectionheight = (sqroot * (root_height - 2)) + 1;

		// drop up the ladder linearly until second device breaks
		while (outputs.get(8) == -1) {
			outputs = drop(sectionheight, safest, outputs);
			sectionheight++;
		}

                // after second device breaks rung below is the highest safest rung
		outputs.set(2, sectionheight - 2);
		return outputs;
	}

	// height is the drop height
	public ArrayList<Integer> drop(int height, int safest, ArrayList<Integer> data)
	{
		// recording the first three drop heights
		if (data.get(4) == -1)
		{
			// set index 4 to drop height if first drop
			data.set(4, height);
		}
		else if (data.get(4) != -1 && data.get(5) == -1)
		{
			// set index 5 to drop height if second drop
			data.set(5, height);
		}
		else if (data.get(4) != -1 && data.get(5) != -1 && data.get(6) == -1)
		{
			// set index 6 to drop height if third drop
			data.set(6, height);
		}

		// check if device breaks
		if (height > safest)
		{
			// record breaks
			if (data.get(7) == -1)
			{
				// set index 7 to height of first break
				data.set(7, height);
			}
			else if (data.get(7) != -1 && data.get(8) == -1)
			{
				// set index 8 to height of second break
				data.set(8, height);
			}
		}

		// increment drop counter
		data.set(3, (data.get(3) + 1));

		return data;
	}
}
