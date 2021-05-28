package Files;
public class IncorrectFileNameException extends FileIsMissingException{
    public IncorrectFileNameException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
    public IncorrectFileNameException(String errorMessage){
        super(errorMessage);
    }
}
