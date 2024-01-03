package lk.ijse.bo;

import lk.ijse.dao.CustomerDao;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dto.CustomerDto;
import lk.ijse.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO{

   CustomerDao customerDao = (CustomerDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);




    @Override
    public ArrayList<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allCustomers = customerDao.getAll();
        ArrayList<CustomerDto> customerDTOS = new ArrayList<>();

        for (Customer customer : allCustomers) {
            customerDTOS.add(new CustomerDto(customer.getId(), customer.getName(), customer.getAddress(),customer.getTel()));
        }

        return customerDTOS;
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDao.exsit(id);
    }

    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDao.save(new Customer(dto.getId(),dto.getName(),dto.getAddress(),dto.getTel()));
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDao.update(new Customer(dto.getId(),dto.getName(),dto.getAddress(),dto.getTel()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDao.delete(id);
    }

    @Override
    public String nextCustomerId() throws SQLException, ClassNotFoundException {
        return customerDao.nextId();
    }
}
