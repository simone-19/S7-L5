package exceptions;



@Getter
public class NotFoundException extends RuntimeException {

    public NotFoundException(long id) {
        super("id " + id + " not found!");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
