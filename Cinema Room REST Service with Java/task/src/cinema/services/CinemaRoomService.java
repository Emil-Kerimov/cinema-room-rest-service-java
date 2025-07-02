package cinema.services;

import cinema.configs.CinemaProperties;
import cinema.exceptions.AlreadyPurchaseException;
import cinema.exceptions.SeatCoordinatesOutOfBoundsException;
import cinema.models.*;
import cinema.repositories.CinemaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CinemaRoomService {
    final CinemaProperties props;
    final CinemaRepo cinemaRepo;

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

    public Seat purchase(SeatCoordinates seatCoordinates) {
        int iRow = seatCoordinates.row();
        int iCol = seatCoordinates.column();
        if (iRow < 1 || iRow > props.nRows() ||
            iCol < 1 || iCol > props.nCols()){
            throw new SeatCoordinatesOutOfBoundsException();
        }
        if (cinemaRepo.delete(seatCoordinates)) {
            return addPrice(seatCoordinates);
        } else {
            throw new AlreadyPurchaseException();

        }
    }

}
