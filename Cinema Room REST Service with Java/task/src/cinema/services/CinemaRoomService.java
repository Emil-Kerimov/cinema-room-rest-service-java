package cinema.services;

import cinema.configs.CinemaProperties;
import cinema.models.Seat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CinemaRoomService {
    final CinemaProperties props;

    public List<Seat> getAvailableSeats() {
        List<Seat> res = new ArrayList<>();
        for (int iRow = 1; iRow <= props.nRows(); iRow++){
            for (int iCol = 1; iCol <= props.nCols(); iCol++){
                res.add(new Seat(iRow,iCol));
            }
        }
        return res;
    }
}
