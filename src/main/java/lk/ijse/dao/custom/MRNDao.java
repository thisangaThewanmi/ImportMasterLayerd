package lk.ijse.dao.custom;

import lk.ijse.entity.MRN;

import java.sql.SQLException;
import java.time.LocalDate;

public interface MRNDao extends CrudDao<MRN> {

     boolean saveMRN(String mrnId, LocalDate date, String supId, Double total) throws SQLException ;

    }
