package com.techienotes.exception;

public class DatabaseException extends RuntimeException {
    public DatabaseException(Exception sqlException) {
        super(sqlException);
    }
}
