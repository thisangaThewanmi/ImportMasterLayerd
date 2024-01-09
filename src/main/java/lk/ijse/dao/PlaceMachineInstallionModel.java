package lk.ijse.dao;

import javafx.scene.control.Alert;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.MachineInstallDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class PlaceMachineInstallionModel {
    public static boolean placeInstallation(MachineInstallDto machineInstallDto) throws SQLException {

        String MIid = machineInstallDto.getMIid();
        LocalDate date = machineInstallDto.getDate();
        String machineId = machineInstallDto.getMachineId();
        String machineName = machineInstallDto.getMachineName();
        String customerId = machineInstallDto.getCustomerId();
        String customerName = machineInstallDto.getCustomerName();
        String engineerId = machineInstallDto.getEngineerId();
        String engineerName = machineInstallDto.getEngineerName();

        double price = machineInstallDto.getPrice();


        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderSaved = MachineInstallationModel.saveInstallation(MIid, machineId, engineerId, customerId, date, price);
            if (isOrderSaved) {
                boolean isUpdated = MachineDaoImpl.updateMachineQty(machineInstallDto);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Machine Installation is Successful").show();
                        connection.commit();
                    }
                }


            connection.rollback();


        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } finally {
            connection.setAutoCommit(true);
        }
        return true;
    }

}
