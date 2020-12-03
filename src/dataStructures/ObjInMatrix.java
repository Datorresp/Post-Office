package dataStructures;

public class ObjInMatrix {
    private double sumWeight;
    private int from;

    public ObjInMatrix(double sumWeight, int from) {
        this.sumWeight = sumWeight;
        this.from = from;
    }

    public double getSumWeight() {
        return sumWeight;
    }

    public void setSumWeight(int sumWeight) {
        this.sumWeight = sumWeight;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }


    @Override
    public String toString() {
        return "sw: " + sumWeight +", from " + from;
    }
}
