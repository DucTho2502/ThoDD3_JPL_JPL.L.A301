package fa.training.dao.impl;

import fa.training.connection.DBConnection;
import fa.training.dao.CustomerDAO;
import fa.training.entities.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public List<Customer> getAllCustomer() {
        try {
            List<Customer> list = new ArrayList<>();

            Connection conn = new DBConnection().getConnection();
            String sql = "select * from Customer";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Customer(rs.getInt("customer_id"), rs.getString("customer_name")));
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }

        return null;
    }

    public boolean checkCustomerExit(int cusID){
        List<Customer> customerList = new CustomerDAOImpl().getAllCustomer();
        for(Customer customer : customerList){
            if(customer.getCustomerId()==cusID){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addCustomer(Customer customer) {
        try {
            Connection conn = new DBConnection().getConnection();
            String sql = "insert into Customer (customer_name) values (?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, customer.getCustomerName());
            ResultSet rs = ps.executeQuery();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return false;
    }

    @Override
    public boolean deleteCustomer(int cuntomerID) {
        try {
            Connection conn = new DBConnection().getConnection();
            String sql = "DELETE FROM LineItem WHERE order_id IN (SELECT order_id FROM Orders WHERE customer_id = ?)";
            String sql1 = "delete from Orders where customer_id = ?";
            String sql2 = "delete from Customer where customer_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            PreparedStatement ps2 = conn.prepareStatement(sql1);
            ps.setInt(1, cuntomerID);
            ps1.setInt(1, cuntomerID);
            ps2.setInt(1, cuntomerID);
            ps.executeQuery();
            ps1.executeQuery();
            ps2.executeQuery();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return false;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        try {
            Connection conn = new DBConnection().getConnection();
            String sql = "update Customer set customer_id=? , customer_name=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, customer.getCustomerId());
            ps.setString(2, customer.getCustomerName());
            ResultSet rs = ps.executeQuery();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return false;
    }

    @Override
    public Customer getCustomer(int customerId) {
        Customer customer = new Customer();
        try {
            Connection conn = new DBConnection().getConnection();
            String sql = "SELECT * FROM Customer WHERE customer_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            customer.setCustomerId(rs.getInt("customer_id"));
            customer.setCustomerName(rs.getString("customer_name"));
            return customer;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }
}
