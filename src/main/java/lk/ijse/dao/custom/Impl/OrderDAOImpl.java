package lk.ijse.dao.custom.Impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.OrderDao;
import lk.ijse.entity.Order;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDao {
   /* public static String generateNextOrderId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    private static String splitOrderId(String currentOrderId) {
        if(currentOrderId != null) {
            String[] split = currentOrderId.split("O0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            return "O00" + id;
        } else {
            return "O001";
        }
    }

    public static boolean saveOrder(String orderId, String machineId, String engineerId, String customerId, LocalDate date) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO orders VALUES(?, ?, ?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, orderId);
        pstm.setString(2,engineerId);
        pstm.setString(3, machineId);
        pstm.setDate(4, Date.valueOf(date));
        pstm.setString(5, customerId);

        return pstm.executeUpdate() > 0;
    }

    public char[] countOrders() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT COUNT(*) AS order_count FROM orders;";

        // Prepare the statement
        PreparedStatement pstm = connection.prepareStatement(sql);

        // Execute the query
        ResultSet resultSet = pstm.executeQuery();

        // Process the result set
        int OrderCount = 0;
        if (resultSet.next()) {
            OrderCount = resultSet.getInt("order_count");
        }



        // Convert the supplier count to char array
        char[] resultCharArray = String.valueOf(OrderCount).toCharArray();

        return resultCharArray;
    }*/

    @Override
    public boolean save(Order dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Order dto) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<Order> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean exsit(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("orderId");
            int newCustomerId = Integer.parseInt(id.replace("O00-", "")) + 1;
            return String.format("O00-%03d", newCustomerId);
        } else {
            return "O00-001";
        }
    }

    @Override
    public Order search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public char[] count() throws SQLException {
        return SQLUtil.execute("SELECT COUNT(*) AS order_count FROM orders;");
    }

    @Override
    public boolean save(String orderId, String MachineId, String engineerId, LocalDate orderDate, String customerId) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO orders (orderId, machineId, engineerId,date, customerId) VALUES (?,?,?,?,?)",
                orderId, MachineId, engineerId, orderDate, customerId);
    }
}
