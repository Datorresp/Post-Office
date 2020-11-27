package dataStructures;

public class GraphD<T> extends Graph {

    protected int numV, numE;
    protected ListPI<Adjacent> array[];

    public GraphD(int numV) {
        this.numV = numV;
        numE = 0;
        array = new ListPI[numV+1];
        for (int i = 0; i < numV; i++) {
            array[i] = new ListPI<Adjacent>();
        }
    }

    @Override
    public int numVertices() {
        return numV;
    }

    @Override
    public int numEdges() {
        return numE;
    }

    @Override
    public boolean existEdge(int i, int j) throws NoSuchMethodException {
        ListPI<Adjacent> l = array[i];
        boolean exist = false;
        for (l.begin();!l.isEnd() && !exist;l.next()){
            if (l.get().destination == j)
                exist = true;
        }
        return exist;
    }

    @Override
    public double weightEdge(int i, int j) throws NoSuchMethodException {
        ListPI<Adjacent> l = array[i];
        for (l.begin();!l.isEnd();l.next()){
            if (l.get().destination == j )
                return l.get().weight;
        }
        return 0.0;
    }

    @Override
    public void insertEdge(int i, int j) throws NoSuchMethodException {
        insertEdge(i,j,1);
    }

    @Override
    public void insertEdge(int i, int j, double p) throws NoSuchMethodException {
        if (!existEdge(i,j)){
            array[i].insert(new Adjacent(j,p));
            numE++;
        }
    }

    @Override
    public ListPI<Adjacent> adjacentOf(int i) {
        return array[i];
    }
}
