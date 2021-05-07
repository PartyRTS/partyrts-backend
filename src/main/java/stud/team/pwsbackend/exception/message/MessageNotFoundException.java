package stud.team.pwsbackend.exception.message;

public class MessageNotFoundException extends Exception {
    public MessageNotFoundException(long messageId) {
        super("message with id " + messageId + " not found");
    }
}
