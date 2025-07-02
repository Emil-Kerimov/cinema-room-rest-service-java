package cinema.services;

import cinema.configs.CinemaProperties;
import cinema.exceptions.AlreadyPurchaseException;
import cinema.exceptions.SeatCoordinatesOutOfBoundsException;
import cinema.exceptions.WrongTokenException;
import cinema.models.*;
import cinema.repositories.CinemaRepo;
import cinema.repositories.SoldTicketRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CinemaRoomService {
    final CinemaProperties props;
    final CinemaRepo cinemaRepo;
    final SoldTicketRepo soldTicketRepo;

    public List<Seat> getAvailableSeats() {
        return cinemaRepo.getAllAvailableSeats().stream()
                .map(this::addPrice)
                .toList();
    }

    private int getPriceForRow(int row) {
        return row <= 4 ? 10 : 8; //TODO externalize
    }


    private Seat addPrice(SeatCoordinates seatCoordinates){
        return new Seat(
                seatCoordinates.row(),
                seatCoordinates.column(),
                getPriceForRow(seatCoordinates.row()));
    }

    public SoldTicket purchase(SeatCoordinates seatCoordinates) {
        int iRow = seatCoordinates.row();
        int iCol = seatCoordinates.column();
        if (iRow < 1 || iRow > props.nRows() ||
            iCol < 1 || iCol > props.nCols()){
            throw new SeatCoordinatesOutOfBoundsException();
        }
        if (cinemaRepo.delete(seatCoordinates)) {
            var seat = addPrice(seatCoordinates);
            String token = UUID.randomUUID().toString();
            var ticket = new SoldTicket(token, seat);
            soldTicketRepo.add(ticket);
            return  ticket;
        } else {
            throw new AlreadyPurchaseException();

        }
    }

    public Seat ticketReturn(String token) {
        synchronized (soldTicketRepo){
            if(soldTicketRepo.exist(token)){
                var seat = soldTicketRepo.remove(token);
                cinemaRepo.add(new SeatCoordinates(seat.row(), seat.column()));
                return seat;
            }else {
                throw new WrongTokenException();
            }
        }
    }
}
