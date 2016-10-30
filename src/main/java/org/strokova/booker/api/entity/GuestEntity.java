package org.strokova.booker.api.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 28.10.2016.
 */
@Entity
@Table(name = "guest")
public class GuestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 300)
    private String name;

    @Size(max = 20)
    private String phone;

    @OneToMany(mappedBy = "guest")
    private Set<ReservationEntity> reservations = new HashSet<>();

    public GuestEntity() {}

    public GuestEntity(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public GuestEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public GuestEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public GuestEntity setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Set<ReservationEntity> getReservations() {
        return reservations;
    }

    public GuestEntity setReservations(Set<ReservationEntity> reservations) {
        this.reservations = reservations;
        return this;
    }
}
