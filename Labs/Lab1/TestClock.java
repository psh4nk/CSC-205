/**
* Made by Preston Shankle
* CSC 205
* 9-2-2016
* Clock Class Tester: tests out the Clock class by
* allowing a user to set the time on his/her "DVR"
*/

import java.util.*;

public class TestClock {
    public static void main (String[] args) {
        Clock dvr = new Clock();
        int hours, minutes, seconds;
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter the time you would like to set your DVR to:");
        System.out.print("Hours: ");
        hours = input.nextInt();
        System.out.print("Minutes: ");
        minutes = input.nextInt();
        System.out.print("Seconds: ");
        seconds = input.nextInt();

        dvr.reset(hours,minutes,seconds);                          // Reset clock to given time
        System.out.println();
        System.out.println("The time is now: " + dvr.toString());  // Print given time
        System.out.println();
        System.out.println("The time is being advanced...");
        System.out.println();
        dvr.advance();                                             // Advance clock by one second
        System.out.println("The time is now: " + dvr.toString());  // Print advanced time
    }
}
