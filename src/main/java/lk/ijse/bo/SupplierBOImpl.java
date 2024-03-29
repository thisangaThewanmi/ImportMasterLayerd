package lk.ijse.bo;

import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SupplierDao;
import lk.ijse.dto.supDto;
import lk.ijse.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {

    SupplierDao supplierDao = (SupplierDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    @Override
    public ArrayList<supDto> getAllSuppliers() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> allSuppliers = supplierDao.getAll();
        ArrayList<supDto> supplierDTOS = new ArrayList<>();

        for (Supplier supplier : allSuppliers) {
            supplierDTOS.add(new supDto(supplier.getId(), supplier.getName(),supplier.getAddress(),supplier.getTel()));
        }

        return supplierDTOS;
    }

    @Override
    public boolean existSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDao.exsit(id);
    }

    @Override
    public boolean saveSupplier(supDto dto) throws SQLException, ClassNotFoundException {
        return supplierDao.save(new Supplier(dto.getId(),dto.getName(),dto.getAddress(),dto.getTel()));
    }

    @Override
    public boolean updateSupplier(supDto dto) throws SQLException, ClassNotFoundException {
        return supplierDao.update(new Supplier(dto.getId(),dto.getName(),dto.getAddress(),dto.getTel()));
    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDao.delete(id);
    }

    @Override
    public String nextSupplierId() throws SQLException, ClassNotFoundException {
        return supplierDao.nextId();
    }

    @Override
    public ResultSet countSupplier() throws SQLException {
        return supplierDao.count();
    }

    @Override
    public Supplier searchSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDao.search(id);
    }
}

