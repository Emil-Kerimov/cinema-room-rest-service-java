package cinema.controllers;


import cinema.configs.CinemaProperties;
import cinema.models.CinemaRoom;
import cinema.models.Seat;
import cinema.services.CinemaRoomService;
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
    final CinemaRoomService cinemaRoomService;
    @GetMapping("/seats")
    CinemaRoom getAvailableSeats(){
        int nRows = props.nRows();
        int nColumns = props.nCols();
        List<Seat> seats = cinemaRoomService.getAvailableSeats();
        return new CinemaRoom(nRows, nColumns, seats);
    }
}
