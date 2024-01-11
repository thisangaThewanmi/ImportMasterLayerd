package lk.ijse.dao.custom;

import lk.ijse.dto.tm.GrnTM;
import lk.ijse.entity.GRNDetails;

import java.sql.SQLException;
import java.util.List;

public interface GRNDetailDao extends CrudDao<GRNDetails> {

    boolean saveGrnOrderDetails(String orderId, List<GrnTM> stockTMList) throws SQLException;


    boolean saveOrderDetails(String grnId, GrnTM tm) throws SQLException;
}
