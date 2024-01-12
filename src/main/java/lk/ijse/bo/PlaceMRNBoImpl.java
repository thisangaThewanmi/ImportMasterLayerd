package lk.ijse.bo;

import lk.ijse.dao.*;
import lk.ijse.dao.custom.MRNDao;
import lk.ijse.dao.custom.MRNDetailsDao;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.MachineDto;
import lk.ijse.dto.PlaceMrnDto;
import lk.ijse.dto.supDto;
import lk.ijse.entity.Machine;
import lk.ijse.entity.Supplier;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDate.parse;

public class PlaceMRNBoImpl implements PlaceMrnBO {

    MachineDao machineDao = (MachineDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MACHINE);

    MRNDao mrnDao = (MRNDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MRN);

    MRNDetailsDao mrnDetailsDao = (MRNDetailsDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MRNDETAILS);

    SupplierDao supplierDao = (SupplierDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    public  boolean placeMRNOrder(PlaceMrnDto dto) throws SQLException {

        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isGrnOrderSaved = mrnDao.saveMRN(dto.getMRNId(), dto.getMRNdate(), dto.getSupplierId(),dto.getTotal());
            if (isGrnOrderSaved) {
                boolean isUpdated = machineDao.updateQty2(dto.getMachineGrnTMS());
                if (isUpdated) {
                    boolean isMrnOrderDetailSaved = mrnDetailsDao.saveMrnDetails(dto.getMRNId(), dto.getMachineGrnTMS());
                    if (isMrnOrderDetailSaved) {
                        connection.commit();
                    }
                }
            }

            connection.rollback();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }


    public String nextMachineId() throws SQLException, ClassNotFoundException {

          return  mrnDao.nextId();

    }

    public List<supDto> getAllSuppliers() throws SQLException {
        ArrayList<Supplier> allSuppliers = supplierDao.getAll();
        ArrayList<supDto> supplierDTOS = new ArrayList<>();

        for (Supplier supplier : allSuppliers) {
            supplierDTOS.add(new supDto(supplier.getId(), supplier.getName(),supplier.getAddress(),supplier.getTel()));
        }

        return supplierDTOS;
    }

    public List<MachineDto> getAllMachine() throws SQLException {
        ArrayList<Machine> allMachines = machineDao.getAll();
        ArrayList<MachineDto> machineDTOS = new ArrayList<>();

        for (Machine machine: allMachines) {
            machineDTOS.add(new MachineDto(machine.getId(), machine.getName(), machine.getQty(),machine.getPrice()));
        }

        return machineDTOS;
    }

    public Machine searchMachine(String id) throws SQLException, ClassNotFoundException {
        return machineDao.search(id);
    }

    public Supplier searchSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDao.search(id);
    }
}

