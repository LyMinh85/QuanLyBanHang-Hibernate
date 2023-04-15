package BUS;

import Entities.Order;
import Entities.OrderDetail;
import Entities.Vegetable;

import java.time.LocalDate;
import java.time.temporal.*;
import java.util.*;

public class ProductStatisticsBUS {
    private final OrderBUS orderBUS;
    public ProductStatisticsBUS() {
        orderBUS = new OrderBUS();
    }

    // Lấy dữ liệu sản phẩm đã bán của ngày
//    public List<Order> getProductsForSaleOfDay(LocalDate date){
//        return orderBUS.getOrdersInRange(date, date);
//    }
    // Lấy top 5 sản phẩm bán chạy nhất của tháng
    public List<VegetableSalesData> getTopSellingProductsOfMonth(int month, int year){
        LocalDate startMonth = LocalDate.of(year, month, 1);
        LocalDate endMonth = startMonth.with(TemporalAdjusters.lastDayOfMonth());
        List<Order> orders = orderBUS.getOrdersInRange(startMonth, endMonth);
        Map<String, Integer> map = new HashMap<>();
        List<VegetableSalesData> salesDataList = new ArrayList<>();
        for (Order order : orders) {
            for (OrderDetail od : order.getOrderDetails()) {
                Vegetable vegetable = od.getVegetable();
                int quantity = od.getQuantity();
                if (map.containsKey(vegetable.getVegetableName())) {
                    quantity += map.get(vegetable.getVegetableName());
                }
                map.put(vegetable.getVegetableName(), quantity);
            }
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            VegetableSalesData salesData = new VegetableSalesData(entry.getKey(), entry.getValue());
            salesDataList.add(salesData);
        }
        salesDataList.sort(Comparator.comparing(VegetableSalesData::getQuantity).reversed());
        return salesDataList;
    }

    // Lấy top 5 sản phẩm bán chạy nhất của tháng
    public List<VegetableSalesData> getTopsellingProductsOfYear(int year){
        LocalDate startYear = LocalDate.of(year, 1, 1);
        LocalDate endYear = LocalDate.of(year, 12, 1).with(TemporalAdjusters.lastDayOfMonth());
        List<Order> orders = orderBUS.getOrdersInRange(startYear, endYear);
        Map<String, Integer> map = new HashMap<>();
        List<VegetableSalesData> salesDataList = new ArrayList<>();
        for (Order order : orders) {
            for (OrderDetail od : order.getOrderDetails()) {
                Vegetable vegetable = od.getVegetable();
                int quantity = od.getQuantity();
                if (map.containsKey(vegetable.getVegetableName())) {
                    quantity += map.get(vegetable.getVegetableName());
                }
                map.put(vegetable.getVegetableName(), quantity);
            }
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            VegetableSalesData salesData = new VegetableSalesData(entry.getKey(), entry.getValue());
            salesDataList.add(salesData);
        }
        salesDataList.sort(Comparator.comparing(VegetableSalesData::getQuantity).reversed());
        return salesDataList.subList(0, 5);
    }
    
}
