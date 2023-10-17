package restaurant.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import restaurant.booking.model.Booking;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    //Logic delete
    List<Booking> findByActiveTrue();

    Booking findByPhoneNumberAndActiveTrue(String phoneNumber);


}
