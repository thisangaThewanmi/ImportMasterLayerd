package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.StockDto;
import lk.ijse.dto.supDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class supModel {

    public static char[] countSuppliers() throws SQLException {



            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "SELECT COUNT(*) AS supplier_count FROM supplier;";

            // Prepare the statement
            PreparedStatement pstm = connection.prepareStatement(sql);

            // Execute the query
            ResultSet resultSet = pstm.executeQuery();

            // Process the result set
            int supplierCount = 0;
            if (resultSet.next()) {
                supplierCount = resultSet.getInt("supplier_count");
            }



            // Convert the supplier count to char array
            char[] resultCharArray = String.valueOf(supplierCount).toCharArray();

            return resultCharArray;
        }



    public boolean SaveSupplier(supDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO supplier VALUES(?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAddress());
        pstm.setString(4, dto.getTel());

        return pstm.executeUpdate() > 0;

    }


    public boolean updateCustomer(supDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE supplier SET name = ?, address = ?, tel = ? WHERE sup_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getAddress());
        pstm.setString(3, dto.getTel());
        pstm.setString(4, dto.getId());

        return pstm.executeUpdate() > 0;
    }

    public boolean deleteSupplier(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM supplier WHERE sup_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static supDto searchSupplier(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM supplier WHERE sup_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet resultSet = pstm.executeQuery();

        supDto dto = null;
        if (resultSet.next()) {
            String supId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);


            dto = new supDto(supId, name, address, tel);
        }
        return dto;
    }


    public List<supDto> getAllSuppliers() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM supplier";

        PreparedStatement ps = connection.prepareStatement(sql);
        List<supDto> allSuppliers = new ArrayList<>();
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            allSuppliers.add(new supDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return allSuppliers;


    }
}









