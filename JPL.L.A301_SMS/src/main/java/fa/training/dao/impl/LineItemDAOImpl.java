package fa.training.dao.impl;

import fa.training.connection.DBConnection;
import fa.training.dao.LineItemDAO;
import fa.training.entities.Customer;
import fa.training.entities.LineItem;
import fa.training.entities.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LineItemDAOImpl implements LineItemDAO {
    @Override
    public List<LineItem> getAllItemsByOrderId(int orderId) {
        try {
            List<LineItem> list = new ArrayList<>();
            Connection conn = new DBConnection().getConnection();
            String sql = "select * from LineItem where order_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new LineItem(rs.getInt("order_id"), rs.getInt("product_id"), rs.getInt("quantity"), rs.getInt("price")));
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }

        return null;
    }

    @Override
    public boolean addLineItem(LineItem item) {
        try{
            Connection conn = new DBConnection().getConnection();
            String sql = "INSERT INTO LineItem(order_id, product_id, quantity, price) VALUES (?, ?, ?, ?);";
            String sql1 = "SELECT list_price FROM Product WHERE product_id = ?;";
            PreparedStatement statement1 = conn.prepareStatement(sql);
            PreparedStatement statement2 = conn.prepareStatement(sql1);

            statement2.setInt(1, item.getProductId());
            ResultSet rs = statement2.executeQuery();
            rs.next();
            double price = rs.getDouble(1) * item.getQuantity();

            statement1.setInt(1, item.getOrderId());
            statement1.setInt(2, item.getProductId());
            statement1.setInt(3, item.getQuantity());
            statement1.setDouble(4, price);
            statement1.executeUpdate();

            OrderDAOImpl orderDAO = new OrderDAOImpl();
            orderDAO.setTotal(item.getOrderId(), orderDAO.getTotal(item.getOrderId()) + price);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateItem(int orderId, int productId, int quantity, double price) {
        try{
            Connection conn = new DBConnection().getConnection();
            String sql = "UPDATE LineItem SET quantity = ?, price = ? WHERE order_id = ? AND product_id = ?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, quantity);
            statement.setDouble(2, price);
            statement.setInt(3, orderId);
            statement.setInt(4, productId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<LineItem> getAllItems() {
        try {
            List<LineItem> list = new ArrayList<>();
            Connection conn = new DBConnection().getConnection();
            String sql = "select * from LineItem";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new LineItem(rs.getInt("order_id"), rs.getInt("product_id"), rs.getInt("quantity"), rs.getInt("price")));
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }

        return null;
    }

    public boolean checkOrderIDExit(int OrdID){
        List<LineItem> lineItemList = new LineItemDAOImpl().getAllItems();
        for(LineItem lineItem : lineItemList){
            if(lineItem.getOrderId()==OrdID){
                return true;
            }
        }
        return false;
    }


}
