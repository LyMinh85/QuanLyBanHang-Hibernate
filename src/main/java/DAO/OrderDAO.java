package DAO;

import BUS.OrderBUS;
import BUS.RevenueDataPoint;
import BUS.RevenueStatisticsBUS;
import BUS.VegetableBUS;
import Entities.*;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderDAO {
    public List<Order> getOrdersInRange(LocalDate startDate, LocalDate endDate) {
        List<Order> orders;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Sử dụng QueryBuilder
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Order> cr = cb.createQuery(Order.class);
            Root<Order> root = cr.from(Order.class);
            cr.select(root)
                    .where(cb.between(root.get("date"), startDate, endDate))
                    .orderBy(cb.asc(root.get("date")));
            orders = session.createQuery(cr).getResultList();
        }
        return orders;
    }

    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Order> cr = cb.createQuery(Order.class);
            Root<Order> root = cr.from(Order.class);
            root.fetch("orderDetails");
            cr.select(root);
            orders = session.createQuery(cr).getResultList();
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
