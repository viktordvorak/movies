package cz.dvorakv.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * @author dvora
 * @since
 */
@Getter
@Setter
@Entity(name = "Borrowed")
public class BorrowedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private CustomerEntity customer;

    @ManyToOne
    private MovieEntity movie;

    //@ManyToOne
    //@JoinColumn(name = "movie_id", nullable = false)
//    @JoinTable(name = "borrowed_movies",
//            joinColumns = @JoinColumn(name = "borrowed_id"),
//            inverseJoinColumns = @JoinColumn(name = "movie_id"))
//    private List<MovieEntity> movies;
//    @ManyToOne
//    @JoinColumn(name = "movie_id", nullable = false)
//    private MovieEntity movie;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate borrowedDate;

    @Temporal(TemporalType.DATE)
    private LocalDate returnedDate;

}
