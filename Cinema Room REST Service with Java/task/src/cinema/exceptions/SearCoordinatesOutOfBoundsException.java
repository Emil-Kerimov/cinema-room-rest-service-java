package cinema.exceptions;

public class SearCoordinatesOutOfBoundsException extends BusinessException{
    public SearCoordinatesOutOfBoundsException() {
        super("The number of a row or a column is out of bounds!");
    }
}
