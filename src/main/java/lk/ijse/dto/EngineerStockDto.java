package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EngineerStockDto {
    private String esinId;
    private Date date;
    private String empId;

    private List<EngineerStockDetailsDto> detailsList;
}
