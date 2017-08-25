public class TestEmployee{

    public static void main (String[] args){
        Date hireDate = new Date();
        Date birthDate = new Date(1980, 3, 8);
        Employee morris = new Employee("Morris", "Brown", 40000.0, birthDate, hireDate);
        System.out.println(morris);
        }
}