package org.strokova.booker.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.strokova.booker.api.entity.HotelEntity;
import org.strokova.booker.api.entity.HotelEntityFactory;
import org.strokova.booker.api.model.Hotel;
import org.strokova.booker.api.repository.HotelRepository;
import org.strokova.booker.api.searchPredicate.HotelSearchPredicates;

import java.util.Collection;
import java.util.stream.Collectors;

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

    public Hotel saveHotel(Hotel hotel) {
        return new Hotel(hotelRepository.save(HotelEntityFactory.create(hotel)));
    }

    public Hotel updateHotel(Integer hotelId, Hotel hotel) {
        if (hotel.getId() != null && !hotel.getId().equals(hotelId)) {
            throw new IllegalArgumentException("Impossible to update hotel id");
        }
        HotelEntity oldHotelEntity = hotelRepository.findOne(hotelId);
        return new Hotel(hotelRepository.save(updateHotelData(oldHotelEntity, hotel)));
    }

    public Collection<Hotel> findHotels() {
        return hotelRepository.findAll()
                .stream()
                .map(Hotel::new)
                .collect(Collectors.toList());
    }



    public Hotel findHotel(Integer id) {
        return new Hotel(hotelRepository.findOne(id));
    }

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
