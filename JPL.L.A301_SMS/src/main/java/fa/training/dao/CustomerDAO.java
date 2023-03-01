package fa.training.dao;

import fa.training.entities.Customer;

import java.util.List;

public interface CustomerDAO {

    public List<Customer> getAllCustomer();

    public boolean addCustomer(Customer customer);

    public boolean deleteCustomer(int customerID);

    public boolean updateCustomer(Customer customer);

    public Customer getCustomer(int customerId);


}
