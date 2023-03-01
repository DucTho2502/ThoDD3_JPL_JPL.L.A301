package fa.training.dao;

import fa.training.entities.Customer;
import fa.training.entities.Order;

import java.util.List;

public interface OrderDAO {

    public List<Order> getAllOrdersByCustomerId(int customerId);

    Double computeOrderTotal(int orderId);

    public boolean addOrder(Order order);

    public Double getTotal(int orderId);

    public boolean setTotal(int orderId, double total);
}
