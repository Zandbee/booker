package org.strokova.booker.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.strokova.booker.api.domain.Hotel;

/**
 * 28.10.2016.
 */
@Repository
//@ transactional(readOnly = true)
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
}
