package lk.ijse.bo;

import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.MachineDao;

import lk.ijse.dto.MachineDto;
import lk.ijse.entity.Machine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MachineBoImpl implements MachineBo {

    MachineDao machineDao = (MachineDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MACHINE);

    @Override
    public ArrayList<MachineDto> getAllMachine() throws SQLException, ClassNotFoundException {
        ArrayList<Machine> allMachines = machineDao.getAll();
        ArrayList<MachineDto> machineDTOS = new ArrayList<>();

        for (Machine machine: allMachines) {
            machineDTOS.add(new MachineDto(machine.getId(), machine.getName(), machine.getQty(),machine.getPrice()));
        }

        return machineDTOS;
    }

    @Override
    public boolean existMachine(String id) throws SQLException, ClassNotFoundException {
        return machineDao.exsit(id);
    }

    @Override
    public boolean saveMachine(MachineDto dto) throws SQLException, ClassNotFoundException {
        return machineDao.save(new Machine(dto.getId(),dto.getName(),dto.getQty(),dto.getPrice()));
    }

    @Override
    public boolean updateMachine(MachineDto dto) throws SQLException, ClassNotFoundException {
        return machineDao.update(new Machine(dto.getId(),dto.getName(),dto.getQty(),dto.getPrice()));
    }

    @Override
    public boolean deleteMachine(String id) throws SQLException, ClassNotFoundException {
        return machineDao.delete(id);
    }

    @Override
    public String nextMachineId() throws SQLException, ClassNotFoundException {
        return machineDao.nextId();
    }

    @Override
    public ResultSet countMachine() throws SQLException {
        return machineDao.count();
    }

    @Override
    public Machine searchMachine(String id) throws SQLException, ClassNotFoundException {
        return machineDao.search(id);
    }
}
