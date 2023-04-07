package DAO;

import Entities.Category;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            categories = session.createQuery("FROM Category ", Category.class).getResultList();
        }
        return categories;
    }
}
