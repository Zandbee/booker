package org.strokova.booker.api.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 28.10.2016.
 */
@Entity
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 300)
    private String name;

    @Size(max = 20)
    private String phone;

    @OneToMany(mappedBy = "guest")
    private Set<Reservation> reservations = new HashSet<>();

    public Guest() {}

    public Guest(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public Guest setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Guest setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Guest setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public Guest setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
        return this;
    }
}
