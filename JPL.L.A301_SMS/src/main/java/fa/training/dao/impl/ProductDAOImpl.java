package fa.training.dao.impl;

import fa.training.connection.DBConnection;
import fa.training.dao.ProductDAO;
import fa.training.entities.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    public static Double getListPrice(int productId) {
        try {
            Connection conn = new DBConnection().getConnection();
            String sql = "SELECT list_price FROM Product WHERE product_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            return rs.getDouble("list_price");
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public List<Product> getAllProduct() {
        List<Product> listPro = new ArrayList<>();
        try {
            Connection conn = new DBConnection().getConnection();
            String sql = "SELECT * FROM Product;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                listPro.add(new Product(rs.getInt("product_id"), rs.getString("product_name"),
                        rs.getDouble("list_price")));
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return listPro;
    }
}
