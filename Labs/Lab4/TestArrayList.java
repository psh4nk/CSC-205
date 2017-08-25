import java.util.*;
public class TestArrayList {
    public static void main(String[] args) {
        ArrayList<String> myArrayList = new ArrayList<String>(10);
        initArray(myArrayList);
        printArrayList(myArrayList);
        delete(myArrayList, "Java");
        System.out.println();
        printArrayList(myArrayList);
        System.out.println("\"C++\" is listed: " + count(myArrayList, "C++") + " times");
    }

    private static void initArray(ArrayList<String> myArrayList) {
        myArrayList.add("Python");
        for (int i = 0; i < 5; i++) myArrayList.add("Java");
        for (int i = 0; i < 4; i++) myArrayList.add("C++");
    }

    private static void printArrayList(ArrayList<String> myArrayList) {
        // Pre  : myArrayList has been initialized
        // Post : The elements of Vector v are printed to the screen on separate lines

        for (int i = 0; i < myArrayList.size(); i++) {
            System.out.println(myArrayList.get(i));
        }


    }

    private static void delete(ArrayList<String> myArrayList, String key) {
        // Pre  : myArrayList has been initialized
        // Post : All copies of key are removed from myArrayList

        while(myArrayList.contains(key))
            myArrayList.remove(key);

    }


    private static int count(ArrayList<String> myArrayList, String key) {
        // Pre  : myArrayList has been initialized
        // Post : number of occurrences of key is computed and returned
        int sum = 0;
        for (int i = 0; i < myArrayList.size(); i++)
            if (myArrayList.get(i).contains(key)) sum++;
            return sum;
    }
}