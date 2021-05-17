package Files;
/** An exception class which extends FileIsMissingException class
 *  It is used to inform user that, file name has some symbols in the name that are forbidden in
 *  file name:
 *  Invalid symbols:
 *  @$%&\\/:*?\"'<>|~`#^+={}[];!
 *  */
public class IncorrectFileNameException extends FileIsMissingException{
    public IncorrectFileNameException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
    public IncorrectFileNameException(String errorMessage){
        super(errorMessage);
    }
}
