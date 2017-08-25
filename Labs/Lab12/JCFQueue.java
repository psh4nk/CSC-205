
import java.util.*;

public class JCFQueue {
	public static void main(String[] args) throws CloneNotSupportedException {
		LinkedList queue1 = new LinkedList();
		LinkedList queue2 = new LinkedList();
		Object key = new Character('$');

		queue1.addLast(new Character('b'));
		queue1.addLast(new Character('$'));
		queue1.addLast(new Character('E'));
		queue1.addLast(new Character('$'));
		queue2.addLast(new Character('b'));
		queue2.addLast(new Character('$'));
		queue2.addLast(new Character('E'));
		queue2.addLast(new Character('b'));
		System.out.println("dup check = " +
				identicalCheck(queue1, queue2));
		findAndReplace(queue1, key, new Character('*'));
		System.out.println("** After findAndReplace **");
		printQueue(queue1);
	}

	private static boolean identicalCheck(LinkedList queue1,
										  LinkedList queue2)
			throws CloneNotSupportedException {
		boolean size = false, items = false, all = false;
		if (queue1.size() == queue2.size())
			size = true;
		try {
			if (size == true) {
				for (int i = 0; i <= queue1.size() - 1; i++)
					if (queue1.get(i).equals(queue2.get(i)))
						items = true;
					else {
						items = false;
						System.out.println("Queues differ at location: " + (i + 1));
					}
			} else {
				System.out.println("Queues not identical");
				items = false;
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Index out of bounds. Queue size not identical.");
		}
		if (size == true && items == true)
			all = true;
		return all;


	}


	private static void findAndReplace(LinkedList queue1,
									   Object key, Object newVal) {
		if (queue1.isEmpty()) {
			System.out.println("Queue is empty.");
		} else {
			for (int i = 0; i < queue1.size(); i++) {
				if (queue1.get(i).equals(key))
					queue1.set(i, newVal);
			}
		}


	}


	private static void printQueue(LinkedList q)
			throws CloneNotSupportedException {
		if (q.isEmpty()) {
			System.out.println("Queue is empty.");
		} else {
			for (int i = 0; i < q.size(); i++)
				System.out.println(q.get(i));
		}


	}
}
