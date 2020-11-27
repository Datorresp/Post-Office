package dataStructures;

public class ListPI<T> implements ListPIInterface<T> {

    private Node<T> first, pi, prevpi;
    private int size;

    public ListPI() {
       first = null;
       pi = null;
       prevpi = null;
       size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isEnd() {
        return pi == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void begin() {
        pi = first;
        prevpi = null;
    }

    @Override
    public void next() throws NoSuchMethodException {
        if(pi == null){
            throw new NoSuchMethodException();
        }
        prevpi = pi;
        pi = pi.getNext();
    }

    @Override
    public T get() throws NoSuchMethodException {
        if (pi == null) {
            throw new NoSuchMethodException();
        }
        return pi.getType();
    }

    @Override
    public void insert(T x) {
        Node<T> newN = new Node<>(x, pi);
        if(pi == first) {
            first = newN;
        }else {
            prevpi.setNext(newN);
        }
        prevpi = newN;
        size++;
    }

    @Override
    public T remove() throws NoSuchMethodException {
        if (pi == null) {
            throw new NoSuchMethodException();
        }
        T x = pi.getType();
        if (pi == first){
            first = first.getNext();
        }else {
            prevpi.setNext(pi.getNext());
        }
        pi = pi.getNext();
        size--;
        return x;
    }
}
