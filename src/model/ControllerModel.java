package model;

import dataStructures.*;
import exceptions.EmptyException;
import exceptions.NullObjectException;

import java.util.ArrayList;
import java.util.LinkedList;

public class ControllerModel {

    ArrayList<Wholesaler> wholesalers;
    GraphDLabelled<Wholesaler> graphs;

    public ControllerModel() {
        try {
            wholesalers = new ArrayList<>();
            Wholesaler d1 = new Wholesaler("D1", "1236");
            wholesalers.add(d1);
            Wholesaler app = new Wholesaler("app", "3614");
            wholesalers.add(app);
            Wholesaler can = new Wholesaler("can", "2378");
            wholesalers.add(can);
            Wholesaler gray = new Wholesaler("gray", "1645");
            wholesalers.add(gray);
            addVertices();
            createConnections(d1,app,5);
            createConnections(d1, can, 10);
            createConnections(d1, gray, 4);
            createConnections(app, can, 3);
            createConnections(gray, can, 2);
            createConnections(can, gray, 5);
        } catch (EmptyException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * this method add a new Wholesaler to an ArrayList
     *
     * @param newWS
     * @throws NullObjectException
     */
    public void addNewWholesalers(Wholesaler newWS) throws NullObjectException {
        if (newWS == null) {
            throw new NullObjectException();
        }
        wholesalers.add(newWS);
    }

    /**
     * this method add all the vertices in the Arraylist to the graph
     */
    public void addVertices() throws EmptyException {
        if (wholesalers.size() == 0) {
            throw new EmptyException();
        }
        graphs = new GraphDLabelled<>(wholesalers.size());
        for (int i = 0; i < wholesalers.size(); i++) {
            graphs.labelVertice(i, wholesalers.get(i));
        }
    }

    /**
     * this method create edges between vertices
     *
     * @param g1
     * @param g2
     * @param p
     * @throws NoSuchMethodException
     */
    public void createConnections(Wholesaler g1, Wholesaler g2, int p) throws NoSuchMethodException {
        graphs.insertEdge(g1, g2, p);
    }

    /**
     * @param initial
     * @return
     * @throws NoSuchMethodException
     */
    public ObjInMatrix[][] dijkstra(Wholesaler initial) throws NoSuchMethodException {
        ObjInMatrix[][] oim = new ObjInMatrix[graphs.numEdges()][graphs.numVertices()];
        boolean[] bVertex = new boolean[graphs.numVertices()];
        oim[0][graphs.getCode(initial)] = new ObjInMatrix(0, graphs.getCode(initial));
        ListPI<Adjacent> adj0 = new ListPI<>();
        adj0.insert(new Adjacent(graphs.getCode(initial), 0));
        ListPI<Adjacent> adj1 = graphs.adjacentOf(initial);
        ListPI<Adjacent> adj2 = new ListPI<>();
        int i = 1;
        boolean e = false;
        adj0.begin();
        while (!e && i < graphs.numEdges()) {
            if (adj1 != null) {
                for (adj1.begin(); !adj1.isEnd(); adj1.next()) {
                    adj2.insert(adj1.get());
                    oim = setItems(oim, i, graphs.getLabel(adj0.get().destination), adj1.get());
                }
            }
            bVertex[adj0.get().destination] = true;
            adj0.next();
            if (adj0.isEnd()) {
                i++;
                adj0 = adj2;
                adj2 = new ListPI<>();
                adj0.begin();
            }
            if (adj0.isEmpty()) {
                e = true;
            } else {
                if (bVertex[adj0.get().destination] == true) {
                    adj1 = null;
                } else {
                    adj1 = graphs.adjacentOf(adj0.get().destination);
                }
            }
        }
        return oim;
    }


    /**
     * @param oim
     * @param code
     * @param previous
     * @param adjacent
     * @return
     * @throws NoSuchMethodException
     */
    private ObjInMatrix[][] setItems(ObjInMatrix[][] oim, int code, Wholesaler previous, Adjacent adjacent) throws NoSuchMethodException {
        int codeM = code - 1;
        double sumW = (oim[codeM][graphs.getCode(previous)].getSumWeight()) + (graphs.weightEdge(previous, graphs.getLabel(adjacent.destination)));
        if (oim[code][adjacent.destination] == null) {
            oim[code][adjacent.destination] = new ObjInMatrix(sumW, graphs.getCode(previous));
        } else {
            if ((oim[code][adjacent.destination].getSumWeight()) > sumW) {
                oim[code][adjacent.destination] = new ObjInMatrix(sumW, graphs.getCode(previous));
            }
        }

        return oim;
    }

    /**
     *
     * @return
     * @throws NoSuchMethodException
     */
    public double[][] floydWarshall() throws NoSuchMethodException {
        double[][] distanceMatrix = createDistanceMatrix();

        for (int k = 0; k < distanceMatrix.length; k++) {
            for (int i = 0; i < distanceMatrix.length; i++) {
                for (int j = 0; j < distanceMatrix.length; j++) {
                    if (distanceMatrix[i][k] + distanceMatrix[k][j] < distanceMatrix[i][j]) {
                        distanceMatrix[i][j] = distanceMatrix[i][k] + distanceMatrix[k][j];
                    }
                }
            }
        }
        return distanceMatrix;
    }

    /**
     *
     * @return
     * @throws NoSuchMethodException
     */
    private double[][] createDistanceMatrix() throws NoSuchMethodException {
        double[][] nm = new double[graphs.numVertices()][graphs.numVertices()];
        for (int i = 0; i < wholesalers.size(); i++) {
            ListPI<Adjacent> lp = graphs.adjacentOf(i);
            for (lp.begin(); !lp.isEnd(); lp.next()) {
                nm[i][lp.get().destination] = lp.get().weight;
            }
        }
        for (int x = 0; x < nm.length; x++) {
            for (int y = 0; y < nm[x].length; y++) {
                if (nm[x][y] == 0) {
                    nm[x][y] = Integer.MAX_VALUE;
                }
            }
        }
        return nm;
    }

    /**
     *
     * @return
     * @throws NoSuchMethodException
     */
    public int[] prim() throws NoSuchMethodException {

        double[][] graph = createDistanceMatrix1();

        int[] parent = new int[graphs.numVertices()];

        int[] key = new int[graphs.numVertices()];

        Boolean[] mstSet = new Boolean[graphs.numVertices()];

        for (int i = 0; i < graphs.numVertices(); i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < graphs.numVertices() - 1; count++) {

            int u = minKey(key, mstSet);

            mstSet[u] = true;
            for (int v = 0; v < graphs.numVertices(); v++)
                if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = (int) graph[u][v];
                }
        }
        return parent;
    }

    /**
     *
     * @return
     * @throws NoSuchMethodException
     */
    private double[][] createDistanceMatrix1() throws NoSuchMethodException {
        double[][] nm = new double[graphs.numVertices()][graphs.numVertices()];
        for (int i = 0; i < wholesalers.size(); i++) {
            ListPI<Adjacent> lp = graphs.adjacentOf(i);
            for (lp.begin(); !lp.isEnd(); lp.next()) {
                nm[i][lp.get().destination] = lp.get().weight;
            }
        }
        return nm;
    }

    /**
     *
     * @param key
     * @param mstSet
     * @return
     */
    private int minKey(int[] key, Boolean[] mstSet) {

        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < graphs.numVertices(); v++)
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }

