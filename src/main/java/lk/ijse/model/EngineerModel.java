package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.dto.EngineerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EngineerModel {



    public static boolean saveEngineer(EngineerDTO dto) throws SQLException {
        Connection connection;
        connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO engineer VALUES(?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getEId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAddress());
        pstm.setString(4, dto.getTel());

        return pstm.executeUpdate() > 0;
    }

    public static boolean updateEngineer(EngineerDTO dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE enginner SET name = ?, address = ?, tel = ? WHERE e_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getAddress());
        pstm.setString(3, dto.getTel());
        pstm.setString(4, dto.getEId());

        return pstm.executeUpdate() > 0;
    }

    public static boolean deleteEngineer(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM engineer WHERE e_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }
    public static List<EngineerDTO> getAllEngineers() {
        List<EngineerDTO> engineers = new ArrayList<>();

        try (Connection connection = DbConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM engineer");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                EngineerDTO engineer = new EngineerDTO();
                engineer.setEId(resultSet.getString("e_id"));
                engineer.setName(resultSet.getString("name"));
                engineer.setAddress(resultSet.getString("address"));
                engineer.setTel(resultSet.getString("tel"));

                engineers.add(engineer);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return engineers;
    }

    public static EngineerDTO searchEngineer(String id) throws SQLException {

            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "SELECT * FROM engineer WHERE e_id = ?";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, id);
            ResultSet resultSet = pstm.executeQuery();

            EngineerDTO dto = null;
            if (resultSet.next()) {
                String engId = resultSet.getString(1);
                String engName = resultSet.getString(2);
                String engAddress = resultSet.getString(3);
                String engTel = resultSet.getString(4);

                dto = new EngineerDTO(engId, engName, engAddress, engTel);
            }
            return dto;
        }
    }

