package dataStructures;

public class Adjacent {
    public int destination;
    public double weight;

    Adjacent(int codAdj, double p) {
        destination = codAdj;
        weight = p;
    }

    @Override
    public String toString() {
        return "Adjacent{" +
                "destination=" + destination +
                ", weight=" + weight +
                '}';
    }
}
