/**
 * Created by Preston Shankle
 * CSC 205
 * Lab 9
 * 10/24/16
 */
import java.util.*;
public class mySums{
    public static void main(String[] args){
        System.out.println(iterativeSum(4));
        System.out.println(recurSum(4));
    }

    public static double iterativeSum(int n) {
        double x = 0;
        for (int i = 1; i <= n; i++) {
                x += Math.pow(2, i) + 1;
        }
        return x;
    }

    public static double recurSum(int n){
    if(n < 1)
        return 0;
    else
        return Math.pow(2,n) + 1 + recurSum(n - 1);
    }
}