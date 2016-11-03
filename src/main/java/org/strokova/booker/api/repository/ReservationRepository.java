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

    // could find/delete only by reservationId, but then it is not checked that the reservation belongs exactly to this room
    ReservationEntity findByIdAndRoomId(Long reservationId, Long roomId);

    void deleteByIdAndRoomId(Long reservationId, Long roomId);
}
