package restaurant.booking.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBooking;
    private String NameClient;
    private LocalDateTime dateTime;
    private int numberOfPeople;
    private String phoneNumber;
    private String email;

    //Booking status activated
    private boolean active = true;
}

/*Booking Attributs
id (Identificador único)
nombreCliente
fechaHora
numeroPersonas
telefono
email
activo
 */