package cz.dvorakv.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MovieDto {

    @JsonProperty("_id")
    private long id;
    private String name;
    private Long directorID;
    private List<@Positive Long> actorIDs;
    @JsonProperty("isAvailable")
    private boolean isAvailable;
    private List<String> genres;
    private Integer year;
    private Date dateAdded;

//    private PersonDto director;
//    private List<PersonDto> actors;

}
