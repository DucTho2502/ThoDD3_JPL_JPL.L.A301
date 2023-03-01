package fa.training.dao.impl;

import fa.training.connection.DBConnection;
import fa.training.dao.OrderDAO;
import fa.training.entities.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO{

    @Override
    public List<Order> getAllOrdersByCustomerId(int customerId) {
        try {
            List<Order> list = new ArrayList<>();
            Connection conn = new DBConnection().getConnection();
            String sql = "select * from Orders where customer_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Order(rs.getInt("order_id"), rs.getDate("order_date"), rs.getInt("customer_id"), rs.getInt("employee_id"), rs.getInt("total")));
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }

        return null;
    }

    public List<Order> getAllOrder() {
        try {
            List<Order> list = new ArrayList<>();
            Connection conn = new DBConnection().getConnection();
            String sql = "select * from Orders";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Order(rs.getInt("order_id"), rs.getDate("order_date"), rs.getInt("customer_id"), rs.getInt("employee_id"), rs.getInt("total")));
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }

        return null;
    }

    @Override
    public Double computeOrderTotal(int orderId) {
        try {
            Connection conn = new DBConnection().getConnection();
            String sql = "SELECT 'total_price' = SUM(price) FROM LineItem WHERE order_id = ? GROUP BY order_id;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getDouble(1);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public boolean addOrder(Order order) {
        try {
            Connection conn = new DBConnection().getConnection();
            String sql = "INSERT INTO Orders(customer_id, employee_id) VALUES (?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, order.getCustomerId());
            ps.setInt(2, order.getEmployeeId());
            ResultSet rs = ps.executeQuery();
            rs.next();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return false;
    }

    @Override
    public Double getTotal(int orderId) {
        try {
            Connection conn = new DBConnection().getConnection();
            String sql = "SELECT total FROM Orders WHERE order_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getDouble("total");
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public boolean setTotal(int orderId, double total) {
        try {
            Connection conn = new DBConnection().getConnection();
            String sql = "UPDATE Orders SET total = ? WHERE order_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, total);
            ps.setInt(2, orderId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return false;
    }
}
