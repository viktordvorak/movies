package cz.dvorakv.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowedDto {

    @JsonProperty("_id")
    private long id;
    private Long customerID;
    private Long movieID;
    private LocalDate borrowedDate;
    private LocalDate returnedDate;

}
