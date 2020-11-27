package dataStructures;

public class GraphDLabelled<T> extends GraphD<T> {
    T[] labels;
    HashTable<T, Integer> dicEdges;

    public GraphDLabelled(int numV) {
        super(numV);
        labels = (T[]) new Object[numV + 1];
        dicEdges = new HashTable<>(numV);
    }

    public boolean existEdge(T i, T j) throws NoSuchMethodException {
        return existEdge(getCode(i), getCode(j));
    }

    public double weightEdge(T i, T j) throws NoSuchMethodException {
        return weightEdge(getCode(i), getCode(j));
    }

    public void insertEdge(T i, T j) throws NoSuchMethodException {
        insertEdge(getCode(i), getCode(j));
    }

    public void insertEdge(T i, T j, double p) throws NoSuchMethodException {
        insertEdge(getCode(i), getCode(j), p);
    }

    public ListPI<Adjacent> adjacentOf(T i) {
        return adjacentOf(getCode(i));
    }

    public void labelVertice(int code, T label) {
        labels[code] = label;
        dicEdges.insert(label, code);
    }

    public T getLabel(int code) {
        return labels[code];
    }

    public int getCode(T label) {
        int code;

        code = dicEdges.search(label).intValue();

        return code;
    }
}
