package BUS;

import DAO.OrderDAO;
import Entities.Order;
import Entities.OrderDetail;

import java.time.LocalDate;
import java.util.List;

public class OrderBUS {
    private OrderDAO orderDAO;
    public  OrderBUS() {
        orderDAO = new OrderDAO();
    }

    public List<Order> getOrders() {
        return orderDAO.getOrders();
    }

    // Tham số là order phải bao gồm orderDeatil trong đó
    // Tạo 1 hóa đơn mới trước xong
    // thêm từng sản phẩm vào hóa đơn đó
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
}
