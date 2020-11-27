package dataStructures;

public interface ListPIInterface<T> {
    public boolean isEmpty();
    public boolean isEnd();
    public int size();
    public void begin();
    public void next() throws NoSuchMethodException;
    public T get() throws NoSuchMethodException;
    public void insert(T x);
    public T remove() throws NoSuchMethodException;

}
