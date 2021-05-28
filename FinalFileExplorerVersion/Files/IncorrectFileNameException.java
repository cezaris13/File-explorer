package Files;
/** An exception class which extends FileIsMissingException class
 *  It is used to inform user that, file name has some symbols in the name that are forbidden in
 *  file name:
 *  Invalid symbols:
 *  $%\\/:*?\"'>|~`#^+={}[];!
 *  */
public class IncorrectFileNameException extends FileIsMissingException{
    /**
     * invokes exception
     * @param errorMessage - error message
     */
    public IncorrectFileNameException(String errorMessage){
        super(errorMessage);
    }
}
