package lk.ijse.dao;

import lk.ijse.dao.custom.CrudDao;
import lk.ijse.entity.MachineInstallation;

import java.sql.SQLException;
import java.time.LocalDate;

public interface MachineInstallationDao extends CrudDao<MachineInstallation> {

     boolean saveInstallation(String MIid, String machineId, String engineerId, String customerId, LocalDate date, double price) throws SQLException ;
}
