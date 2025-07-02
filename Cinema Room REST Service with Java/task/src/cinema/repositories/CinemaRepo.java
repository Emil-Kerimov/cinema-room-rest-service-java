package cinema.repositories;

import cinema.configs.CinemaProperties;
import cinema.models.Seat;
import cinema.models.SeatCoordinates;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CinemaRepo {
    Set<SeatCoordinates> availableSeats = new LinkedHashSet<>();

    public CinemaRepo(CinemaProperties props) {
        for (int iRow = 1; iRow <= props.nRows(); iRow++) {
            for (int iCol = 1; iCol <= props.nCols(); iCol++) {
                add(new SeatCoordinates(iRow, iCol));
            }
        }
    }

    public boolean delete(SeatCoordinates seatCoordinates) {
        return availableSeats.remove(seatCoordinates);
    }

    public Collection<SeatCoordinates> getAllAvailableSeats() {
        return Collections.unmodifiableCollection(availableSeats);
    }

    public void add(SeatCoordinates seatCoordinates){
        availableSeats.add(seatCoordinates);
    }
}
