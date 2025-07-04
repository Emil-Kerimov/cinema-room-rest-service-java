package cinema.repositories;

import cinema.models.ReturnedTicketResponse;
import cinema.models.Seat;
import cinema.models.SoldTicket;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class SoldTicketRepo {
    Map<String/*ticket*/, Seat> soldTickets = new HashMap<>();
    public void add(SoldTicket soldTicket){
        soldTickets.put(soldTicket.token(), soldTicket.ticket());
    }

    public boolean exist(String token) {
        return  soldTickets.containsKey(token);
    }

    public Seat remove(String token) {
        return soldTickets.remove(token);
    }
}
