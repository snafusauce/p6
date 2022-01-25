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

        // ...
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

        // ...
    }

    // Not from MyList interface
    public void addFirst(T element) {
        // ...
    }

    // Not from MyList interface
    public void addLast(T element) {
        // ...
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
        // ...
        return true;
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
        // ...
        return 0;
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
