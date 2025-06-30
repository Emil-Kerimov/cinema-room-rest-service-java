package cinema.controllers;


import cinema.configs.CinemaProperties;
import cinema.models.CinemaRoom;
import cinema.models.Seat;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class CinemaController {
    final CinemaProperties props;
    @GetMapping("/seats")
    CinemaRoom getAvailableSeats(){
        int nRows = props.nRows();
        int nColumns = props.nCols();
        List<Seat> seats = new ArrayList<>();
        for (int iRow = 1; iRow <= nRows; iRow++){
            for (int iCol = 1; iCol <= nColumns; iCol++){
                seats.add(new Seat(iRow,iCol));
            }
        }
        return new CinemaRoom(nRows, nColumns, seats);
    }
}
