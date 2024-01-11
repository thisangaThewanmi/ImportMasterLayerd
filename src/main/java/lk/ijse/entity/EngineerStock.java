package lk.ijse.entity;

import lk.ijse.dto.EngineerStockDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EngineerStock {
    private String esinId;
    private Date date;
    private String empId;

    private List<EngineerStockDetailsDto> detailsList;
}
