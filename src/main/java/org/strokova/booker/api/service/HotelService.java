package org.strokova.booker.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.strokova.booker.api.entity.HotelEntity;
import org.strokova.booker.api.model.Hotel;
import org.strokova.booker.api.repository.HotelRepository;

import java.util.Collection;
import java.util.List;
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

    public HotelEntity saveHotel(HotelEntity hotel) {
        return hotelRepository.save(hotel);
    }

    public Collection<Hotel> findHotels() {
        return hotelRepository.findAll()
                .stream()
                .map(Hotel::new)
                .collect(Collectors.toList());
    }

    public HotelEntity findHotel(Integer id) {
        return hotelRepository.findOne(id);
    }

    public void deleteHotel(Integer hotelId) {
        hotelRepository.delete(hotelId);
    }
}
