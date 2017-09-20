package eil11.lexer;


import java.util.Optional;

public final class ParserException extends Exception {

    public enum ErrorCode {
        TOKEN_EXPECTED,
        INVALID_TOKEN,
        TRAILING_INPUT
    }

    private final ErrorCode errorCode;

    private final int location;


    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public int getLocation() {
        return location;
    }

    ParserException(LocationalToken token, ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.location = token.getLocation();
    }

    ParserException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.location = -1;
    }

    @Override
    public String toString() {
        return "ParserException{" +
                "errorCode=" + errorCode +
                ", location=" + location +
                '}';
    }

    public static void verify(Optional<LocationalToken> token) throws ParserException{
        if (token.equals(Optional.empty())) {
            throw new ParserException(ErrorCode.TOKEN_EXPECTED);
        }
    }

    public static void verifyEnd(Optional<LocationalToken> token) throws ParserException {
        if (token.isPresent()) {
            throw new ParserException(ErrorCode.TRAILING_INPUT);
        }
    }



}
