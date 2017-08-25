/**
 * Made by Preston Shankle
 * CSC 205
 * 9/3/2016
 * MagicSquares class: Reads in a 3x3 square of integers and tests
 * for Magic-Squareness (all sums of rows/columns/diagonals must be equal)
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MagicSquare {

    private static final int SIZE = 3;

    public static void main(String[] args) {
        int[][] list = new int[SIZE][SIZE];
        int numItems;

        Initialize (list);

        if(Check(list)) System.out.println("This is a magic square ╚═( ͡° ͜ʖ ͡°)═╝");
        else System.out.println("This is not a magic square ( ͡°_ʖ ͡°)");
    }

    private static void Initialize(int[][] list) {
        //post : List is initialized with all integers from file.

        String filename;
        int numInput;
        try {
            System.out.print("Input File : ");
            Scanner stdin = new Scanner(System.in);
            filename = stdin.nextLine();
            stdin = new Scanner(new File(filename));

            for(int k = 0; k < list.length; k++) {
                for (int j = 0; j < list.length; j++) {
                    numInput = stdin.nextInt();
                    list[k][j] = numInput;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static boolean Check(int[][] magicSquare) {

        // Check sum of each row
        for (int i = 0; i < magicSquare.length; i++) {
            int hsum = 0;
            for (int j = 0; j < magicSquare.length; j++) {
                hsum += magicSquare[i][j];
            }
            if (hsum != 15) return false;  // 15 is the magic constant for a 3x3 magic square
        }

        // Check sum of each column
        for (int j = 0; j < magicSquare.length; j++) {
            int vsum = 0;
            for (int i = 0; i < magicSquare.length; i++)
                vsum += magicSquare[i][j];
            if (vsum != 15) return false;
        }

        //Check first diagonal sum
        if (magicSquare[0][0] + magicSquare[1][1] + magicSquare[2][2] != 15) return false;

        //Check second diagonal sum
        if (magicSquare[0][2] + magicSquare[1][1] + magicSquare[2][0] != 15) return false;

        return true;
    }
}