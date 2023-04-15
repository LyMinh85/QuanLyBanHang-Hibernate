package BUS;

public class VegetableSalesData {
    private String name;
    private int quantity;

    public VegetableSalesData(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "VegetableSalesData{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
