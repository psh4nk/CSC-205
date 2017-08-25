
public class Links
{
	public static void main(String[] args)
	{
          	Node pos1 = null;
    		Node pos2 = null;
    		pos1 = new Node(new Integer(13));
   		pos1.setNext(new Node(new Integer(15), null));
    		pos2 = new Node(new Integer(11), null);
		pos2.setNext(pos1);
		printList(pos2);
		System.out.println("Number of elements linked to node pos1: " + count(pos1));
		System.out.println("Number of elements linked to node pos2: " + count(pos2));
		System.out.println("Maximum value of node pos1: " + findMax(pos1));
		System.out.println("Maximum value of node pos2: " + findMax(pos2));
	}

	private static void printList(Node head)
	{
		if (head != null)
		{
			System.out.println(head.getItem());
			printList(head.getNext());
		}
	}
	
	private static int count(Node head){
        if(head == null)
            return 0;
	    else
		    return 1 + count(head.getNext());
	}

	private static Integer findMax(Node head){
		for(int i = 0; i < count(head); i++){
		if((((Comparable)head.getItem()).compareTo((Comparable)head.getNext().getItem())) < 0){
			head = head.getNext();
        }
	}
	return (Integer) head.getItem();
	}
}
