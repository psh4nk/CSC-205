
public class Puzzle
{
	public static void main(String[] args){
		puzzle(9);
	}

	private static int puzzle (int n) {
		int ans = 0;
		for (int i = 1; i <= 4 - n; i++) System.out.print("\t");
		if ((n % 3) == 2) {
			ans = 1;
			for (int i = 1; i <= 4 - n; i++) System.out.print("\t");
			System.out.println("puzzle(" + n + ")" + " = returns " + ans);
			System.out.println("\t\tpuzzle(" + (n-1) + ")" + " = returns " + (ans+=2));
			System.out.println("\tpuzzle(" + (n+1) + ")" + " = returns " + (ans+=1));
			System.out.println("puzzle(" + (n+7) + ")" + " = returns " + (ans+=1));
			return 1;
		}
		else if ((n % 3) == 1) {
			ans = (n + 1);
			System.out.println("puzzle(" + n + ")" + " = puzzle(" + ans + ") + " + 2);
			return (puzzle(n + 1) + 2);
		}
		else {
			ans = (n / 3);
			System.out.println("puzzle(" + n + ")" + " = " + "puzzle(" + ans + ")" + " + " + 1);
			return (puzzle(n / 3) + 1);
		}


	}
}
