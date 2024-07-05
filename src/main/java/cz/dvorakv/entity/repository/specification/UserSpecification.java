package cz.dvorakv.entity.repository.specification;

import cz.dvorakv.constant.UserRole;
import cz.dvorakv.entity.*;
import cz.dvorakv.entity.repository.filter.UserFilter;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UserSpecification  implements Specification<UserEntity> {

    private final UserFilter userFilter;

    @Override
    public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();

        if (userFilter.getRole() != null) {
            predicate = criteriaBuilder.and(predicate,
                    criteriaBuilder.equal(root.get(UserEntity_.admin), userFilter.getRole() == UserRole.ADMIN));
        }

        return predicate;
    }
}
