package org.strokova.booker.api.service;

import org.springframework.data.domain.Sort;

/**
 * 01.11.2016.
 */
// package-private
final class ServiceUtils {

    // package-private
    static Sort.Direction determineDirection(String order) {
        if (order != null && order.equalsIgnoreCase("desc")) {
            return Sort.Direction.DESC;
        } else {
            return Sort.Direction.ASC;
        }
    }
}
