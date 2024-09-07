package cz.dvorakv.entity.repository;

import cz.dvorakv.entity.BorrowedEntity;
import cz.dvorakv.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author dvora
 * @since 13.7.2024
 */
public interface BorrowedRepository extends JpaRepository<BorrowedEntity, Long> {


}
