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

        // call addLast since default behavior will be to add elements to the end of the list; handles first case and sz++
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

        //if index is closer to the front of the list then iterate from the front to add it so we can save a few pennies
        if(index < (sz/2) ){
            MyNode<T> iteratorNode = dummyHead.next;
            int i = 0;
            while (i != index) {
                iteratorNode = iteratorNode.next;
                ++i;
            }
            
            //set our t
            iteratorNode =  new MyNode<T>(element, iteratorNode, iteratorNode.prev);

        }
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
        //make the first and only element both the head and tail
        if(first)
            dummyHead = dummyTail = new MyNode<T>(element);
        else{
            //set the first prev link = to element, and link element's next node to dummHead
            dummyHead.prev = new MyNode<T>(element,dummyHead,null);
            //now set dummyhead to its prev node so that it's first in the list
            dummyHead = dummyHead.prev;
        }

        sz++;
    }

    // Not from MyList interface
    public void addLast(T element) {
        // make the first and only element both the head and tail
        if(first)
            dummyHead = dummyTail = new MyNode<T>(element);
        else{
            //set the last next link = to element, and link element's prev node to dummytail
            dummyTail.next = new MyNode<T>(element,null,dummyTail);
            //now set dummytail to its next node so that it's last in the list
            dummyTail = dummyTail.next;
        }

        sz++;
    }

    public void clear() {
        // ...
    }

    public boolean contains(T element) {
        if (!first && element.getClass() != dataType) {
            throw new InputMismatchException("Type mismatch");
        }

        // ...
        return false;
    }

    public T get(int index) {
        if (index < 0 || sz <= index) {
            throw new IndexOutOfBoundsException();
        }

        MyNode<T> iteratorNode = null;
        // ...

        return null;
    }

    // Not from MyList interface
    public T getFirst() {
        if (sz == 0) {
            throw new NoSuchElementException();
        }

        T ret = null;
        // ...

        return null;
    }

    // Not from MyList interface
    public T getLast() {
        if (sz == 0) {
            throw new NoSuchElementException();
        }

        T ret = null;
        // ...

        return null;
    }

    public int indexOf(T element) {
        if (!first && element.getClass() != dataType) {
            throw new InputMismatchException("Type mismatch");
        }

        // ...
        return -1;
    }

    public boolean isEmpty() {
        //if size hasn't been incremented then the list is empty
        return (sz == 0);
    }

    public int lastIndexOf(T element) {
        if (!first && element.getClass() != dataType) {
            throw new InputMismatchException("Type mismatch");
        }

        // ...
        return -1;
    }

    public T remove(int index) {
        if (index < 0 || sz <= index) {
            throw new IndexOutOfBoundsException();
        }

        // ...

        T ret = null;
        // ...

        return null;
    }

    public boolean remove(T element) {
        if (!first && element.getClass() != dataType) {
            throw new InputMismatchException("Type mismatch");
        }

        // ...
        return false;
    }

    // Not from MyList interface
    public T removeFirst() {
        if (sz == 0) {
            throw new NoSuchElementException();
        }

        T ret = null;
        // ...

        return null;
    }

    // Not from MyList interface
    public T removeLast() {
        if (sz == 0) {
            throw new NoSuchElementException();
        }

        T ret = null;
        // ...

        return null;
    }

    public T set(int index, T element) {
        if (index < 0 || sz <= index) {
            throw new IndexOutOfBoundsException();
        }
        if (element.getClass() != dataType) {
            throw new InputMismatchException("Type mismatch");
        }

        // ...

        T ret = null;
        // ...

        return null;
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
