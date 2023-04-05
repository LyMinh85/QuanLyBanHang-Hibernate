package Bus;

import java.util.Date;

public class RevenueDataPoint {
    private String label;
    private double revenue;
    public RevenueDataPoint(String label, double revenue) {
        this.label = label;
        this.revenue = revenue;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    @Override
    public String toString() {
        return "RevenueDataPoint{" +
                "label='" + label + '\'' +
                ", revenue=" + revenue +
                '}';
    }
}
