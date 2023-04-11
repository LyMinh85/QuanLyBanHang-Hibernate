package DAO;

import Entities.Customer;
import Entities.Order;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = """
                    SELECT o FROM Customer o
                    """;
            Query<Customer> query = session.createQuery(hql, Customer.class);
            customers = query.getResultList();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return customers;
    }
    public boolean addCustomer(Customer customer) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(customer);
            transaction.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean updateCustomer(Customer customer) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(customer);
            transaction.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteCustomer(int customerId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Customer customer = session.find(Customer.class, customerId);
            session.remove(customer);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public List<Customer> searchCustomerByName(String name) {
        List<Customer> customers = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = """
                    SELECT c FROM Customer c
                    WHERE c.fullname LIKE :name
                    """;
            Query<Customer> query = session.createQuery(hql, Customer.class);
            query.setParameter("name", "%" + name + "%");
            customers = query.getResultList();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public static void main(String[] args) {

    }
}
