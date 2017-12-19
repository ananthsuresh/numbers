public final class NumbersException extends Exception{

    public enum ErrorCode{
        INVALID_NUM_LINES("failure"),
        INVALID_NUM_CHARS("failure"),
        INVALID_SEGMENT("failure"),
        TOO_MANY_CANDIDATES("ambiguous"),
        TOO_MANY_GARBLED("failure");

        private final String errorMessage;

        ErrorCode(String errorMessage){
            this.errorMessage = errorMessage;
        }

        String errorMessage(){
            return errorMessage;
        }

    }

    private final ErrorCode errorCode;

    public ErrorCode getErrorCode(){
        return errorCode;
    }

    NumbersException(ErrorCode errorCode){
        this.errorCode = errorCode;
    }

}
