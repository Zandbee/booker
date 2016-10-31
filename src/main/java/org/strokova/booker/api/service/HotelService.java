package org.strokova.booker.api.service;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.strokova.booker.api.entity.HotelEntity;
import org.strokova.booker.api.entity.HotelEntityFactory;
import org.strokova.booker.api.model.Hotel;
import org.strokova.booker.api.repository.HotelRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    @Transactional(readOnly = true)
    public Hotel saveHotel(Hotel hotel) {
        return new Hotel(hotelRepository.save(HotelEntityFactory.create(hotel)));
    }

    @Transactional
    public Hotel updateHotel(Integer hotelId, Hotel hotel) {
        if (hotel.getId() != null && !hotel.getId().equals(hotelId)) {
            throw new IllegalArgumentException("Impossible to update hotel id");
        }
        HotelEntity oldHotelEntity = hotelRepository.findOne(hotelId);
        return new Hotel(hotelRepository.save(updateHotelData(oldHotelEntity, hotel)));
    }

    @Transactional(readOnly = true)
    public Collection<Hotel> findHotels() {
        return hotelRepository.findAll()
                .stream()
                .map(Hotel::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Collection<Hotel> findHotels(Map<String, String> predicates) {
        if (predicates.isEmpty()) {
            return findHotels();
        }

        Predicate searchPredicate = null; // TODO: is it good?
        for (Map.Entry<String, String> predicate: predicates.entrySet()) {
            if (predicate.getKey().equalsIgnoreCase("hasPool")) {
                searchPredicate = ;
            }
        }

        if (searchPredicate == null) {
            return Collections.emptyList();
        }

        // TODO: parallel and sequential streams - ??
        return StreamSupport.stream(hotelRepository.findAll(searchPredicate).spliterator(), false)
                .map(Hotel::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Hotel findHotel(Integer id) {
        return new Hotel(hotelRepository.findOne(id));
    }

    @Transactional
    public void deleteHotel(Integer hotelId) {
        hotelRepository.delete(hotelId);
    }

    private HotelEntity updateHotelData(HotelEntity hotel, Hotel data) {
        return hotel
                .setName(data.getName())
                .setHasPool(data.isHasPool())
                .setHasWaterpark(data.isHasWaterpark())
                .setHasTennisCourt(data.isHasTennisCourt());
    }
}
