/**
 * Author: Michael Sauce
 */
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class MyLimitedLinkedList<T> implements MyList<T> {
    private Class dataType; // The type of data we have in our Linked List
    private boolean first;  // True until Linked List adds its first element
    private MyNode<T> dummyHead;
    private MyNode<T> dummyTail;
    private int sz;

    // No-argument constructor
    public MyLimitedLinkedList() {
        first = true;
        sz = 0;
        dummyHead = new MyNode<>();
        dummyTail = new MyNode<>();
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    public boolean add(T element) {
        if (first) {
            dataType = element.getClass();
            first = false;
        }
        if (element.getClass() != dataType) {
            throw new InputMismatchException("Type mismatch");
        }

        // call addLast since default behavior will be to add elements to the end of the list; handles sz++
        addLast(element);

        return true; // (as specified by Collection.add(E))
    }

    public void add(int index, T element) {
        if (index < 0 || sz < index) {
            throw new IndexOutOfBoundsException();
        }
        if (first) {
            dataType = element.getClass();
            first = false;
        }
        if (element.getClass() != dataType) {
            throw new InputMismatchException("Type mismatch");
        }
        //create a container to hold the helper functions returned node
        MyNode<T> indexedNode;
        indexedNode = index < (sz/2) ? iterateFromFront(index): iterateFromRear(index);
        
        indexedNode.prev = indexedNode.prev.next = new MyNode<T>(element, indexedNode, indexedNode.prev);


        sz++;
    }

    // Not from MyList interface
    public void addFirst(T element) {
        if (first) {
            dataType = element.getClass();
            first = false;
        }
        if (element.getClass() != dataType) {
            throw new InputMismatchException("Type mismatch");
        }
        //dummyhead will always be the barrier, set both the next and the next's prev pointers to this new node
        dummyHead.next = dummyHead.next.prev = new MyNode<T>(element, dummyHead.next, dummyHead q1`);

        sz++;
    }

    // Not from MyList interface
    public void addLast(T element) {
        
        //this will add in between the previous two nodes
        dummyTail.prev = dummyTail.prev.next = new MyNode<T>(element,dummyTail,dummyTail.prev);
        sz++;
    }

    public void clear() {
        //point these back to the variables to donate everything to the garbage collecter and reset
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    public boolean contains(T element) {
        if (!first && element.getClass() != dataType) {
            throw new InputMismatchException("Type mismatch");
        }
        //variable for if the element is here in our list
        boolean here = false;

        //callindexof and it's -1 then that means it wasn't in the list
        here = indexOf(element) ==-1 ? false: true;
    
        return here;
    }

    public T get(int index) {
        if (index < 0 || sz <= index) {
            throw new IndexOutOfBoundsException();
        }
        //check where to start and then return with the cheaper method
        return (index < (sz/2)) ? iterateFromFront(index).val : iterateFromRear(index).val;
    }

    // Not from MyList interface
    public T getFirst() {
        if (sz == 0) {
            throw new NoSuchElementException();
        }

        //return the value of whatever dummyHead points to
        return dummyHead.next.val;
    }

    // Not from MyList interface
    public T getLast() {
        if (sz == 0) {
            throw new NoSuchElementException();
        }

        //return the value of whatever dummytail points to
        return dummyTail.prev.val;
    }

    public int indexOf(T element) {
        if (!first && element.getClass() != dataType) {
            throw new InputMismatchException("Type mismatch");
        }

        //create a container for our nodes to loop through
        MyNode<T> iteratorNode = dummyHead.next;
        //loop through the list and check if element is here
        int here = -1;
        for(int i =0; i < sz; i++){
            if(iteratorNode.val == element){
                here = i;
                break;
            }
            iteratorNode = iteratorNode.next;
        }
        return here;
    }

    public boolean isEmpty() {
        //if size hasn't been incremented then the list is empty
        return (sz == 0);
    }

    public int lastIndexOf(T element) {
        if (!first && element.getClass() != dataType) {
            throw new InputMismatchException("Type mismatch");
        }

        //create a container for our nodes to loop through
        MyNode<T> iteratorNode = dummyHead.next;
        //loop through the list and check if element is here
        int here = -1;
        //start from the back and break when it's found
        for(int i = sz-1; i >= 0; i--){
            if(iteratorNode.val == element){
                here = i;
                break;
            }
            iteratorNode = iteratorNode.next;
        }
        return here;
    }

    public T remove(int index) {
        if (index < 0 || sz <= index) {
            throw new IndexOutOfBoundsException();
        }

        //create a new index node and iterate with the cheapest option
        MyNode<T> indexedNode;
        indexedNode = index < (sz/2) ? iterateFromFront(index): iterateFromRear(index);


        T ret = indexedNode.val;
        indexedNode.prev.next = indexedNode.next;
        indexedNode.next.prev = indexedNode.prev;

        return ret;
    }

    public boolean remove(T element) {
        if (!first && element.getClass() != dataType) {
            throw new InputMismatchException("Type mismatch");
        }

        //create a container for our nodes to loop through
        MyNode<T> iteratorNode = dummyHead.next;
        //loop through the list and check if element is here
        boolean here = false;
        //start from the back and break when it's found
        for(int i = sz-1; i >= 0; i--){
            if(iteratorNode.val == element){
                here = true;
                break;
            }
            iteratorNode = iteratorNode.next;
        }
    
        iteratorNode.prev.next = iteratorNode.next;
        iteratorNode.next.prev = iteratorNode.prev;        
        
        return here;
    }

    // Not from MyList interface
    public T removeFirst() {
        if (sz == 0) {
            throw new NoSuchElementException();
        }

        T ret = dummyHead.next.val;
        //change the pointers to exlcude dummyHead.next using the .next.ext node
        dummyHead.next = dummyHead.next.next;
        dummyHead.next.prev = dummyHead;
        return ret;
    }

    // Not from MyList interface
    public T removeLast() {
        if (sz == 0) {
            throw new NoSuchElementException();
        }

        T ret = dummyTail.prev.val;
        //change the pointers to exclude prev and let the garbage collecter handle the rest
        dummyTail.prev.prev.next = dummyTail;
        dummyTail.prev = dummyTail.prev.prev;
       

        return ret;
    }

    public T set(int index, T element) {
        if (index < 0 || sz <= index) {
            throw new IndexOutOfBoundsException();
        }
        if (element.getClass() != dataType) {
            throw new InputMismatchException("Type mismatch");
        }

        //create a new index node and iterate with the cheapest option
        MyNode<T> indexedNode;
        indexedNode = index < (sz/2) ? iterateFromFront(index): iterateFromRear(index);

        T ret = indexedNode.val;
        indexedNode.val = element;

        return ret;
    }

    public int size() {
        // return the size of the linkedlist
        return sz;
    }

    // Helper functions
    private MyNode<T> iterateFromFront(int index) {
        MyNode<T> iteratorNode = dummyHead.next;
        int i = 0;
        while (i != index) {
            iteratorNode = iteratorNode.next;
            ++i;
        }

        return iteratorNode;
    }

    private MyNode<T> iterateFromRear(int index) {
        MyNode<T> iteratorNode = dummyTail.prev;
        int i = sz - 1;
        while (i != index) {
            iteratorNode = iteratorNode.prev;
            --i;
        }

        return iteratorNode;
    }
}

/**
 * Usage in main():
 * MyLimitedLinkedList<Integer> myFirstList =
 *     new MyLimitedLinkedList<Integer>();
 * myFirstList.add(5);
 * ...
 *
 * MyLimitedLinkedList<String> mySecondList =
 *     new MyLimitedLinkedList<String>();
 * mySecondList.add("Hello World");
 * ...
 */
