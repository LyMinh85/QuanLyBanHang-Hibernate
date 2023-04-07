package DAO;

import BUS.VegetableBUS;
import Entities.Category;
import Entities.Vegetable;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class VegetableDAO {
    public List<Vegetable> getVegetables() {
        List<Vegetable> vegetables = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Vegetable> cr = cb.createQuery(Vegetable.class);
            Root<Vegetable> root = cr.from(Vegetable.class);
            root.fetch("category");
            cr.select(root).orderBy(cb.asc(root.get("vegetableID")));
            vegetables = session.createQuery(cr).getResultList();
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
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Vegetable> cr = cb.createQuery(Vegetable.class);
            Root<Vegetable> root = cr.from(Vegetable.class);
            root.fetch("category");
            cr.select(root).where(cb.like(root.get("vegetableName"), "%" + name  + "%"));
            vegetables = session.createQuery(cr).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vegetables;
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