    /**
     *
     * @return
     * @throws NoSuchMethodException
     */
    public String kruskalMST() throws NoSuchMethodException {

        String s = "";

        double[][] graph = createDistanceMatrix();

        int[] parent = new int[graphs.numVertices()];
        int INF = Integer.MAX_VALUE;

        int mincost = 0;

        for (int i = 0; i < graphs.numVertices(); i++)
            parent[i] = i;

        int edge_count = 0;
        while (edge_count < graphs.numVertices() - 1) {
            int min = INF, a = -1, b = -1;
            for (int i = 0; i < graphs.numVertices(); i++) {
                for (int j = 0; j < graphs.numVertices(); j++) {
                    if (find(i, parent) != find(j, parent) && graph[i][j] < min) {
                        min = (int) graph[i][j];
                        a = i;
                        b = j;
                    }
                }
            }
            parent = u(a, b, parent);
            s += "Edge " + (edge_count++) + ":(" + a + ", " + b + ") cost: " + min + "\n";
            mincost += min;
        }
        s += "MinCost: " + mincost;

        return s;
    }

    /**
     *
     * @param i
     * @param j
     * @param parent
     * @return
     */
    private int[] u(int i, int j, int[] parent) {
        int a = find(i, parent);
        int b = find(j, parent);
        parent[a] = b;
        return parent;
    }

    /**
     *
     * @param i
     * @param parent
     * @return
     */
    private int find(int i, int[] parent) {
        while (parent[i] != i)
            i = parent[i];
        return i;
    }

    /**
     *
     * @throws NoSuchMethodException
     */
    public void dfs() throws NoSuchMethodException {
        boolean visited[] = new boolean[graphs.numVertices()];
        for (int i = 0; i < graphs.numVertices(); ++i)
            if (visited[i] == false)
                dfsF(i, visited);
    }

    /**
     *
     * @param v
     * @param visited
     * @throws NoSuchMethodException
     */
    private void dfsF(int v, boolean[] visited) throws NoSuchMethodException {
        visited[v] = true;
        System.out.print(v + " ");

        ListPI<Adjacent> j = graphs.adjacentOf(v);
        j.begin();
        while (!j.isEnd()) {
            int n = j.get().destination;
            if (!visited[n])
                dfsF(n, visited);
            j.next();
        }
    }

    /**
     *
     * @param v
     * @return
     * @throws NoSuchMethodException
     */
    public String bfs(int v) throws NoSuchMethodException {
        boolean visited[] = new boolean[graphs.numVertices()];

        LinkedList<Integer> queue = new LinkedList<Integer>();

        String s = "";

        visited[v] = true;
        queue.add(v);

        while (queue.size() != 0) {
            v = queue.poll();
            s += v+" ";

            ListPI<Adjacent> j = graphs.adjacentOf(v);
            j.begin();
            while (!j.isEnd()) {
                int n = j.get().destination;
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
                j.next();
            }
        }
        return s;
    }

    public ArrayList<Wholesaler> getWholesalers() {
        return wholesalers;
    }

    public GraphDLabelled<Wholesaler> getGraphs() {
        return graphs;
    }
}
