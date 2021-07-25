package kr.heesu.practice.r2dbc.repository.exception;

public class AlreadyExistException extends RuntimeException {

    public AlreadyExistException(String message) {
        super(message);
    }
}
