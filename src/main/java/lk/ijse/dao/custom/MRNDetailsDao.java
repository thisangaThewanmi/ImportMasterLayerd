package lk.ijse.dao.custom;

import lk.ijse.dto.tm.MrnTM;
import lk.ijse.entity.MRNDetails;

import java.sql.SQLException;
import java.util.List;

public interface MRNDetailsDao extends CrudDao<MRNDetails> {

     boolean saveMrnDetails(String mrnId, List<MrnTM> machineTMList) throws SQLException;

     boolean saveMrnDetails(String mrnId, MrnTM tm) throws SQLException ;
}
