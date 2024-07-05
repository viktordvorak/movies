package cz.dvorakv.entity.repository.filter;

import lombok.Data;

@Data
public class MovieFilter {

    private Long directorID;
    private Long actorID;
    private String genre;
    private Integer fromYear;
    private Integer toYear;
    private Availability availability;
    //private Integer limit = 10;

    public enum Availability {
        AVAILABLE,UNAVAILABLE
    }

}
