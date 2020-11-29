package model;

import dataStructures.GraphDLabelled;
import exceptions.EmptyException;
import exceptions.NullObjectException;

import java.util.ArrayList;

public class ControllerModel {

    ArrayList<Wholesaler> wholesalers;
    GraphDLabelled<Wholesaler> graphs;

    public ControllerModel() {
        wholesalers = new ArrayList<>();
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
        if (wholesalers.size() == 0){
            throw new EmptyException();
        }
        graphs = new GraphDLabelled<>(wholesalers.size());
        for (int i = 0; i < wholesalers.size(); i++) {
            graphs.labelVertice(i,wholesalers.get(i));
        }
    }

    /**
     * this method create edges between vertices
     * @param g1
     * @param g2
     * @param p
     * @throws NoSuchMethodException
     */
    public void createConnections(Wholesaler g1, Wholesaler g2, int p) throws NoSuchMethodException {
        graphs.insertEdge(g1, g2, p);
    }

    public ArrayList<Wholesaler> getWholesalers() {
        return wholesalers;
    }

    public GraphDLabelled<Wholesaler> getGraphs() {
        return graphs;
    }
}
