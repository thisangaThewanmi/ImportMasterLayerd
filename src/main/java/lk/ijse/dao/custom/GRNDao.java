package lk.ijse.dao.custom;

import lk.ijse.entity.GRN;

import java.sql.SQLException;
import java.time.LocalDate;

public interface GRNDao extends CrudDao<GRN> {
     boolean saveOrder(String grnId, LocalDate date, String supplierId, String supplierName, Double total) throws SQLException ;

    }
