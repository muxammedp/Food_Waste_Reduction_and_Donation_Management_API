package exception;

public class DatabaseOperationException extends RuntimeException {
    public DatabaseOperationException(String msg) { super(msg); }
}
