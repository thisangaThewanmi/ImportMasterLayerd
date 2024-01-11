package lk.ijse.bo;

import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.EngineerDTO;
import lk.ijse.dto.MachineDto;
import lk.ijse.dto.MachineInstallDto;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Engineer;
import lk.ijse.entity.Machine;

import java.sql.SQLException;
import java.util.List;

public interface PlaceMachineInstallationBO extends SuperBO{

    public  boolean placeInstallation(MachineInstallDto machineInstallDto) throws SQLException;

    Machine searchMachine(String id) throws SQLException, ClassNotFoundException;

    Customer searchCustomer(String id) throws SQLException, ClassNotFoundException;

    Engineer searchEngineer(String id) throws SQLException, ClassNotFoundException;

    List<CustomerDto> getAllCustomers() throws SQLException;

    List<MachineDto> getAllMachine() throws SQLException;

    List<EngineerDTO> getAllEngineers() throws SQLException;

    String generateNextMachineInstallationId() throws SQLException, ClassNotFoundException;
}
