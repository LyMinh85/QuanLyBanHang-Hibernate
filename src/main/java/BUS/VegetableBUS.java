package BUS;

import DAO.VegetableDAO;
import Entities.Vegetable;

import java.util.List;

public class VegetableBUS {
    private VegetableDAO vegetableDAO;
    public VegetableBUS() {
        this.vegetableDAO = new VegetableDAO();
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
}
