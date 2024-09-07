package cz.dvorakv.entity.repository;

import cz.dvorakv.entity.CustomerEntity;
import cz.dvorakv.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author dvora
 * @since 5.7.2024
 */
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {


}
