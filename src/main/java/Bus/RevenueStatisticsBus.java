package Bus;

import Dao.OrderDao;
import Entities.Order;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RevenueStatisticsBus {
    private OrderDao orderDao;
    public RevenueStatisticsBus() {
        orderDao = new OrderDao();
    }
    public List<RevenueDataPoint> getDailyRevenue(LocalDate startDate, LocalDate endDate) {
        List<Order> orders = orderDao.getOrdersByDateRange(startDate, endDate);
        return getRevenueByTimeUnit(orders, "Days");
    }

    public List<RevenueDataPoint> getMonthlyRevenue(LocalDate startDate, LocalDate endDate) {
        List<Order> orders = orderDao.getOrdersByDateRange(startDate, endDate);
        return getRevenueByTimeUnit(orders, "Months");
    }

    public List<RevenueDataPoint> getYearlyRevenue(LocalDate startDate, LocalDate endDate) {
        List<Order> orders = orderDao.getOrdersByDateRange(startDate, endDate);
        return getRevenueByTimeUnit(orders, "Years");
    }

    private List<RevenueDataPoint> getRevenueByTimeUnit(List<Order> orders, String unit) {
        Map<String, Double> revenueData = new LinkedHashMap<>();
        for (Order order : orders) {
            String key = switch (unit) {
                case "Days" -> order.getDate().format(DateTimeFormatter.ofPattern("dd/MM"));
                case "Months" -> order.getDate().format(DateTimeFormatter.ofPattern("MM/yyyy"));
                case "Years" -> order.getDate().format(DateTimeFormatter.ofPattern("yyyy"));
                default -> throw new IllegalStateException("Unexpected value: " + unit);
            };
            double revenue = order.getTotal();
            if (revenueData.containsKey(key)) {
                revenue += revenueData.get(key);
            }
            revenueData.put(key, revenue);
        }
        return mapRevenueDataToDataPoints(revenueData);
    }

    private List<RevenueDataPoint> mapRevenueDataToDataPoints(Map<String, Double> revenueData) {
        List<RevenueDataPoint> dataPoints = new ArrayList<>();
        for (Map.Entry<String, Double> entry : revenueData.entrySet()) {
            dataPoints.add(new RevenueDataPoint(entry.getKey(), entry.getValue()));
        }
        return dataPoints;
    }
}
