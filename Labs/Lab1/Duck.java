/**
 * Made by Preston Shankle
 * 4/9/2016
 * CSC 205
 * Duck class: reads a text file and determines if whether a duck,
 * duckling or neither.
 */

import java.util.*;
import java.io.*;

public class Duck {
    public static void main(String[] args) {

        String[] list = new String[5];
        Initialize(list);

        for (int i = 0; i < list.length; i++) {
            if (duckCheck(list[i]) && !ducklingCheck(list[i])) System.out.println("Line " + (i + 1) + " is a duck");
            else if (duckCheck(list[i]) && ducklingCheck(list[i])) System.out.println("Line " + (i + 1) + " is a duckling");
            else System.out.println("Line " + (i + 1) + " is not a duck");
        }
    }

    private static int Initialize(String[] list) {
        //post : List is initialized with all strings from file.

        String filename, lineInput;
        int i = 0, numItems = 0;
        try {
            System.out.print("Input File : ");
            Scanner stdin = new Scanner(System.in);
            filename = stdin.nextLine();
            stdin = new Scanner(new File(filename));

            while ((stdin.hasNext()) && (i < list.length)) {
                lineInput = stdin.nextLine();
                list[i] = lineInput;
                i++;
            }
            numItems = i;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return numItems;
    }

    private static boolean duckCheck(String isDuck) {

        return isDuck.contains("waddle") &&
                isDuck.contains("swim") &&
                isDuck.contains("quack");
    }

    private static boolean ducklingCheck(String isDuck) {

        return !isDuck.contains("Waddle") &&
                !isDuck.contains("Swim") &&
                !isDuck.contains("Quack");
    }
}

