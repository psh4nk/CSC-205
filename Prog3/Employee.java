/**
 * Created by Preston Shankle
 * CSC 205
 * 10/25/2016
 * Employee Class -- Parent Class
 */

import java.io.*;

public abstract class Employee implements Serializable{
    private String name;
    private double hourlyWage;

    protected Employee(String name, double hourlyWage) {
        /**
         * Protected main constructor.
         * Takes in a name and wage, and builds an
         * Employee with them.
         */
        this.name = name;
        this.hourlyWage = hourlyWage;
    }

    public String getName() {
        /**
         * Public string returning method
         * that returns the
         * Employee's name
         */
        return this.name;
    }

    public double getWage() {
        /**
         * Public double returning method
         * that returns the
         * Employee's wage
         */
        return this.hourlyWage;
    }

    public void setName(String name) {
        /**
         * Void method that sets the
         * Employee up with a brand new name
         */
        this.name = name;
    }

    public void setWage(double hourlyWage) {
        /**
         * Void method that sets the
         * Employee's wage
         */
        this.hourlyWage = hourlyWage;
    }

    public abstract String computePay(double name);
        /**
        * Abstract method that child classes implement
        * to compute the pay of the Employee.
        */

    public boolean equals(Employee name) {
        /**
         * Boolean returning method that tells whether or
         * not one Employee's name equals another Employee's name
         */
        return this.name.equals(name.getName());
    }

    public void raise(double hourlyWage) {
        /**
         * Void method that takes in a double
         * wage and uses that to raise the wage.
         */
        this.hourlyWage *= (hourlyWage / 100) + 1;
    }
}
