package com.kirak.to.booking;

import com.kirak.to.ApartmentTo;
import com.kirak.util.session.SessionObjectsIdGenerator;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Kir on 29.08.2017.
 */
public class SubBookingObject implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer editorId;

    private Integer superBookingId;

    private List<ApartmentTo> apartmentTos;

    private List<BookingTo> bookings;

    public SubBookingObject(Integer editorId,
                            Integer superBookingId,
                            List<ApartmentTo> apartmentTos,
                            List<BookingTo> bookings) {
        this.id = SessionObjectsIdGenerator.getNewId();
        this.editorId = editorId;
        this.superBookingId = superBookingId;
        this.apartmentTos = apartmentTos;
        this.bookings = bookings;
    }

    public Integer getId() {
        return id;
    }

    public Integer getEditorId() {
        return editorId;
    }

    public void setEditorId(Integer editorId) {
        this.editorId = editorId;
    }

    public Integer getSuperBookingId() {
        return superBookingId;
    }

    public void setSuperBookingId(Integer superBookingId) {
        this.superBookingId = superBookingId;
    }

    public List<ApartmentTo> getApartmentTos() {
        return apartmentTos;
    }

    public void setApartmentTos(List<ApartmentTo> apartmentTos) {
        this.apartmentTos = apartmentTos;
    }

    public List<BookingTo> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingTo> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "SubBookingObject{" +
                "id=" + id +
                ", hotelId=" + editorId +
                ", superBookingId=" + superBookingId +
                ", apartmentTos=" + apartmentTos +
                ", bookings=" + bookings +
                '}';
    }
}
