/**
 * Created by Preston Shankle
 * CSC 205
 * 10/25/2016
 * Personnel Class
 */

import java.util.*;
import java.io.*;

/**
 *                                    Personnel Class
 * Class that serves as a driver for adding and managing hourly and salaried employees from an employee database
 *                             which is created upon runtime.
 */

public class Personnel {
    private static int numEmployees = 0;
    private static ArrayList<Employee> EmployeesList = new ArrayList(1);
    public static void main(String[] args){
        clearScreen();
        selection();
    }

    private static void selection(){
        /**
         * Void method that allows the user
         * to select from the menu which part
         * of the program they would like to interact with.
         */
        switch(printMenu()) {
            case "n":
                createEmployee();
                break;
            case "N":
                createEmployee();
                break;
            case "c":
                checks();
                break;
            case "C":
                checks();
                break;
            case "r":
                raise();
                break;
            case "R":
                raise();
                break;
            case "p":
                printWages();
                break;
            case "P":
                printWages();
                break;
            case "d":
                download();
                break;
            case "D":
                download();
                break;
            case "u":
                upload();
            case "U":
                upload();
            case "m":
                modifyEmployee();
            case "M":
                modifyEmployee();
            case "e":
                eraseFile();
            case "E":
                eraseFile();
            case "a":
                deleteEmployee();
            case "A":
                deleteEmployee();
            case "q":
                clearScreen();
                System.exit(0);
            case "Q":
                clearScreen();
                System.exit(0);
            default:
                error();
                break;
        }
    }
    
    private static String printMenu() {
        /**
         * String returning method that prints the menu
         * to the screen and takes in the user's input
         * for the selection method.
         */
        Scanner input = new Scanner(System.in);
        String selection;
        System.out.println("---------------------------------------------");
        System.out.println("|Commands: n - New employee                 |");
        System.out.println("|          c - Compute paychecks            |");
        System.out.println("|          r - Raise wages                  |");
        System.out.println("|          p - Print records                |");
        System.out.println("|          d - Download data                |");
        System.out.println("|          u - Upload data                  |");
        System.out.println("|          m - Modify employee              |");
        System.out.println("|          e - Erase data file              |");
        System.out.println("|          a - Delete employee              |");
        System.out.println("|          q - Quit                         |");
        System.out.println("---------------------------------------------");
        System.out.println("Enter Command: ");
        selection = input.next();
        return selection;
    }

    private static void createEmployee() {
        /**
         * Void method that allows the user to
         * define the characteristics of the new
         * employee, including the name and wage.
         * This method also allows the user to
         * input whether the employee will be
         * an hourly or a salaried employee.
         */
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the new employee: ");
        String employeeName = input.next();
        System.out.println("Hourly (h) or salaried (s): ");
        String salarySelection = input.next();
        switch(salarySelection) {
            case "h":
                newHourlyEmployee(employeeName);
                selection();
                break;
            case "H":
                newHourlyEmployee(employeeName);
                selection();
                break;
            case "s":
                newSalariedEmployee(employeeName);
                selection();
                break;
            case "S":
                newSalariedEmployee(employeeName);
                selection();
                break;
            default:
                System.out.println("Input was not h or s;  please try again.");
                selection();
        }
    }

    public static void newHourlyEmployee(String name) {
        /**
         * Void method that is passed a String "name."
         * This method is an extension from the createEmployee
         * method that allows the user to specifically create a
         * new hourly employee.
         */
        Scanner input = new Scanner(System.in);
        double hourlyWage = 0;
        System.out.println("Enter hourly wage:  ");
       try {
           hourlyWage = input.nextDouble();
           EmployeesList.add(numEmployees, new HourlyEmployee(name, hourlyWage));
           ++numEmployees;
       }
       catch(InputMismatchException e) {
           System.out.println("Input not accepted");
       }
    }

    public static void newSalariedEmployee(String name) {
        /**
         * Void method that is passed a String "name."
         * This method is an extension from the createEmployee
         * method that allows the user to specifically create a
         * new salaried employee.
         */
        Scanner input = new Scanner(System.in);
        double hourlyWage = 0;
        System.out.println("Enter annual salary:  ");
        try {
            hourlyWage = input.nextDouble();
            EmployeesList.add(numEmployees, new SalariedEmployee(name, hourlyWage));
            ++numEmployees;
        }
        catch(InputMismatchException e) {
            System.out.println("Input not accepted");
        }
        }

    public static void checks() {
        /**
         * Void method that uses the respective salaried
         * employee or hourly employee class's computePay
         * method to calculate the amount of pay that the
         * employee should receive.
         */
        Scanner input = new Scanner(System.in);
        double hours = 0;
        if(numEmployees > 0) {
            for (int i = 0; i < numEmployees; i++) {
                System.out.println("Enter number of hours worked by " + EmployeesList.get(i).getName() + ":  ");
                hours = input.nextDouble();
                System.out.println("Pay:  $" + EmployeesList.get(i).computePay(hours));
            }
            selection();
        }
        else {
            System.out.println("No employees have been added. Please either upload or add employees.");
            selection();
        }
        }

