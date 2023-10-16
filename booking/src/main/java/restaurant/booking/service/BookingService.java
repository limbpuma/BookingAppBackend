package restaurant.booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restaurant.booking.model.Booking;
import restaurant.booking.repository.BookingRepository;

import java.util.List;

@Service
public class BookingService implements IBookingService{

    @Autowired
    private BookingRepository bookingRepository;

    /*Delete
    @Override
    public List<Booking> listBooking() {
        return this.bookingRepository.findAll();
    }
    */

    @Override
    public List<Booking> listBooking() {
        return this.bookingRepository.findByActiveTrue();
    }


    @Override
    public Booking searchBookingById(Integer idBooking) {
        Booking booking = this.bookingRepository.findById(idBooking).orElse(null);
        return booking;
    }

    @Override
    public Booking saveBooking(Booking booking) {
        this.bookingRepository.save(booking);
        return booking;
    }

    @Override
    public void deleteBookingById(Integer idBooking) {
    this.bookingRepository.deleteById(idBooking);
    }

    @Override
    public Booking findByPhoneNumberAndActiveTrue(String phoneNumber) {
        return bookingRepository.findByPhoneNumberAndActiveTrue(phoneNumber);
    }

}
