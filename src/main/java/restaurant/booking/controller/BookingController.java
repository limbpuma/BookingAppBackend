package restaurant.booking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant.booking.excepcion.ResourceNotFoundException;
import restaurant.booking.model.Booking;
import restaurant.booking.service.BookingService;

import java.util.List;

@RestController
//localhost:8081/restaurant-app
@RequestMapping("restaurant-app")
@CrossOrigin(origins = "*")

public class BookingController {

    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private BookingService bookingService;

    //http://localhost:8081/restaurant-app/booking
    //Booking List
    @GetMapping("/booking")
    public List<Booking> getBooking(){
        List<Booking> bookings = this.bookingService.listBooking();
        logger.info("reservations obtained");
        bookings.forEach((booking -> logger.info(booking.toString())));
        return bookings;
    }

    /*Add booking
    @PostMapping("/booking")
    public Booking addBooking(@RequestBody Booking booking){
        logger.info("Add Booking: " + booking);
        return this.bookingService.saveBooking(booking);
    }
     */
    //Add booking
    @PostMapping("/booking")
    public ResponseEntity<?> addBooking(@RequestBody Booking booking){
        // Verificar si ya existe una reserva activa con el mismo número de teléfono
        Booking existingBooking = bookingService.findByPhoneNumberAndActiveTrue(booking.getPhoneNumber());
        if(existingBooking != null) {
            return ResponseEntity.badRequest().body("Ya existe una reserva activa con este número de teléfono.");
        }

        logger.info("Add Booking: " + booking);
        Booking savedBooking = this.bookingService.saveBooking(booking);
        return ResponseEntity.ok(savedBooking);
    }



    //Search Booking
    @GetMapping("/booking/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable int id){
        Booking booking = this.bookingService.searchBookingById(id);
        if(booking !=null)
            return ResponseEntity.ok(booking);
        else throw new ResourceNotFoundException("ID not found: "+ id);
    }

    //Update
    @PutMapping("/booking/{id}")
    public ResponseEntity<Booking> updateBooking(
            @PathVariable int id,
            @RequestBody Booking bookingReceived
    ){
        Booking booking = this.bookingService.searchBookingById(id);
        if(booking == null)
            throw new ResourceNotFoundException("ID not found: "+ id);
        booking.setNameClient(bookingReceived.getNameClient());
        booking.setDateTime(bookingReceived.getDateTime());
        booking.setNumberOfPeople(bookingReceived.getNumberOfPeople());
        booking.setPhoneNumber(bookingReceived.getPhoneNumber());
        booking.setEmail(bookingReceived.getEmail());
        this.bookingService.saveBooking(booking);
        return ResponseEntity.ok(booking);
    }


    // Delete Logic
    @PutMapping("/booking/deactivate/{id}")
    public ResponseEntity<Booking> deactivateBooking(@PathVariable int id) {
        Booking booking = bookingService.searchBookingById(id);
        if (booking == null) {
            throw new ResourceNotFoundException("ID not found: "+ id);
        }
        booking.setActive(false);
        bookingService.saveBooking(booking);
        return ResponseEntity.ok(booking);
    }



}