    public static void raise() {
        /**
         * Void method that allows the user
         * to increase the pay of all users
         * by a certain percentage.
         */
        Scanner input = new Scanner(System.in);
        double raiseAmount = 0;
        if(numEmployees > 0) {
            System.out.println("Enter percentage increase:  ");
            try {
                raiseAmount = input.nextDouble();
                for (int i = 0; i < numEmployees; i++) {
                    EmployeesList.get(i).raise(raiseAmount);
                }
                System.out.println("New Wages:");
                printWages();
            }
            catch(InputMismatchException e){
                System.out.println("Input not accepted");
            }
        }
        else {
            System.out.println("No employees have been added. Please either upload or add employees.");
        }
        selection();
    }

    public static void printWages() {
        /**
         * Void method that prints the
         * name and wage of each employee
         * onto the screen.
         */
        if(numEmployees > 0 && !EmployeesList.isEmpty()) {
            for (int i = 0; i < numEmployees; i++) {
                System.out.println(EmployeesList.get(i));
            }
        }
        else
            System.out.println("No employees have been added. Please either upload or add employees.");
        selection();

    }

    public static void download() {
        /**
         * Void method that outputs a binary
         * file containing the objects stored
         * within the ArrayList EmployeeList.
         * File is stored in the local directory.
         */
        try {
            System.out.println("Now downloading these records...");
            FileOutputStream fileOut = new FileOutputStream("employee.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(EmployeesList);
            out.close();
        } catch (IOException e) {
            System.err.println("Error writing to employee.dat");
        }
        selection();
    }

    public static void upload() {
        /**
         * Void method that allows the user
         * to upload a previously downloaded file
         * back into the program.
         */
        try {
            FileInputStream fileIn = new FileInputStream("employee.dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ArrayList<Employee> input = (ArrayList<Employee>) in.readObject();
            compare(input);
        } catch (IOException e) {
            System.err.println("Error reading from employee.dat; check directory to see if file exists");
        } catch (ClassNotFoundException e) {
            System.err.println("Error reading from employee.dat; check directory to see if file exists " + e.getMessage());
        }
        selection();
    }

    public static void eraseFile(){
        /**
         * Void method that allows the user
         * to delete the employee.dat file
         * from the local directory.
         */
        Scanner input = new Scanner(System.in);
        System.out.println("Are you sure you wish to delete this file? Y/N:");
        String answer = input.next();
        switch (answer) {
            case "y":
                try {
                    File fileToErase = new File("employee.dat");
                    if (fileToErase.delete()) {
                        for(int i = 0; i < 3; i++) {
                            System.out.print(".");
                            Thread.sleep(1000);
                        }
                        System.out.println();
                        System.out.println("File " + fileToErase.getName() + " has been erased.");
                    } else if(fileToErase.exists() && fileToErase.canRead())
                        System.out.println("File " + fileToErase.getName() + " has not been erased.");
                } catch (Exception e) {
                    System.err.println("Error reading from employee.dat; check directory to see if file exists");
                }
            case "Y":
                try {

                    File fileToErase = new File("employee.dat");
                    if (fileToErase.delete()) {
                        for(int i = 0; i < 3; i++) {
                            System.out.print(".");
                            Thread.sleep(1000);
                        }
                        System.out.println();
                        System.out.println("File " + fileToErase.getName() + " has been erased.");
                    } else if(fileToErase.exists() && fileToErase.canRead())
                        System.out.println("File " + fileToErase.getName() + " has not been erased.");
                } catch (Exception e) {
                    System.err.println("Error reading from employee.dat; check directory to see if file exists");
                }
            default:
                selection();
        }
    }

    public static void deleteEmployee() {
        /**
         * Void method that allows the user to
         * delete a particular employee.
         */
        Scanner input = new Scanner(System.in);
        if(numEmployees > 0)
        System.out.println("Which employee would you like to delete?");
        else{
            System.out.println("No employees have been added. Please either upload or add employees.");
            selection();
        }
        for (int i = 0; i < numEmployees; i++)
            System.out.println(EmployeesList.get(i));
        String name = input.next();
        int i = search(name);
        EmployeesList.remove(i);
        numEmployees--;
        System.out.println("Employee has been removed.");
        EmployeesList.ensureCapacity(numEmployees);
        selection();
    }

    public static void modifyEmployee() {
        /**
         * Void method that uses a switch statement
         * to access other methods that allow the user
         * to modify the employee's name or wage.
         * Uses the printModificationMenu method to
         * take in the menu selection from the user.
         */
        Scanner input = new Scanner(System.in);
        System.out.println("Which employee would you like to modify?");
        for (int i = 0; i < numEmployees; i++)
            System.out.println(EmployeesList.get(i));
            String name = input.next();
        for (int i = 0; i < numEmployees; i++)
        if (EmployeesList.get(i).getName().compareTo(name) == 0) {
            System.out.println("What would you like to modify?");
            switch (printModificationMenu()) {
                case "w":
                    setNewWage(name, i);
                case "W":
                    setNewWage(name, i);
                case "i":
                    raiseSelectedWage(name, i);
                case "I":
                    raiseSelectedWage(name, i);
                case "n":
                    setNewName(name, i);
                case "N":
                    setNewName(name, i);
                case "q":
                    selection();
                case "Q":
                    selection();
                default:
                    modError();
                    break;
            }
        }
        else if(!EmployeesList.contains(name) && EmployeesList.get(i).getName().compareTo(name) != 0 && i > 1)
            System.out.println("Employee not found.");
        selection();
    }

    public static int search(String name){
        /**
         * Integer returning method that is mainly
         * required by the deleteEmployee method to find
         * a particular employee in the EmployeeList.
         * A name is put into the method, and the integer
         * location of the Employee is returned.
         */
        int foundIt = 0;
        for (int i = 0; i < numEmployees; i++)
            if (EmployeesList.get(i).getName().compareTo(name) == 0)
                foundIt = i;
            else if(i == numEmployees)
                System.out.println("Employee not found.");
        return foundIt;
    }

    public static void raiseSelectedWage(String name, int loc){
        /**
         * A void method that has the parameters String
         * name and integer loc. Allows the user to
         * increase one particular employee's pay by a
         * certain percentage.
         */
        Scanner input = new Scanner(System.in);
        System.out.println("Enter percentage increase: ");
        double raiseAmount = input.nextDouble();
        try {
            EmployeesList.get(loc).raise(raiseAmount);
            System.out.println("Wage successfully raised");
            System.out.println(EmployeesList.get(loc));
        } catch (InputMismatchException e) {
            System.out.println("Input not accepted");
        }
        selection();
    }

    public static void setNewWage(String name, int loc){
        /**
         * A void method that has the parameters String
         * name and integer loc. Allows the user to
         * completely change the employee's current wage.
         */
        Scanner input = new Scanner(System.in);
        System.out.println("Enter new wage: ");
        double newWage = input.nextDouble();
        if(EmployeesList.get(loc) instanceof HourlyEmployee){
            try {
                EmployeesList.get(loc).setWage(newWage);
                System.out.println("Wage successfully raised:");
                System.out.println(EmployeesList.get(loc));
            } catch (InputMismatchException e) {
                System.out.println("Input not accepted");
            }
        }
        else if(EmployeesList.get(loc) instanceof SalariedEmployee) {
            try {
                ((SalariedEmployee) EmployeesList.get(loc)).setSalary(newWage);
                System.out.println("Salary successfully raised:");
                System.out.println(EmployeesList.get(loc));
            } catch (InputMismatchException e) {
                System.out.println("Input not accepted");
            }
        }
        selection();
    }

    public static void setNewName(String name, int loc){
        /**
         * A void method that has the parameters String
         * name and integer loc. Allows the user to change
         * the Employee's name. Could be useful for a position
         * change or if someone changes marital status, etc.
         */
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the modified name: ");
        try {
            String newName = input.next();
            EmployeesList.get(loc).setName(newName);
            System.out.println("Name successfully changed.");
            System.out.println("New name: " + EmployeesList.get(loc).getName());
        } catch (InputMismatchException e) {
            System.out.println("Input not accepted.");
        }
        selection();
    }

    public static String printModificationMenu(){
        /**
         * String returning method that prints the
         * Modification menu to the screen and allows
         * the user to input their selection.
         */
        Scanner input = new Scanner(System.in);
        System.out.println("---------------------------------------------");
        System.out.println("|Commands: w - Wage                         |");
        System.out.println("|          i - Increase wage by percentage  |");
        System.out.println("|          n - Name                         |");
        System.out.println("|          q - Quit                         |");
        System.out.println("---------------------------------------------");
        System.out.println("Enter Command: ");
        String select = input.next();
        return select;
    }

    public static void compare(ArrayList<Employee> list) {
        /**
         * Void method that takes in an Employee
         * ArrayList "List" as a parameter. Used by
         * the upload method to check for any Employees
         * that may have been duplicated. If there is a duplicated
         * employee within the uploaded file, the employee is ignored.
         */
        int count = 0;
            for(int i = 0; i < list.size(); i++) {
                boolean eAlreadyExists = false;
                for(int j = 0; j < numEmployees; j++) {
                    if(list.get(i).equals(list.get(j))) {
                        eAlreadyExists = true;
                    }
                }
                if(!eAlreadyExists) {
                    EmployeesList.add(numEmployees, list.get(i));
                    count++;
                    numEmployees++;
                }
            }
        System.out.println(count + " new employees have been added.");
    }

    public static void clearScreen()
    /**
     * Void method that clears the screen using some
     *              funky characters.
     */
    {
        System.out.println("\u001b[H\u001b[2J");
    }

    public static void error() {
        /**
         * Tells the user whether what they're typing is
         * valid Personnel.java main menu syntax or not.
         */
        System.out.println("Command was not recognized;  please try again.");
        selection();
    }

    public static void modError() {
        /**
         * Tells the user whether what they're typing is
         * valid Personnel.java Modification Menu syntax or not.
         */
        System.out.println("Command was not recognized;  please try again.");
        modifyEmployee();
    }
}
