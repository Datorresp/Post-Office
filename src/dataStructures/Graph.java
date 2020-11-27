package dataStructures;

public abstract class Graph {
    public Graph() {}
    public abstract int numVertices();
    public abstract int numEdges();
    public abstract boolean existEdge(int i, int j) throws NoSuchMethodException;
    public abstract double weightEdge(int i, int j) throws NoSuchMethodException;
    public abstract void insertEdge(int i, int j) throws NoSuchMethodException;
    public abstract void insertEdge(int i, int j, double p) throws NoSuchMethodException;
    public abstract ListPI<Adjacent> adjacentOf(int i);
    public String ts() throws NoSuchMethodException {
        String res = "" ;
        for (int i = 1 ; i <= numVertices() ; i++) {
            res += "Vértice: " + i;
            ListPI<Adjacent> l = adjacentOf(i);
            res += (l.isEmpty()) ? " sin Adyacentes " : " con Adyacentes: ";
            for (l.begin(); !l.isEnd() ; l.next()) {
                res += l.get() + " ";
                res += "\n";
            }
        } return res;
    }

}
