/**
 * Created by Preston Shankle
 * CSC 205
 * 12/6/2016
 * Job Queue Class
 */

import java.util.*;
import java.io.*;

public class TimeShare{
    public TimeShare(){}
    private static final int[] clocks = new int[2];

    public static void main (String[] args)throws IOException, CloneNotSupportedException{
        Queue inputQueue = new Queue();
        Queue finishQueue = new Queue();
        buildQueue(args, inputQueue);
        jobProcessor(inputQueue, finishQueue, clocks);
    }

    private static void buildQueue(String[] args, Queue inputQueue)throws IOException{
        /**
         * void method that is passed the String array args
         * and the empty queue inputQueue. inputQueue is
         * enqueued with all jobs from the file sent in from the
         * command line.
         */
        if(args.length == 0){
            System.out.println("Error : Filename Not Provided");
            System.exit(-1);
        }
        FileReader fileIn = new FileReader(args[0]);
        BufferedReader in = new BufferedReader(fileIn);
        Job job1;

        while(true){
            String jobInfo = in.readLine();
            if (jobInfo == null)
                return;
            job1 = jobParser(jobInfo);
            inputQueue.enqueue(job1);
        }
    }

    private static void jobProcessor(Queue inputQueue, Queue finishQueue, int[] clocks)throws CloneNotSupportedException{
        /**
         * void method that is passed the previously filled
         * inputQueue, an empty Queue finishQueue, and an integer
         * array named clocks. Integer clocks mainClock, idleClock, usageClock,
         * are declared along with the Queue jobQueue. jobQueue is queued with the
         * front Job of inputQueue, 'finished' Jobs from jobQueue are queued into finishQueue.
         * Method ends with both job & input queues empty, clock integers are saved into
         * the final int array clocks.
         */
        int mainClock = 1, idleClock = 0, usageClock = 0;
        Queue jobQueue = new Queue();

        do {

            if (!inputQueue.isEmpty()) {
                Job job1 = (Job) inputQueue.front();
                if (job1.arrivalTime == mainClock) {
                    jobQueue.enqueue(job1);
                    inputQueue.dequeue();
                }
            }

            if (!jobQueue.isEmpty()) {
                Job job2 = (Job) jobQueue.front();
                if (job2.startTime != -1 && mainClock - job2.startTime == job2.runTime) {
                    job2.turnTime = mainClock - job2.arrivalTime;
                    finishQueue.enqueue(job2);
                    jobQueue.dequeue();
                }

            }

            if (!jobQueue.isEmpty()) {
                Job job3 = (Job) jobQueue.front();
                if (job3.arrivalTime <= mainClock && !jobStarted(jobQueue, job3)) {
                    ((Job) jobQueue.front()).startTime = mainClock;
                    ((Job) jobQueue.front()).waitTime = mainClock - job3.arrivalTime;

                }
            }

            if (!inputQueue.isEmpty() && jobQueue.isEmpty())
                idleClock++;
            else if (!jobQueue.isEmpty())
                usageClock++;
            mainClock++;
        } while(!inputQueue.isEmpty() || !jobQueue.isEmpty());

        clocks[0] = idleClock;
        clocks[1] = usageClock;
        analysisPrint(finishQueue, clocks[0], clocks[1]);
    }

    public static Job jobParser(String jobInfo) throws IOException{
        /**
         * Job returning method that takes in the String jobInfo
         * from the buildQueue method, which is one entire line from
         * the file saved in String array args. StringTokenizer class is
         * used to parse the lines and the name, arrivalTime, and runTime
         * are all saved into a new Job which is being returned.
         */
        String name;
        int arrivalTime, runTime;
        StringTokenizer tokenizer = new StringTokenizer(jobInfo);
        name = tokenizer.nextToken();
        arrivalTime = Integer.parseInt(tokenizer.nextToken());
        runTime = Integer.parseInt(tokenizer.nextToken());
        return new Job(name, arrivalTime, runTime);
    }

    private static boolean jobStarted(Queue inputQueue, Job testJob)throws CloneNotSupportedException{
        /**
         * Boolean returning method that takes in a queue
         * inputQueue and Job testJob. A clone Queue of inputQueue
         * is created called temp. If the Job is found in the queue
         * and has begun, the method returns true.
         */
        Queue temp = (Queue) inputQueue.clone();
        while(!temp.isEmpty()){
            String jobName = ((Job) temp.front()).jobName;
            if((jobName.equals(testJob.jobName)) && (testJob.startTime != -1))
                return true;
            temp.dequeue();
        }
        return false;
    }

    private static void analysisPrint(Queue inputQueue, int idleClock, int usageClock) throws CloneNotSupportedException{
        /**
         * void method that takes in the inputQueue and
         * integers idleClock and jobCount. Clones of inputQueue
         * temp and temp2 are created. temp2 is used to calculate
         * values for jobCount and TWaitTime (total wait time).
         * Information about each Job are printed using the Job class's toString() method.
         * Avg wait time is calculated by dividing total wait
         * time by job count. CPU usage and CPU idle were stored
         * in clocks array slots 0 and 1, filled in jobProcessor method, and stored
         * as integers idleClock and usageClock. Finally, CPU usage is the usageClock
         * over the usageClock plus the idleClock times 10000. The CPU usage is then entirely
         * divided by 100 to give the resulting accurate double.
         *
         */
        int jobCount = 0, TWaitTime = 0;
        Queue temp = (Queue) inputQueue.clone();
        Queue temp2 = (Queue) inputQueue.clone();
        while (!temp2.isEmpty()) {
            Job tempJob2 = (Job) temp2.front();
            TWaitTime+= tempJob2.waitTime;
            jobCount++;
            temp2.dequeue();
        }
        System.out.println();
        System.out.println("\tJob Control Analysis : Summary Report");
        System.out.println("\t-------------------------------------");
        System.out.println();
        System.out.println("\tjob id\tarrival\tstart\trun\twait\tturnaround\t");
        System.out.println("\t      \ttime   \ttime \ttime\ttime\ttime      \t");
        System.out.println("\t------\t-------\t-----\t----\t----\t----------");

        while (!temp.isEmpty()) {
            Job tempJob = (Job) temp.front();
            System.out.println("\t" + tempJob.toString());
            temp.dequeue();
        }

        System.out.println("\t\tAverage Wait Time => " + (double) Math.round( TWaitTime / (double) jobCount * 100) / 100);
        System.out.println("\t\tCPU Usage => " + (double) usageClock);
        System.out.println("\t\tCPU Idle => " + (double) idleClock);
        System.out.println("\t\tCPU Usage (%) => " + (double) Math.round((double) usageClock / (double) (usageClock + idleClock) * 10000) / 100 + "%\n");
    }



}
