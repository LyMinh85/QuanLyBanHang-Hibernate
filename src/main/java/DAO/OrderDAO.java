package DAO;


import Entities.*;
import jakarta.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    public List<Order> getOrdersInRange(LocalDate startDate, LocalDate endDate) {
        List<Order> orders;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = """
                    SELECT o FROM Order o
                    WHERE o.date BETWEEN :startDate AND :endDate
                    ORDER BY o.date ASC
                    """;
            Query<Order> query = session.createQuery(hql, Order.class);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            orders = query.getResultList();
        }
        return orders;
    }

    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = """
                    SELECT DISTINCT o FROM Order o
                    INNER JOIN FETCH o.orderDetails od
                    INNER JOIN FETCH od.vegetable v
                    INNER JOIN FETCH o.customer
                    """;
            Query<Order> query = session.createQuery(hql, Order.class);
            orders = query.getResultList();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public boolean addOrder(Order order, int customerID) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Customer customer = session.find(Customer.class, customerID);
            order.setCustomer(customer);
            session.persist(order);
            transaction.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean addOrderDetail(List<OrderDetail> orderDetails) {
        int insertedRows = 0;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            for (int i = 0; i < orderDetails.size(); i++) {
                session.persist(orderDetails.get(i));
                if (i % 20 == 0) {
                    insertedRows = i + 1;
                    session.flush();
                    session.clear();
                }
            }
            insertedRows = orderDetails.size();
            transaction.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return insertedRows == orderDetails.size();
    }

    public boolean updateOrder(Order order) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(order);
            transaction.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteOrder(int orderID) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Order order = session.get(Order.class, orderID);
            if (order != null) {
                order.getOrderDetails().forEach(session::remove);
                session.remove(order);
                transaction.commit();
            } else {
                return false;
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
