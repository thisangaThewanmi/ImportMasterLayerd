package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.MachineDto;
import lk.ijse.dto.MachineInstallDto;
import lk.ijse.dto.StockDto;
import lk.ijse.dto.tm.MrnTM;
import lk.ijse.dto.tm.StockTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MachineModel {

    public static boolean saveMachine(MachineDto dto) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql ="INSERT INTO machine VALUES (?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, String.valueOf(dto.getQty()));
        pstm.setString(4, String.valueOf(dto.getPrice()));

        return pstm.executeUpdate() > 0;
    }

    public static List<MachineDto> getAllMachines() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM machine";
        PreparedStatement ps = connection.prepareStatement(sql);
        List<MachineDto> allMachines = new ArrayList<>();
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            allMachines.add(new MachineDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4)

            ));
        }
        return allMachines;
    }

    public static boolean deleteMachine(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM stock WHERE m_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static  MachineDto searchMachine(String id) throws SQLException {

            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "SELECT * FROM machine WHERE m_id = ?";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, id);
            ResultSet resultSet = pstm.executeQuery();

            MachineDto dto = null;
            if (resultSet.next()) {
                String m_id = resultSet.getString(1);
                String m_name = resultSet.getString(2);
                Integer qty = Integer.valueOf(String.valueOf(resultSet.getInt(3)));
                Double unitPrice = Double.valueOf(String.valueOf(resultSet.getDouble(4)));




                dto = new MachineDto(m_id, m_name, qty, unitPrice);
            }
            return dto;
    }




    public static boolean updateMachineQty(MachineInstallDto ob) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE machine SET qty_ = qty_ - 1 WHERE m_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, ob.getMachineId());
        return pstm.executeUpdate() > 0;
    }


    public static String generateNextMRId() throws SQLException {

            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "SELECT mrn_id FROM  mrn ORDER BY mrn_id DESC LIMIT 1";
            PreparedStatement pstm = connection.prepareStatement(sql);

            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()) {
                return splitOrderId(resultSet.getString(1));
            }
            return splitOrderId(null);
        }

        private static String splitOrderId(String currentOrderId) {
            if (currentOrderId != null) {
                String[] split = currentOrderId.split("MR");

                // Ensure that the array has at least two elements
                int id;
                if (split.length > 1) {
                    id = Integer.parseInt(split[1]); //01
                } else {

                    id = 0;
                }

                id++;
                return "MR00" + id;
            } else {
                // Handle the case when currentOrderId is null
                return "MR001";
            }
        }


        public static boolean updateQty2(List<MrnTM> list) throws SQLException {
        for (MrnTM ob : list) {
            if (!updateQty2(ob)){
                return false;
            }
        }
        return true;
    }

    private static boolean updateQty2(MrnTM ob) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE machine SET qty_ = qty_ + ? WHERE m_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, ob.getQty());
        pstm.setString(2, ob.getMachineId());
        return pstm.executeUpdate() > 0;
    }

    public char[] countMachines() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT COUNT(*) AS machine_count FROM machine;";

        // Prepare the statement
        PreparedStatement pstm = connection.prepareStatement(sql);

        // Execute the query
        ResultSet resultSet = pstm.executeQuery();

        // Process the result set
        int machineCount = 0;
        if (resultSet.next()) {
            machineCount = resultSet.getInt("machine_count");
        }



        // Convert the supplier count to char array
        char[] resultCharArray = String.valueOf(machineCount).toCharArray();

        return resultCharArray;
    }
    }


