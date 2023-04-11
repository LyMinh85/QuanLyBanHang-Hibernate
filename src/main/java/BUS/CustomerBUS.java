package BUS;

import DAO.CustomerDAO;
import Entities.Customer;

import java.util.List;

public class CustomerBUS {
    private final CustomerDAO customerDAO;
    public CustomerBUS() {
        this.customerDAO = new CustomerDAO();
    }

    public List<Customer> getCustomers() {
        return customerDAO.getCustomers();
    }

    public boolean addCustomer(Customer customer) {
        return customerDAO.addCustomer(customer);
    }

    public boolean updateCustomer(Customer customer) {
        return customerDAO.updateCustomer(customer);
    }

    public boolean deleteCustomer(int customerID) {
        return customerDAO.deleteCustomer(customerID);
    }

    public List<Customer> searchCustomerByName(String name) {
        return customerDAO.searchCustomerByName(name);
    }
}
