/**
* Made by Preston Shankle
* 9/10/16
* CSC 205
 * Stats Class: Returns mathematical attributes
 * of a user defined integer array
*/

import java.util.*;

public class Stats
{
	private static final int MAX_SIZE = 100;
	public static void main(String[] args)
	{
        	int[] List = new int[MAX_SIZE];		

		int numItems;

        	numItems = fillUp (List);
		System.out.println("\n\10\7" + " The range of your " +
                                   numItems + " items is :  " + 
				   range (List, numItems));

        	System.out.println("\n\10\7" + " The mean of your " + 
                                   numItems + " items is  :  " +
				   mean (List, numItems));
	}

	public static int fillUp(int[] numList){
		int i = 0, intInput = 0;
		Scanner input = new Scanner(System.in);
		System.out.println("Input your integer values (Enter a 0 to stop): ");
		do {
			intInput = input.nextInt();
			numList[i] = intInput;
			i++;
			if(intInput == 0 || i >= MAX_SIZE) break;
	    }
		while ((input.hasNextInt()) && (i < numList.length));
		return i-1;
	}

	public static int mean(int[] numList, int numItems){
		int j = 0;
	    for(int i = 0; i < numList.length; i++){
		    if(numList[i] != 0)
		        numList[j++] = numList[i];
        }
        int [] newNumList = new int[j];
        System.arraycopy(numList, 0, newNumList, 0, j);


	    Arrays.sort(newNumList, 0, numItems);
		int meanNum = newNumList[numItems/2];
		return meanNum;
	}

	public static int range(int[]numList, int numItems){
        int j = 0;
        for(int i = 0; i < numList.length; i++){
            if(numList[i] != 0)
                numList[j++] = numList[i];
        }
        int [] newNumList = new int[j];
        System.arraycopy(numList, 0, newNumList, 0, j);

        Arrays.sort(newNumList, 0, numItems);
        int range = newNumList[numItems-1] - newNumList[0];
        return range;

	}
}
