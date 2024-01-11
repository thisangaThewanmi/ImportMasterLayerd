package lk.ijse.bo;

import javafx.scene.control.Alert;
import lk.ijse.dao.*;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.EngineerDTO;
import lk.ijse.dto.MachineDto;
import lk.ijse.dto.MachineInstallDto;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Engineer;
import lk.ijse.entity.Machine;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlaceMachineInstallionBOImpl implements PlaceMachineInstallationBO {

    MachineInstallationDao machineInstallationDao = (MachineInstallationDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MACHINE_INSTALLATION);

    EngineerDao engineerDao = (EngineerDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ENGINNER);


    CustomerDao customerDao = (CustomerDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);


    MachineDao machineDao = (MachineDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MACHINE);
    public  boolean placeInstallation(MachineInstallDto machineInstallDto) throws SQLException {

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

            boolean isOrderSaved = machineInstallationDao.saveInstallation(MIid, machineId, engineerId, customerId, date, price);
            if (isOrderSaved) {
                boolean isUpdated = machineDao.updateMachineQty(machineInstallDto);
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

    @Override
    public Machine searchMachine(String id) throws SQLException, ClassNotFoundException {
      return  machineDao.search(id);
    }

    @Override
    public Customer searchCustomer(String id) throws SQLException, ClassNotFoundException {
       return customerDao.search(id);
    }

    @Override
    public Engineer searchEngineer(String id) throws SQLException, ClassNotFoundException {
       return engineerDao.search(id);
    }

    @Override
    public List<CustomerDto> getAllCustomers() throws SQLException {
        ArrayList<Customer> allCustomers = customerDao.getAll();
        ArrayList<CustomerDto> customerDTOS = new ArrayList<>();

        for (Customer customer : allCustomers) {
            customerDTOS.add(new CustomerDto(customer.getId(), customer.getName(), customer.getAddress(),customer.getTel()));
        }

        return customerDTOS;
    }

    @Override
    public List<MachineDto> getAllMachine() throws SQLException {
        ArrayList<Machine> allMachines = machineDao.getAll();
        ArrayList<MachineDto> machineDTOS = new ArrayList<>();

        for (Machine machine: allMachines) {
            machineDTOS.add(new MachineDto(machine.getId(), machine.getName(), machine.getQty(),machine.getPrice()));
        }

        return machineDTOS;
    }

    @Override
    public List<EngineerDTO> getAllEngineers() throws SQLException {
        ArrayList<Engineer> allEngineers = engineerDao.getAll();
        ArrayList<EngineerDTO> engineerDTOS = new ArrayList<>();

        for (Engineer engineer : allEngineers) {
            engineerDTOS.add(new EngineerDTO(engineer.getEId(), engineer.getName(), engineer.getAddress(),engineer.getTel()));
        }

        return engineerDTOS;
    }

    @Override
    public String generateNextMachineInstallationId() throws SQLException, ClassNotFoundException {
       return  machineInstallationDao.nextId();
    }

}
