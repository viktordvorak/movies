package cz.dvorakv.entity;

import cz.dvorakv.constant.CustomerLabel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author dvora
 * @since 5.7.2024
 */
@Getter
@Setter
@Entity(name = "Customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private Date birthDate;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CustomerLabel label;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BorrowedEntity> borrowedMovies;

}
