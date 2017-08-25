/**
 *  Created by Preston Shankle
 *  11/13/16
 *  CSC 205
 *  Pez Class
 */

public class Pez{
    private static String[] colors = { "yellow", "red", "green", "green", "yellow", "yellow", "red", "green" };
    public static void main(String[] args){
        Stack pezStack = new Stack();
        addPez(pezStack);
        try{
            pezStack = removeGreen(pezStack);
            printStack(pezStack);
        }
        catch (CloneNotSupportedException e) {
            System.out.println("Clone not supported");
        }
    }
    public static void addPez(Stack pezStack){
        for(int i = 0; i < colors.length; i++)
            pezStack.push(colors[i]);
    }

    public static Stack removeGreen(Stack pezStack) throws CloneNotSupportedException{
            Stack temp = (Stack) pezStack.clone();
            temp.popAll();
            for (int i = 0; i < colors.length; i++) {
                if (!colors[i].equals("green"))
                    temp.push(colors[i]);
            }
            return temp;
    }

    public static void printStack(Stack pezStack) throws CloneNotSupportedException{
        Stack temp = (Stack) pezStack.clone();
        while(!temp.isEmpty()){
            System.out.println(temp.pop());
        }
    }
}