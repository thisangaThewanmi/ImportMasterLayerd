package lk.ijse.bo;

import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    ArrayList<EmployeeDto> getAllEmployee() throws SQLException, ClassNotFoundException;
    boolean existEmployee(String id) throws SQLException, ClassNotFoundException;
    boolean saveEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException;
    boolean updateEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;
    String nextEmployeeId() throws SQLException, ClassNotFoundException;
    public char[] countEmployee() throws SQLException;

    Employee searchEmployee(String id) throws SQLException, ClassNotFoundException;
}

