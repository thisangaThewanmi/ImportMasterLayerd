package lk.ijse.dao;

import lk.ijse.dao.custom.CrudDao;
import lk.ijse.dto.MachineInstallDto;
import lk.ijse.dto.tm.MrnTM;
import lk.ijse.entity.Machine;

import java.sql.SQLException;
import java.util.List;

public interface MachineDao extends CrudDao<Machine> {

     boolean updateMachineQty(MachineInstallDto ob) throws SQLException;

    public  boolean updateQty2(List<MrnTM> list) throws SQLException ;

     boolean updateQty2(MrnTM ob) throws SQLException ;
}
