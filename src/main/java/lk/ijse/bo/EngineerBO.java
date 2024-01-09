package lk.ijse.bo;

import lk.ijse.dto.EmployeeDto;
import lk.ijse.dto.EngineerDTO;
import lk.ijse.entity.Employee;
import lk.ijse.entity.Engineer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EngineerBO extends SuperBO{
    ArrayList<EngineerDTO> getAllEngineers() throws SQLException, ClassNotFoundException;
    boolean existEngineer(String id) throws SQLException, ClassNotFoundException;
    boolean saveEngineer(EngineerDTO dto) throws SQLException, ClassNotFoundException;


    boolean updateEnginner(EngineerDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteEnginner(String id) throws SQLException, ClassNotFoundException;
    String nextEngineerId() throws SQLException, ClassNotFoundException;

    public char[] countEngineer() throws SQLException;

    Engineer searchEngineer(String id) throws SQLException, ClassNotFoundException;
}
