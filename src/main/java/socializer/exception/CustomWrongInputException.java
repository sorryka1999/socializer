package socializer.exception;

import socializer.constant.WrongInputType;

public class CustomWrongInputException extends RuntimeException {

    public CustomWrongInputException(WrongInputType wrongInputType) {
        super(wrongInputType.getMessage());
    }

}
