package cz.dvorakv.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.dvorakv.constant.CustomerLabel;
import cz.dvorakv.constant.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    @JsonProperty("_id")
    private long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private CustomerLabel label;

}
