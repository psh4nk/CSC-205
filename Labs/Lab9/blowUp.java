public class blowUp{
public static int count;

    public static void main(String[] args){
        blowUp(1);
    }
    public static void blowUp(int args){
        System.out.println(count);
        if(args < 1)
            return;
        else {
            count++;
            blowUp(args);
        }
    }


}

