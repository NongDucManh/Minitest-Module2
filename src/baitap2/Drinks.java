package baitap2;

import java.io.Serializable;

public class Drinks extends Product implements Serializable {
    private int volume;
    private String bottleType;

    public Drinks(int id, String name, int price, int quantity, Category category, int volume, String bottleType) {
        super(id, name, price, quantity, category);
        this.volume = volume;
        this.bottleType = bottleType;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getBottleType() {
        return bottleType;
    }

    public void setBottleType(String bottleType) {
        this.bottleType = bottleType;
    }

    @Override
    public String toString() {
        return super.toString() + ",volume = " + volume + ",bottleType = " + bottleType;
    }
}
