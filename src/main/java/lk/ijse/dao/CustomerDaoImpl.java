package lk.ijse.dao;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.CustomerDto;
import lk.ijse.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {



/*
    public static boolean deleteCustomer(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM customer WHERE id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static boolean updateCustomer(CustomerDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE customer SET name = ?, address = ?, tel = ? WHERE id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        String name = dto.getName();
        String address = dto.getAddress();
        String tel = dto.getTel();
        String id = dto.getId();

        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getAddress());
        pstm.setString(3, dto.getTel());
        pstm.setString(4, dto.getId());

        return pstm.executeUpdate() > 0;
    }

    public static List<CustomerDto> getAllCustomers() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        List<CustomerDto> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                CustomerDto customer = new CustomerDto(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("tel")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return customers;
    }


    public static CustomerDto searchCustomer(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM customer WHERE id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet resultSet = pstm.executeQuery();

        CustomerDto dto = null;
        if (resultSet.next()) {
            String cusId = resultSet.getString(1);
            String cusName = resultSet.getString(2);
            String cusAddress = resultSet.getString(3);
            String cusTelNo = resultSet.getString(4);

            dto = new CustomerDto(cusId, cusName, cusAddress, cusTelNo);
        }
        return dto;
}

    public char[] countCustomers() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT COUNT(*) AS customer_count FROM customer;";

        // Prepare the statement
        PreparedStatement pstm = connection.prepareStatement(sql);

        // Execute the query
        ResultSet resultSet = pstm.executeQuery();

        // Process the result set
        int CustomerCount = 0;
        if (resultSet.next()) {
            CustomerCount = resultSet.getInt("customer_count");
        }



        // Convert the supplier count to char array
        char[] resultCharArray = String.valueOf(CustomerCount).toCharArray();

        return resultCharArray;
    }
*/

    @Override
    public boolean save(Customer entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO Customer (id,name, address,tel) VALUES (?,?,?,?)",
                entity.getId(),entity.getName(),entity.getAddress(),entity.getTel());

    }


    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM customer WHERE id=?",
                id);
    }

    @Override
    public boolean update(Customer entity) throws SQLException {
        return SQLUtil.execute("UPDATE customer SET name=?, address=? ,tel=? WHERE id=?",
                entity.getName(),entity.getAddress(),entity.getTel(),entity.getId());
    }

    @Override
    public ArrayList getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer");

        ArrayList<Customer> getAllCustomer = new ArrayList<>();

        while (rst.next()) {
            Customer entity = new Customer(rst.getString("id"), rst.getString("name"), rst.getString("address"),rst.getString("tel"));
            getAllCustomer.add(entity);
        }

        return getAllCustomer;
    }

    @Override
    public boolean exsit(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.execute("SELECT id FROM customer WHERE id=?",
                id);
        return set.next();
    }

    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM customer ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }


    @Override
    public Customer search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public char[] count() throws SQLException {
        return SQLUtil.execute("SELECT COUNT(id) FROM customer;");
    }
}


