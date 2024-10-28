package Exceptions;

/**
 * FileIsMissingException is used to inform user that specific file, by given
 * name does not exist
 */
public class FileIsMissingException extends Exception {
    /**
     * it has 1 additional variable
     * fileName, which are used when printing the error
     */
    public String fileName = "";

    /**
     * @param errorMessage error message
     * @param fileName     - file name
     */
    public FileIsMissingException(String errorMessage, String fileName) {
        super(errorMessage);
        this.fileName = fileName;
    }

    /**
     * @param errorMessage - error message
     */
    public FileIsMissingException(String errorMessage) {
        super(errorMessage);
    }
}
