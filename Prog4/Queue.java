
public class Queue implements QueueInterface {
  private Node lastNode;
  
  public Queue() {
    lastNode = null;   
  }  // end default constructor
  
  // queue operations:
  public boolean isEmpty() {
        return (lastNode == null);
  }  // end isEmpty

  public void dequeueAll() {
        this.lastNode = null;

  }  // end dequeueAll

  public void enqueue(Object newItem) {

      Node queuedItem = new Node(newItem);
    if (this.isEmpty()) {
        queuedItem.setNext(queuedItem);
      // insertion into empty queue
    }
    else {
      // insertion into nonempty queue
        queuedItem.setNext(lastNode.getNext());
        this.lastNode.setNext(queuedItem);

    }  // end if

        this.lastNode = queuedItem;

  }  // end enqueue

  public Object dequeue() throws QueueException {
    if (!this.isEmpty()) {
      // queue is not empty; remove front
        Node newLink = this.lastNode.getNext();
        if(newLink == this.lastNode)
            this.lastNode = null;
        else
            lastNode.setNext(newLink.getNext());

        return newLink.getItem();




    }  //end if
    else {
      throw new QueueException("QueueException on dequeue:"
                             + "queue empty");
    }  // end if
  }  // end dequeue

  public Object front() throws QueueException {
    if (!isEmpty()) {
      Node firstNode = lastNode.getNext();
      return firstNode.getItem();
    }
    else {
      throw new QueueException("QueueException on front:"
                             + "queue empty");
    }
  }

  public Object clone() throws CloneNotSupportedException
  {
	boolean copied = false;
        Queue copy = new Queue();
        Node curr = lastNode, prev = null;
        while ( (! copied) && (lastNode != null) )
        {
                Node temp = new Node(curr.getItem());
                if (prev == null)
                        copy.lastNode = temp;
                else
                        prev.setNext(temp);
                prev = temp;
                curr = curr.getNext();
		copied = (lastNode == curr);
        }
	prev.setNext(copy.lastNode);
        return copy;
  }
} // end Queue
