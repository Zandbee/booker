package org.strokova.booker.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.strokova.booker.api.entity.HotelEntity;
import org.strokova.booker.api.entity.RoomEntityFactory;
import org.strokova.booker.api.model.Room;
import org.strokova.booker.api.repository.HotelRepository;
import org.strokova.booker.api.repository.RoomRepository;

/**
 * 02.11.2016.
 */
@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository, HotelRepository hotelRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }

    @Transactional
    public Room saveRoom(Room room, Integer hotelId) {
        HotelEntity hotelEntity = hotelRepository.findOne(hotelId);
        if (hotelEntity == null) {
            throw new IllegalArgumentException("Cannot find hotel with id = " + hotelId);
        }

        return new Room(roomRepository.save(RoomEntityFactory.create(room, hotelEntity)));
    }

    /*@Transactional(readOnly = true)
    public Page<Room> findRooms() {

    }*/
}
