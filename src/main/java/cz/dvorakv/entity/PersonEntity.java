package cz.dvorakv.entity;

import cz.dvorakv.constant.RoleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author dvora
 * @since 21.10.2023
 */
@Getter
@Setter
@Entity(name = "Person")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;
    @Column
    private Date birthDate;
    @Column
    private String country;
    @Column
    private String biography;
    @OneToMany(mappedBy = "director")
    private List<MovieEntity> directedMovies;
    @ManyToMany(mappedBy = "actors")
    private List<MovieEntity> actedInMovies;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType role;

}
