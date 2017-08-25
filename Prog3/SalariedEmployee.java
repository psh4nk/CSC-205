/**
 * Created by Preston Shankle
 * CSC 205
 * 10/25/2016
 * SalariedEmployee Class -- Child of the Employee Class
 */

import java.io.*;

public class SalariedEmployee extends Employee implements Serializable {
    public SalariedEmployee(String name, double salary) {
        /**
         * Constructor method that accesses the parent
         * Employee class to create a new SalariedEmployee.
         */
        super(name, salary / 52 / 40);
    }

    public double getSalary() {
        /**
         * Returns the "Wage" of the employee,
         * but first it converts it into a salary.
         */
        return super.getWage() * 40 * 52;
    }

    public void setSalary(double salary) {
        /**
         * Void method that sets the Employee's
         * salary by accessing the Employee class
         * setWage method.
         */
        super.setWage(salary / 52 / 40);
    }

    public String computePay(double hourlyWage) {
        /**
         * String returning method that
         * calculated the pay that the
         * Employee should receive. It's
         * always just the salary / 52.
         */
        double salaryAnnual = this.getSalary() / 52;
        toDollars(salaryAnnual);
        String salaryOutput = toDollars(salaryAnnual);
        return salaryOutput;
    }

    public String toString() {
        /**
         * String returning method that
         * implements the Object class's
         * toString method and adds some
         * Employeeistic extras to it.
         */
        String salaryOutput = toDollars(this.getSalary());
        toDollars(this.getSalary());
        int pay = 40 - super.getName().length() - salaryOutput.length() - "$/year".length();
        String name = super.getName();

        for (int i = 0; i <= pay; i++) {
            name = name + " ";
        }

        return name + "$" + salaryOutput + "/year";
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
