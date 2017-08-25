/**
 * Made by Preston Shankle
 * CSC 205
 * 9/16/2016
 * Bingo Class
 */

import java.io.*;
import java.util.*;

public class Bingo {
    private static int total;
    private static final int SIZE = 5;
    private static int direction = 0;

    public static void main(String[] args) {
        int[][] card = new int[SIZE][SIZE];
        fillCard(card);
        printCard(card);
        playGame(card);
        checkForDirection(card);
        directionSwitch();
    }

    private static void fillCard(int[][] list) {
        /**
         * Void method that is passed array list,
         * initialized in main. Array list is
         * generated using the input from the
         * bingo.in file included in the source
         * directory.
         */

        String filename;
        int numInput;
        try {
            Scanner stdin = new Scanner(System.in);
            filename = "bingo.in";
            stdin = new Scanner(new File(filename));

            for (int k = 0; k < list.length; k++) { // Read in values from bingo.in, insert into bingo card
                for (int j = 0; j < list.length; j++) {
                    numInput = stdin.nextInt();
                    list[k][j] = numInput;
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printCard(int[][] card) {
        /**
         * Void method that prints the bingo card in
         * its original, unmodified state. Card is the
         * previously generated (from bingo.in) array
         * that is printed to the user's screen.
         */

        Arrays.asList(card);
        System.out.println("YOUR BINGO CARD:");
        String bingo[] = {"B", "I", "N", "G", "O"};
        System.out.print("\t");
        for (int i = 0; i < bingo.length; i++)
            System.out.print(bingo[i] + "\t\t");
        System.out.println();

        for (int i = 0; i < card.length; i++) {
            System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
            for (int j = 0; j < card[i].length; j++) { // Print values of bingo card
                System.out.print("|      " + card[i][j] + "\t");
            }
            System.out.print("|");
            System.out.println();

        }
    }

    private static void playGame(int[][] card) {
        /**
         * Void method that is passed array card,
         * initializes the ArrayList 'bin,'
         * and generates random values that are used to
         * fill out the bingo card. Bin is printed along with
         * number of random integers it took to win the game.
         */

        int count = 0, a = 0;
        ArrayList<Integer> bin = new ArrayList<Integer>();
        System.out.println();
        System.out.println("BINGO NUMBERS PICKED AT RANDOM: ");
        while ((!checkForDirection(card)) /*&& count != 500*/) {
            if (direction != 0) break;
            count++;
            total++;
            if (!checkForDirection(card)) bin.add((int) Math.round((Math.random() * 74 + 1)));
            if (count == 20) {
                System.out.println();
                count = 0;
            }

            for (int b = 0; b < bin.size(); b++)
                for (int i = 0; i < card.length; i++)
                    for (int j = 0; j < card.length; j++) {
                        if (bin.get(b).equals(card[i][j])) card[i][j] = 0;
                    }


            System.out.print(" " + bin.get(a).toString() + " ");

            a++;

        }
        System.out.println();
        System.out.println();
        if (checkForDirection(card)) System.out.println("You have won after " + total + " picks!");
        System.out.println();

        checkForWin(card, bin);

    }


    private static boolean checkForWin(int[][] card, ArrayList<Integer> bin) {
        /**
         * Returns a boolean value determining whether a general win
         * has been achieved. Array card and arrayList bin are evaluated
         * to determine equality of each element. For each equal element,
         * a string "X" displaces the current card integer. New card
         * showing matches is printed.
         */

        boolean win = false;
        int count = 0;
        while (!win && count != 10) {
            count++;
            for (int a = 0; a < bin.size(); a++)
                for (int i = 0; i < card.length; i++)
                    for (int j = 0; j < card.length; j++) {
                        if (bin.get(a).equals(card[i][j])) win = true;
                    }
        }
        String bingo[] = {"B", "I", "N", "G", "O"};
        System.out.print("\t");
        for (int i = 0; i < bingo.length; i++)
            System.out.print(bingo[i] + "\t\t");
        System.out.println();
        if (checkForDirection(card)) {
            for (int i = 0; i < card.length; i++) {
                System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
                for (int j = 0; j < card[i].length; j++) { // Print values of bingo card
                    if (card[i][j] != 0) System.out.print("|      " + card[i][j] + "\t");
                    else if (card[i][j] == 0) System.out.print("|      " + "X" + "\t");
                }
                System.out.print("|");
                System.out.println();

            }
           System.out.println();
        }
        return win;
    }

    private static boolean checkForDirection(int[][] card) {
        /**
         * Returns boolean value determining whether directional win
         * has been achieved. Array card is evaluated for directional
         * win. Global integer 'direction' is also set based on
         * the type of win.
         */

        boolean h = false, v = false, d1 = false, d2 = false;

        for (int i = 0; i < card.length; i++) {
            int hsum = 0;
            for (int j = 0; j < card.length; j++) {
                hsum += card[i][j];
            }
            if (hsum == 0) h = true;
            if (hsum == 0) direction = 1;
        }

        // Check sum of each column
        for (int j = 0; j < card.length; j++) {
            int vsum = 0;
            for (int i = 0; i < card.length; i++)
                vsum += card[i][j];
            if (vsum == 0) v = true;
            if (vsum == 0) direction = 2;
        }

        //Check first diagonal sum
        if (card[0][0] + card[1][1] + card[2][2] + card[3][3] + card[4][4] == 0) {
            d1 = true;
            direction = 3;
        }

        //Check second diagonal sum
        if (card[0][4] + card[1][3] + card[2][2] + card[3][1] + card[4][0] == 0) {
            d2 = true;
            direction = 4;
        }

        if (h || v || d1 || d2) return true;
        return false;
    }

    private static void directionSwitch() {
        /**
         * Uses global int 'direction'
         * (value is set in checkForDirection)
         * to print what type of win was achieved
         */

        switch (direction) {
            case 1:
                System.out.println("Horizontal Win!");
                break;
            case 2:
                System.out.println("Vertical Win!");
                break;
            case 3:
                System.out.println("Diagonal Win!");
                break;
            case 4:
                System.out.println("Diagonal Win!");
                break;
            default:
                break;
        }
        System.out.println();
    }
}