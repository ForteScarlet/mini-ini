package love.forte.minini;

/**
 *
 * a format exception
 *
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public class IniFormatException extends RuntimeException {

    public IniFormatException() {
    }

    public IniFormatException(String message) {
        super(message);
    }

    public IniFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public IniFormatException(Throwable cause) {
        super(cause);
    }

    public IniFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
