package lk.ijse.bo;

import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.EmployeeDao;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDao employeeDao = (EmployeeDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public ArrayList<EmployeeDto> getAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> allEmployeess =employeeDao.getAll();
        ArrayList<EmployeeDto> employeesDTOS = new ArrayList<>();

        for (Employee employee : allEmployeess) {
            employeesDTOS.add(new EmployeeDto(employee.getId(), employee.getName(), employee.getAddress(),employee.getTel()));
        }

        return employeesDTOS;

    }

    @Override
    public boolean existEmployee(String id) throws SQLException, ClassNotFoundException {
       return employeeDao.exsit(id);
    }

    @Override
    public boolean saveEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return employeeDao.save(new Employee(dto.getId(),dto.getName(),dto.getAddress(),dto.getTel()));
    }

    @Override
    public boolean updateEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return employeeDao.update(new Employee(dto.getId(),dto.getName(),dto.getAddress(),dto.getTel()));
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDao.delete(id);
    }

    @Override
    public String nextEmployeeId() throws SQLException, ClassNotFoundException {
       return employeeDao.nextId();
    }

    @Override
    public char[] countEmployee() throws SQLException {
        return employeeDao.count();
    }

    @Override
    public Employee searchEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDao.search(id);
    }
}
