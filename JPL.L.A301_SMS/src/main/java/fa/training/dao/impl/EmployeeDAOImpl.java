package fa.training.dao.impl;

import fa.training.connection.DBConnection;
import fa.training.dao.EmployeeDAO;
import fa.training.entities.Customer;
import fa.training.entities.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> list = new ArrayList<>();
        try {
            Connection conn = new DBConnection().getConnection();
            String sql = "SELECT * FROM Employee;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Employee(rs.getInt("employee_id"), rs.getString("employee_name"),
                        rs.getDouble("salary"), rs.getInt("supervisor_id")));
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return list;
    }

}
