package cz.dvorakv.entity.repository.specification;

import cz.dvorakv.constant.UserRole;
import cz.dvorakv.entity.*;
import cz.dvorakv.entity.repository.filter.MovieFilter;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class MovieSpecification implements Specification<MovieEntity> {

    private final MovieFilter filter;

    @Override
    public Predicate toPredicate(Root<MovieEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getFromYear() != null) {
            predicates.add(criteriaBuilder.greaterThan(root.get(MovieEntity_.year), filter.getFromYear()));
        }

        if (filter.getToYear() != null) {
            predicates.add(criteriaBuilder.greaterThan(root.get(MovieEntity_.year), filter.getToYear()));
        }


        if (filter.getDirectorID() != null) {
            Join<PersonEntity, MovieEntity> directorJoin = root.join(MovieEntity_.DIRECTOR);
            predicates.add(criteriaBuilder.equal(directorJoin.get(PersonEntity_.ID), filter.getDirectorID()));
        }

        if (filter.getActorID() != null) {
            Join<MovieEntity, PersonEntity> actorJoin = root.join(MovieEntity_.ACTORS);
            predicates.add(actorJoin.get(PersonEntity_.ID).in(filter.getActorID()));
        }

        if (filter.getGenre() != null) {
            Expression<String> genreJoin = root.join(MovieEntity_.GENRES);
            predicates.add(genreJoin.in(filter.getGenre()));
        }

        if (filter.getAvailability() != null) {
            predicates.add(criteriaBuilder.equal(root.get(MovieEntity_.IS_AVAILABLE), filter.getAvailability() == MovieFilter.Availability.AVAILABLE));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }


}
