package cinema.controllers;

import cinema.configs.CinemaProperties;
import cinema.models.*;

import cinema.services.CinemaRoomService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
public class CinemaController {
    CinemaProperties props;
    final CinemaRoomService cinemaRoomService;

    @GetMapping("/seats")
    CinemaRoom getAvailableSeats(){
        int nRows = props.nRows();;
        int nColumns = props.nCols();
        List<Seat> seats = cinemaRoomService.getAvailableSeats();
        return new CinemaRoom(nRows, nColumns, seats);
    }

    @PostMapping("/purchase")
    Seat purchase(@RequestBody SeatCoordinates seatCoordinates) {
        log.info("Req to /purchase : {}", seatCoordinates);
        return cinemaRoomService.purchase(seatCoordinates);
    }

}
