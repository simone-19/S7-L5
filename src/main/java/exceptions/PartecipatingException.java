package exceptions;

public class PartecipatingException extends RuntimeException {
    public PartecipatingException(String message) {
        super(message);
    }

    public PartecipatingException(Event event, User user) {
        super("User " + user.getName() + " is already partecipating in event " + event.getTitle());
    }
}
