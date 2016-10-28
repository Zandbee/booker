package org.strokova.booker.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.strokova.booker.api.domain.Hotel;
import org.strokova.booker.api.repository.HotelRepository;

import java.util.Collection;

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
        return hotelRepository.save(hotel);
    }

    public Collection<Hotel> findHotels() {
        return hotelRepository.findAll();
    }

    public Hotel findHotel(Integer id) {
        return hotelRepository.findOne(id);
    }

    public void deleteHotel(Integer hotelId) {
        hotelRepository.delete(hotelId);
    }
}
