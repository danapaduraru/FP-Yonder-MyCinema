package ro.fiipractic.mycinema.exceptions;

class MyCinemaGenericException extends Exception {

    MyCinemaGenericException(String message) {
        super(message);
    }

    MyCinemaGenericException(String message, Throwable cause) {
        super(message, cause);
    }
}
