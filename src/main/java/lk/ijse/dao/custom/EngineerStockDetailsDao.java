package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDao;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.EngineerStockDetailsDto;
import lk.ijse.dto.EngineerStockDto;
import lk.ijse.entity.EngineerStockDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EngineerStockDetailsDao extends CrudDao<EngineerStockDetails> {

     boolean saveEngineerStockDetails(List<EngineerStockDetailsDto> list) throws SQLException, ClassNotFoundException;

     boolean saveEngineerStockDetails(EngineerStockDetailsDto engineerStockDetailsDto) throws SQLException, ClassNotFoundException;

      ArrayList<EngineerStockDetails> getAllEngineerStockDetails () throws SQLException ;
}
