package DAO;

import Entities.Order;
import Entities.Vegetable;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    public List<Order> getOrdersByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Order> orders = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            // Sử dụng QueryBuilder
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Order> cr = cb.createQuery(Order.class);
            Root<Order> root = cr.from(Order.class);
            cr.select(root)
                    .where(cb.between(root.get("date"), startDate, endDate))
                    .orderBy(cb.asc(root.get("date")));
            orders = session.createQuery(cr).getResultList();
            session.getTransaction().commit();
        }
        return orders;
    }

    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            // Sử dụng QueryBuilder
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Order> cr = cb.createQuery(Order.class);
            Root<Order> root = cr.from(Order.class);
            // Nếu muốn sử dụng getVegetables() thì phải fetch
            // Vì quan hệ giữa Order với Vegetable là fetch LAZY
            root.fetch("vegetables");
            cr.select(root);
            orders = session.createQuery(cr).getResultList();
            session.getTransaction().commit();
        }
        return orders;
    }

    public static void main(String[] args) {
//        LocalDate startDate = LocalDate.parse("2023-02-04");
//        LocalDate endDate = LocalDate.parse("2023-04-04");
//        RevenueStatisticsBus revenueStatisticsBus = new RevenueStatisticsBus();
//        List<RevenueDataPoint> revenueDate = revenueStatisticsBus.getDailyRevenue(startDate, endDate);
//        for (RevenueDataPoint dataPoint : revenueDate) {
//            System.out.println(dataPoint);
//        }

        OrderDAO orderDao = new OrderDAO();
        List<Order> orders = orderDao.getOrders();
        for (Order order : orders) {
            List<Vegetable> vegetables = order.getVegetables();
            System.out.printf("Order id: %d\n", order.getOrderID());
            for (Vegetable vegetable : vegetables) {
                System.out.printf("Vegetable id: %d\n", vegetable.getVegetableID());
                System.out.printf("Vegetable name: %s\n", vegetable.getVegetableName());
            }
            System.out.println("-".repeat(10));
        }
    }
}
