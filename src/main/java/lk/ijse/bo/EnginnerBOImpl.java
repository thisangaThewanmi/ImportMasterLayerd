package lk.ijse.bo;

import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.EngineerDao;
import lk.ijse.dto.EngineerDTO;
import lk.ijse.entity.Engineer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EnginnerBOImpl implements EngineerBO {

    EngineerDao engineerDao= (EngineerDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ENGINNER);


    @Override
    public ArrayList<EngineerDTO> getAllEngineers() throws SQLException, ClassNotFoundException {
        ArrayList<Engineer> allEngineers = engineerDao.getAll();
        ArrayList<EngineerDTO> engineerDTOS = new ArrayList<>();

        for (Engineer engineer : allEngineers) {
            engineerDTOS.add(new EngineerDTO(engineer.getEId(), engineer.getName(), engineer.getAddress(),engineer.getTel()));
        }

        return engineerDTOS;
    }


    @Override
    public boolean existEngineer(String id) throws SQLException, ClassNotFoundException {
        return engineerDao.exsit(id);
    }


    @Override
    public boolean saveEngineer(EngineerDTO dto) throws SQLException, ClassNotFoundException {
        return engineerDao.save(new Engineer(dto.getEId(),dto.getName(),dto.getAddress(),dto.getTel()));
    }

    @Override
    public boolean updateEnginner(EngineerDTO dto) throws SQLException, ClassNotFoundException {
        return engineerDao.update(new Engineer(dto.getEId(),dto.getName(),dto.getAddress(),dto.getTel()));
    }

    @Override
    public boolean deleteEnginner(String id) throws SQLException, ClassNotFoundException {
        return engineerDao.delete(id);
    }

    @Override
    public String nextEngineerId() throws SQLException, ClassNotFoundException {
        return engineerDao.nextId();
    }

    @Override
    public ResultSet countEngineer() throws SQLException {
        return engineerDao.count();
    }

    @Override
    public Engineer searchEngineer(String id) throws SQLException, ClassNotFoundException {
       return engineerDao.search(id);
    }
}
