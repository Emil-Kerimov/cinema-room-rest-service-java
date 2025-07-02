package cinema.exceptions;

public class SeatCoordinatesOutOfBoundsException extends BusinessException{
    public SeatCoordinatesOutOfBoundsException() {
        super("The number of a row or a column is out of bounds!");
    }
}
