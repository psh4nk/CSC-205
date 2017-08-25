/**
 * Created by Preston Shankle
 * CSC 205
 * 10/25/2016
 * HourlyEmployee Class -- Child of the Employee Class
 */
import java.io.*;

public class HourlyEmployee extends Employee implements Serializable {

    public HourlyEmployee(String name, double hourlyWage) {
        /**
         * The default constructor uses the parent
         * class to create the HourlyEmployee.
         * It can be said that the hourly employee
         * bares more resemblence to his parent
         * than his sibling does.
         */
        super(name, hourlyWage);
    }

    public String computePay(double hourlyWage) {
        /**
         * String returning method that computes
         * the pay of an employee using a sweet
         * ternary operator deal
         * and also uses the toDollars method
         * to put the bow on top.
         */
        double newWage;
        newWage = (hourlyWage > 40) ? (40 * super.getWage() + (hourlyWage - 40) * super.getWage() * 1.5) : (hourlyWage * super.getWage());
        String output = "" + toDollars(newWage);
        return output;
    }

    public String toString() {
        /**
         * String returning method that
         * implements the Object class's
         * toString method and adds class-specific
         * modifications to it.
         */
        String hourlyOutput = toDollars(this.getWage());
        int pay = 40 - super.getName().length() - hourlyOutput.length() - "$/year".length();
        String name = super.getName();
        for(int i = 0; i <= pay; i++) {
            name = name + " ";
        }

        return name + "$" + hourlyOutput + "/hour";
    }

    public static String toDollars(double amount) {
        /**
         * String returning method that makes
         * the pay/wage/salary output make more
         * sense to the user. Either rounds the
         * amount to two dec places or adds a '.00'
         */
        long roundedAmount = Math.round(amount * 100);
        long dollars = roundedAmount / 100;
        long cents = roundedAmount % 100;

        if (cents <= 9)
            return dollars + ".0" + cents;
        else
            return dollars + "." + cents;
    }
}
