package BUS;

import DAO.CategoryDAO;
import DAO.VegetableDAO;
import Entities.Category;
import Entities.Vegetable;

import java.util.List;

public class VegetableBUS {
    private final VegetableDAO vegetableDAO;
    private final CategoryDAO categoryDAO;

    public VegetableBUS() {
        this.vegetableDAO = new VegetableDAO();
        this.categoryDAO =  new CategoryDAO();
    }

    public List<Vegetable> getVegetables() {
        return vegetableDAO.getVegetables();
    }

    public boolean addVegetable(Vegetable vegetable, int categoryID) {
        return vegetableDAO.addVegetable(vegetable, categoryID);
    }

    public boolean updateVegetable(Vegetable vegetable, int categoryID) {
        return vegetableDAO.updateVegetable(vegetable, categoryID);
    }

    public boolean deleteVegetable(int vegetableID) {
        return vegetableDAO.deleteVegetable(vegetableID);
    }

    public List<Vegetable> searchVegetableByName(String name) {
        return vegetableDAO.searchVegetableByName(name);
    }

    public Vegetable getById(int vegetableID) {
        return vegetableDAO.getById(vegetableID);
    }

    public List<Category> getCategories() {
        return categoryDAO.getCategories();
    }
}
