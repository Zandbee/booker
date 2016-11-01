package org.strokova.booker.api.service;

import com.google.common.collect.ImmutableList;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.strokova.booker.api.entity.HotelEntity;
import org.strokova.booker.api.entity.HotelEntityFactory;
import org.strokova.booker.api.model.Hotel;
import org.strokova.booker.api.queryParameters.HotelQueryParameter;
import org.strokova.booker.api.repository.HotelRepository;

import static org.strokova.booker.api.Utils.*;
import static org.strokova.booker.api.searchPredicate.HotelSearchPredicates.*;
import static org.strokova.booker.api.queryParameters.HotelQueryParameter.*;

import java.util.*;
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
    public Collection<Hotel> findHotels(Map<String, String> params) {
        // return all hotels if there are no query parameters
        if (params.isEmpty()) {
            return findHotels();
        }

        BooleanBuilder predicate = new BooleanBuilder();
        addParamsToSearchPredicate(params, predicate);

        // return empty collection if all the query parameters are invalid
        if (!predicate.hasValue()) {
            return Collections.emptyList();
        }

        return convertHotelEntityIterableToHotelCollection(hotelRepository.findAll(predicate.getValue()));
    }

    @Transactional(readOnly = true)
    public Page<Hotel> findHotels(int page, int size) {
        return hotelRepository
                .findAll(new PageRequest(page, size))
                .map(Hotel::new);
    }

    @Transactional(readOnly = true)
    public Page<Hotel> findHotels(int page, int size, String order) {
        return hotelRepository
                .findAll(new PageRequest(page, size, ServiceUtils.determineDirection(order)))
                .map(Hotel::new);
    }

    @Transactional(readOnly = true)
    public Collection<Hotel> findHotels(String by, String order) {
        String sortProperty = determineSortProperty(by);

        return convertHotelEntityIterableToHotelCollection(
                hotelRepository.findAll(new Sort(ServiceUtils.determineDirection(order), sortProperty)));
    }

    @Transactional(readOnly = true)
    public Hotel findHotel(Integer id) {
        return new Hotel(hotelRepository.findOne(id));
    }

    @Transactional(readOnly = true)
    public Hotel findHotel(String hotelName) {
        return new Hotel(hotelRepository.findByNameIgnoreCase(hotelName));
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

    private static void addParamsToSearchPredicate(Map<String, String> params, BooleanBuilder predicate) {
        for (Map.Entry<String, String> param: params.entrySet()) {
            String paramKey = param.getKey();
            String paramValue = param.getValue();
            if (paramKey.equalsIgnoreCase(HAS_POOL.name())) {
                predicate.and(hasPool(booleanFrom(paramValue)));
            } else if (paramKey.equalsIgnoreCase(HAS_WATERPARK.name())) {
                predicate.and(hasWaterpark(booleanFrom(paramValue)));
            } else if (paramKey.equalsIgnoreCase(HAS_TENNIS_COURT.name())) {
                predicate.and(hasTennisCourt(booleanFrom(paramValue)));
            }
        }
    }

    private static Collection<Hotel> convertHotelEntityIterableToHotelCollection(Iterable<HotelEntity> entities) {
        ImmutableList.Builder<Hotel> result = ImmutableList.builder();
        for (HotelEntity entity : entities) {
            result.add(new Hotel(entity));
        }

        return result.build();
    }

    private static String determineSortProperty(String by) {
        String sortProperty = null;

        for (HotelQueryParameter param : HotelQueryParameter.values()) {
            if (by.equalsIgnoreCase(param.getSortColumn())) {
                sortProperty = param.getColumnName();
            }
        }
        // if by param is invalid - order by name
        if (sortProperty == null) {
            sortProperty = HotelQueryParameter.NAME.getColumnName();
        }

        return sortProperty;
    }
}
