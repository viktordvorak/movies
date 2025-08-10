package cz.dvorakv.entity;

import cz.dvorakv.constant.BorrowStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
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

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate borrowedDate;

    @Temporal(TemporalType.DATE)
    private LocalDate returnedDate;
    private BigDecimal fine;
    @Enumerated(EnumType.STRING)
    private BorrowStatus status;

}
