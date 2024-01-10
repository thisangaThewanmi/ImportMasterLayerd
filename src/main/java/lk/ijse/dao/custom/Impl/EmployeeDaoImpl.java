package lk.ijse.dao.custom.Impl;


import lk.ijse.dao.EmployeeDao;
import lk.ijse.dao.SQLUtil;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Employee;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDaoImpl implements EmployeeDao {


    @Override
    public boolean save(Employee  entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO Employee (emp_id,name, address,tel) VALUES (?,?,?,?)",
                entity.getId(),entity.getName(),entity.getAddress(),entity.getTel());

    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM Employee WHERE emp_id=?",
                id);
    }


    @Override
    public boolean update(Employee entity) throws SQLException {
        return SQLUtil.execute("UPDATE Employee SET name=?, address=? ,tel=? WHERE emp_id=?",
                entity.getName(),entity.getAddress(),entity.getTel(),entity.getId());
    }

    @Override
    public ArrayList<Employee> getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Employee");

        ArrayList<Employee> getAllEmployee = new ArrayList<>();

        while (rst.next()) {
             Employee entity = new Employee(rst.getString("emp_id"), rst.getString("name"), rst.getString("address"),rst.getString("tel"));
            getAllEmployee.add(entity);
        }

        return getAllEmployee;
    }

    @Override
    public boolean exsit(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.execute("SELECT id FROM Employee WHERE emp_id=?",
                id);
        return set.next();
    }

    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM Employee ORDER BY emp_id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newEmployeeId = Integer.parseInt(id.replace("E00-", "")) + 1;
            return String.format("E00-%03d", newEmployeeId);
        } else {
            return "E00-001";
        }
    }


    @Override
    public Employee search(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM employee WHERE emp_id = ?");


        Employee entity = null;
        while (rst.next()) {
            entity = new Employee(rst.getString("id"), rst.getString("name"), rst.getString("address"), rst.getString("tel"));

        }

        return entity;
    }

    @Override
    public char[] count() throws SQLException {
        return SQLUtil.execute("SELECT COUNT(emp_id) FROM employee;");
    }
}

