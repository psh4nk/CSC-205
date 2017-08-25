/**
* Made by Preston Shankle
* CSC 205
* 9-2-2016
* Interactive clock class that counts time by seconds, minutes, and hours
*/

public class Clock {
    final static int HOURS_IN_DAY = 23,
                     MINUTES_IN_HOUR = 59,
                     SECONDS_IN_MINUTE = 59;

    private int hours, mins, secs;

    // initializes the time variables
    public Clock(int hoursIn, int minutesIn, int secondsIn ){

        hours = hoursIn;
        mins = minutesIn;
        secs = secondsIn;
    }

    // sets time to the default zero
    public Clock(){
        hours = 0;
        mins = 0;
        secs= 0;
    }

    // resets time to zero
    public void reset(){
        hours = 0;
        mins = 0;
        secs= 0;
    }

    // method that sets time given in parameters to zero
    public void reset(int hoursIn, int minutesIn, int secondsIn ){
        hours = hoursIn;
        mins = minutesIn;
        secs = secondsIn;
    }

    public void advance(){
        secs += 1;    // increment seconds
        if (secs > SECONDS_IN_MINUTE) {    // increment minutes, reset if seconds equals 60
            secs = 0;
            mins += 1;
        }
		if(mins > MINUTES_IN_HOUR) {    // increment hours, reset if seconds equals 60
                mins = 0;
                hours += 1;
        }
        if(hours > HOURS_IN_DAY){    // reset hours if hours equals 24
            hours = 0;
        }
    }

    // returns String form of time in h:m:s format
    public String toString(){
        return hours + ":" + mins + ":" + secs;

    }

}

