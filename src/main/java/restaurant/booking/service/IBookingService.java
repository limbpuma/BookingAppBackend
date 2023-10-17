package restaurant.booking.service;

import restaurant.booking.model.Booking;

import java.util.List;

public interface IBookingService {
    public List<Booking> listBooking();

    public Booking searchBookingById(Integer idBooking);

    public Booking saveBooking(Booking booking);

    public void deleteBookingById(Integer idBooking);

    Booking findByPhoneNumberAndActiveTrue(String phoneNumber);

}
