package MusicPlayer.exception;

public class Mp3Exception extends Exception{
    public Mp3Exception() {
    }

    public Mp3Exception(String message) {
        super(message);
    }

    public Mp3Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Mp3Exception(Throwable cause) {
        super(cause);
    }
}
