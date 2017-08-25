import java.util.*;

public class Employee extends Person{

    private static double salary;
    private static Date hired;

    public Employee(String firstName, String lastName, double salary, Date birth, Date hired){
        super(firstName, lastName, birth);
        this.salary = salary;
        this.hired = hired;
    }

    public void setSalary(double salary){
        salary = this.salary;
        }

    public void setDate(Date hired){
        this.hired = hired;
        }

    public double getSalary(double salary){
        return salary;
        }

    public Date getDate(Date hired){
        return hired;
        }

    public String toString(){
        return "name = " + getLastName() + ", " + getFirstName() + "\n"
                + "salary = " + salary + "\n"
                + "birth = " + getBirthDate() + "\n"
                + "hired = " + hired;
    }
}
