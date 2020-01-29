import java.util.Iterator; //used to implement iteators for the list so we can use .next() etc
import java.util.NoSuchElementException; //used to avoid accessing elements that do not exsist

public class MyQueue <Item>  {


    public int enques = 0; //keep tracks of how many times enqueue is called.


    public int dequeues = 0; //keeps tracks of how many times dequeue is called


    private Node<Item> first; //tracks front of code


    private Node<Item> last; //tracks end of queue


    private int n; //tracks number of items on queue




    private static class Node<Item>{ //used for each node in the list
        private Item item;
        private Node<Item> next;
    }


    public MyQueue(){ //default constructor just sets values to 0
        first = null;
        last  = null;
        n = 0;
    }


    public boolean isEmpty(){ //simple check if list is empty
        return first == null;
    }


    public int size(){ //returns the size that is stored locally as n
        return n;
    }


    public Item peek(){ //looks at the front of the queue without popping the item off
        if(isEmpty()) throw new NoSuchElementException("queue underflow");
        return first.item;
    }


    public void enqueue(Item item){ //used to add a new item to the end of the list
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if(isEmpty()) first = last; //accounts for adding an element to a empty list
        else          oldlast.next = last;
        n++; //increment count of list
        enques++; //increment how many time enque is called. never gets decremented.

    }


    public Item dequeue(){ //"pops" item out of the front of the queue. It is the FIFO
        if(isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        n--; //decrements number of items in list but not enques importantly;
        dequeues++; //keeps track of the ammount of times this function is called.
        if(isEmpty()) last = null; //accounts for popping the last item
        return item;

    }


}


