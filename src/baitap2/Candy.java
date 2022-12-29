package baitap2;

import java.io.Serializable;

public class Candy extends Product implements Serializable {
    private int weight;

    public Candy(int id, String name, int price, int quantity, Category category, int weight) {
        super(id, name, price, quantity, category);
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return super.toString()
                + ",weight = " + weight;
    }
}
