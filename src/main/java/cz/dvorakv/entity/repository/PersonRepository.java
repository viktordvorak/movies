package cz.dvorakv.entity.repository;

import cz.dvorakv.constant.RoleType;
import cz.dvorakv.entity.PersonEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    List<PersonEntity> getAllByIdGreaterThan(long id);

    List<PersonEntity> getAllByRole(RoleType role, Pageable page);

    @Query(value = "SELECT * FROM person WHERE role=:#{#role.name()} LIMIT :limit", nativeQuery = true)
    List<PersonEntity> getAllByRole(@Param("role") RoleType role, @Param("limit") int limit);

}
