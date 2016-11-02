package org.strokova.booker.api.service;

import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.strokova.booker.api.entity.HotelEntity;
import org.strokova.booker.api.entity.HotelEntityFactory;
import org.strokova.booker.api.model.Hotel;
import org.strokova.booker.api.queryParameters.HotelParameter;
import org.strokova.booker.api.repository.HotelRepository;

import static org.strokova.booker.api.searchPredicate.HotelSearchPredicates.*;
import static org.strokova.booker.api.service.ServiceUtils.determineSortDirection;

/**
 * 28.10.2016.
 */

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Transactional
    public Hotel saveHotel(Hotel hotel) {
        return new Hotel(hotelRepository.save(HotelEntityFactory.create(hotel)));
    }

    @Transactional
    public Hotel updateHotel(Integer hotelId, Hotel hotel) {
        Integer newId = hotel.getId();
        if (newId != null && !newId.equals(hotelId)) {
            throw new IllegalArgumentException("Impossible to update hotel id");
        }
        HotelEntity oldHotelEntity = hotelRepository.findOne(hotelId);
        return new Hotel(hotelRepository.save(updateHotelData(oldHotelEntity, hotel)));
    }

    @Transactional(readOnly = true)
    public Page<Hotel> findHotels(Integer page, Integer size, String order, String by,
            String name, Boolean hasPool, Boolean hasWaterpark, Boolean hasTennisCourt) {
        return hotelRepository.findAll(
                createSearchPredicate(name, hasPool, hasWaterpark, hasTennisCourt),
                createPageRequest(page, size, order, by))
                .map(Hotel::new);
    }

    @Transactional(readOnly = true)
    public Hotel findHotel(Integer id) {
        return new Hotel(hotelRepository.findOne(id));
    }

    @Transactional
    public void deleteHotel(Integer hotelId) {
        hotelRepository.delete(hotelId);
    }

    private static HotelEntity updateHotelData(HotelEntity hotel, Hotel data) {
        return hotel
                .setName(data.getName())
                .setHasPool(data.isHasPool())
                .setHasWaterpark(data.isHasWaterpark())
                .setHasTennisCourt(data.isHasTennisCourt());
    }

    private static BooleanBuilder createSearchPredicate(
            String name, Boolean hasPool, Boolean hasWaterpark, Boolean hasTennisCourt) {
        BooleanBuilder predicate = new BooleanBuilder();
        if (name != null && !name.trim().isEmpty()) {
            predicate.and(nameIs(name.trim()));
        }
        if (hasPool != null) {
            predicate.and(hasPool(hasPool));
        }
        if (hasWaterpark != null) {
            predicate.and(hasWaterpark(hasWaterpark));
        }
        if (hasTennisCourt != null) {
            predicate.and(hasTennisCourt(hasTennisCourt));
        }
        return predicate;
    }

    private static PageRequest createPageRequest(Integer page, Integer size, String order, String by) {
        return new PageRequest(page, size, determineSortDirection(order), determineSortProperty(by));
    }

    private static String determineSortProperty(String by) {
        String sortProperty = null;

        for (HotelParameter param : HotelParameter.values()) {
            if (by.equalsIgnoreCase(param.getQueryParameterName())) {
                sortProperty = param.getColumnName();
            }
        }
        // if by param is invalid - order by name
        if (sortProperty == null) {
            sortProperty = HotelParameter.NAME.getColumnName();
        }

        return sortProperty;
    }
}
