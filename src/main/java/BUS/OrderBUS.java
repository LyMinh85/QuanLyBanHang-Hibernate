package BUS;

import DAO.OrderDAO;
import Entities.Order;
import Entities.OrderDetail;

import java.time.LocalDate;
import java.util.List;

public class OrderBUS {
    private final OrderDAO orderDAO;

    public OrderBUS() {
        orderDAO = new OrderDAO();
    }

    public List<Order> getOrders() {
        return orderDAO.getOrders();
    }

    public boolean addOrder(Order order, int customerID) {
        return orderDAO.addOrder(order, customerID);
    }

    public boolean addOrderDetail(List<OrderDetail> orderDetails) {
        return orderDAO.addOrderDetail(orderDetails);
    }

    public boolean updateOrder(Order order) {
        return orderDAO.updateOrder(order);
    }

    public boolean deleteOrder(int orderID) {
        return orderDAO.deleteOrder(orderID);
    }

    public List<Order> getOrdersInRange(LocalDate startDate, LocalDate endDate) {
        return orderDAO.getOrdersInRange(startDate, endDate);
    }

    public List<Order> getByCustomerName(String name) {
        return orderDAO.getByCustomerName(name);
    }
}
