package application.commands.valute;

public class ServerUnavailableException extends RuntimeException {
    public ServerUnavailableException(String message) {
        super(message);
    }
}
