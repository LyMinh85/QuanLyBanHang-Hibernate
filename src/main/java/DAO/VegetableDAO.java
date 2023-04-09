package DAO;

import BUS.VegetableBUS;
import Entities.Category;
import Entities.Vegetable;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class VegetableDAO {
    public List<Vegetable> getVegetables() {
        List<Vegetable> vegetables = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = """
                    SELECT DISTINCT v FROM Vegetable v
                    INNER JOIN FETCH v.category c
                    """;
            vegetables = session.createQuery(hql, Vegetable.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vegetables;
    }

    public boolean addVegetable(Vegetable vegetable, int categoryID) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Category category = session.get(Category.class, categoryID);
            vegetable.setCategory(category);
            session.persist(vegetable);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateVegetable(Vegetable vegetable, int categoryID) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
         Transaction transaction = session.beginTransaction();
            Category category = session.find(Category.class, categoryID);
            vegetable.setCategory(category);
            session.merge(vegetable);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteVegetable(int vegetableId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Vegetable vegetable = session.find(Vegetable.class, vegetableId);
            session.remove(vegetable);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Vegetable> searchVegetableByName(String name) {
        List<Vegetable> vegetables = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = """
                    SELECT DISTINCT v FROM Vegetable v
                    INNER JOIN FETCH v.category c
                    WHERE v.vegetableName LIKE :vegetableName
                    """;
            Query<Vegetable> query = session.createQuery(hql, Vegetable.class);
            query.setParameter("vegetableName", "%" + name + "%");
            vegetables = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vegetables;
    }

    public Vegetable getById(int vegetableID) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(Vegetable.class, vegetableID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        VegetableBUS vegetableBUS = new VegetableBUS();

        List<Vegetable> vegetables = vegetableBUS.getVegetables();
        System.out.printf("%20s|%20s|%20s\n", "VegetableID", "VegetableName", "CategoryName");
        vegetables.forEach(vegetable -> {
            System.out.printf("%20s|%20s|%20s\n", vegetable.getVegetableID(), vegetable.getVegetableName(), vegetable.getCategory().getName());
        });


        vegetables = vegetableBUS.searchVegetableByName("n");
        System.out.printf("%20s|%20s|%20s\n", "VegetableID", "VegetableName", "CategoryName");
        vegetables.forEach(vegetable -> {
            System.out.printf("%20s|%20s|%20s\n", vegetable.getVegetableID(), vegetable.getVegetableName(), vegetable.getCategory().getName());
        });
    }
}
