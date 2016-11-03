package org.strokova.booker.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.strokova.booker.api.entity.ReservationEntity;

/**
 * 03.11.2016.
 */
@Repository
@Transactional(readOnly = true)
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long>, QueryDslPredicateExecutor<ReservationEntity> {
}
