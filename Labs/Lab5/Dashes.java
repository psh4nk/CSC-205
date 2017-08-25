/**
 * Edited by Preston Shankle
 * CSC 205
 * 9/23/16
 */

public class Dashes {
	public static void main(String[] args) {
		String word = "Two-Thousand-And-Sixteen";
		System.out.println(removeDashes(word));
	}

	private static String removeDashes(String word) {
		StringBuffer buffer = new StringBuffer(word);
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == '-')
				buffer.replace(i, i+1, " ");
		}


		return buffer.toString();

	}
}