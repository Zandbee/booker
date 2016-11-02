package org.strokova.booker.api.model;

import org.strokova.booker.api.entity.GuestEntity;

import java.util.Objects;

/**
 * author: Veronika, 10/30/2016.
 */
public class Guest {
    private Long id;
    private String name;
    private String phone;

    public Guest() {}

    public Guest(Long id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public Guest (GuestEntity guestEntity) {
        this.id = guestEntity.getId();
        this.name = guestEntity.getName();
        this.phone = guestEntity.getPhone();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guest guest = (Guest) o;
        return Objects.equals(id, guest.id) &&
                Objects.equals(name, guest.name) &&
                Objects.equals(phone, guest.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone);
    }

    @Override
    public String toString() {
        return "Guest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
