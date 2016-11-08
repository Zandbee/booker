package org.strokova.booker.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.strokova.booker.api.entity.ReservationEntity;
import org.strokova.booker.api.entity.RoomEntity;

import java.util.Date;
import java.util.List;

/**
 * 03.11.2016.
 */
@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long>, QueryDslPredicateExecutor<ReservationEntity> {

    // could find/delete only by reservationId, but then it is not checked that the reservation belongs exactly to this room
    ReservationEntity findByIdAndRoomId(Long reservationId, Long roomId);

    void deleteByIdAndRoomId(Long reservationId, Long roomId);

    // TODO: how to name this method?
    @Transactional(readOnly = true)
    @Query("SELECT r FROM ReservationEntity r " +
            "WHERE r.room = ?1 " +
            "AND (r.dateFrom BETWEEN ?2 AND ?3 " +
            "OR r.dateTo BETWEEN ?2 AND ?3 " +
            "OR ?2 BETWEEN r.dateFrom AND r.dateTo)")
    List<ReservationEntity> findDatesIntersection(RoomEntity room, Date dateFrom, Date dateTo);
}
