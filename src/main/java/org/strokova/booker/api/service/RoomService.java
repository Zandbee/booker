package org.strokova.booker.api.service;

import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.strokova.booker.api.entity.HotelEntity;
import org.strokova.booker.api.entity.RoomEntity;
import org.strokova.booker.api.entity.RoomEntityFactory;
import org.strokova.booker.api.model.Room;
import org.strokova.booker.api.model.RoomType;
import org.strokova.booker.api.queryParameters.RoomParameter;
import org.strokova.booker.api.repository.HotelRepository;
import org.strokova.booker.api.repository.RoomRepository;

import static org.strokova.booker.api.searchPredicate.RoomSearchPredicates.*;

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

    @Transactional(readOnly = true)
    public Page<Room> findRooms(Integer page, Integer size, String order, String by,
                                RoomType type, Boolean hasTv, Boolean hasBalcony, Boolean hasAirConditioner, Boolean hasRubbishView,
                                Boolean hasPoolView, Boolean hasSeaView, Boolean hasFixedDateReservation) {
        return roomRepository.findAll(
                createSearchPredicate(type, hasTv, hasBalcony, hasAirConditioner, hasRubbishView, hasPoolView, hasSeaView, hasFixedDateReservation),
                createPageRequest(page, size, order, by))
                .map(Room::new);
    }

    @Transactional(readOnly = true)
    public Room findRoom(Long roomId, Integer hotelId) {
        return new Room(roomRepository.findByIdAndHotelId(roomId, hotelId));
    }

    @Transactional
    public void deleteRoom(Long roomId, Integer hotelId) {
        roomRepository.deleteByIdAndHotelId(roomId, hotelId);
    }

    @Transactional
    public Room updateRoom(Long roomId, Integer hotelId, Room newRoomData) {
        Long newId = newRoomData.getId();
        if (newId != null && newId.equals(roomId)) {
            throw new IllegalArgumentException("Impossible to update room id");
        }
        RoomEntity oldRoomEntity = roomRepository.findByIdAndHotelId(roomId, hotelId);
        return new Room(roomRepository.save(updateRoomData(oldRoomEntity, newRoomData)));
    }

    private static RoomEntity updateRoomData(RoomEntity roomEntity, Room data) {
        return roomEntity
                .setType(data.getType())
                .setHasTv(data.isHasTv())
                .setHasAirConditioner(data.isHasAirConditioner())
                .setHasBalcony(data.isHasBalcony())
                .setHasRubbishView(data.isHasRubbishView())
                .setHasPoolView(data.isHasPoolView())
                .setHasSeaView(data.isHasSeaView())
                .setHasFixedDateReservation(data.isHasFixedDateReservation());
    }

    private static BooleanBuilder createSearchPredicate(RoomType type, Boolean hasTv, Boolean hasBalcony, Boolean hasAirConditioner,
                                                        Boolean hasRubbishView, Boolean hasPoolView, Boolean hasSeaView,
                                                        Boolean hasFixedDateReservation) {
        BooleanBuilder predicate = new BooleanBuilder();
        if (type != null) {
            predicate.and(typeIs(type));
        }
        if (hasTv != null) {
            predicate.and(hasTv(hasTv));
        }
        if (hasBalcony != null) {
            predicate.and(hasBalcony(hasBalcony));
        }
        if (hasAirConditioner != null) {
            predicate.and(hasAirConditioner(hasAirConditioner));
        }
        if (hasRubbishView != null) {
            predicate.and(hasRubbishView(hasRubbishView));
        }
        if (hasPoolView != null) {
            predicate.and(hasPoolView(hasPoolView));
        }
        if (hasSeaView != null) {
            predicate.and(hasSeaView(hasSeaView));
        }
        if (hasFixedDateReservation != null) {
            predicate.and(hasFixedDateReservation(hasFixedDateReservation));
        }

        return predicate;
    }

    private static PageRequest createPageRequest(Integer page, Integer size, String order, String by) {
        return new PageRequest(page, size, ServiceUtils.determineSortDirection(order), determineSortProperty(by));
    }

    private static String determineSortProperty(String by) {
        String sortProperty = null;

        for (RoomParameter param : RoomParameter.values()) {
            if (by.equalsIgnoreCase(param.getQueryParameterName())) {
                sortProperty = param.getColumnName();
            }
        }
        // if by param is invalid - order by type
        if (sortProperty == null) {
            sortProperty = RoomParameter.TYPE.getColumnName();
        }

        return sortProperty;
    }
}
