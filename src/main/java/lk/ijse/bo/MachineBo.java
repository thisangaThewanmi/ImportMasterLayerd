package lk.ijse.bo;

import lk.ijse.dto.MachineDto;
import lk.ijse.entity.Machine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface MachineBo extends SuperBO{
    ArrayList<MachineDto> getAllMachine() throws SQLException, ClassNotFoundException;
    boolean existMachine(String id) throws SQLException, ClassNotFoundException;
    boolean saveMachine(MachineDto dto) throws SQLException, ClassNotFoundException;
    boolean updateMachine(MachineDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteMachine(String id) throws SQLException, ClassNotFoundException;
    String nextMachineId() throws SQLException, ClassNotFoundException;

    public ResultSet countMachine() throws SQLException;

    Machine searchMachine(String id) throws SQLException, ClassNotFoundException;


}
