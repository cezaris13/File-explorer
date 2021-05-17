package Files;
/**
 * FileIsMissingException is used to inform user that specific file, by given name does not exist
 *
 *  */
public class FileIsMissingException extends Exception{
    /**
     * it has 2 additional variables
     * fileName and fileDir, which are used when printing the error */
    public String fileName="";
    public String fileDir="";

    /**
     *
     * @param errorMessage error message
     * @param fileName - file name
     */
    public FileIsMissingException(String errorMessage,String fileName){
        super(errorMessage);
        this.fileName=fileName;
    }

    /**
     *
     * @param errorMessage - error message
     * @param fileName - file name
     * @param fileDir - file directory
     */
    public FileIsMissingException(String errorMessage,String fileName,String fileDir){
        super(errorMessage);
        this.fileName=fileName;
        this.fileDir=fileDir;
    }

    /**
     *  returns file name
     * @return fileName (String)
     */
    public String getFileName() {
        return fileName;
    }

    /**
     *
     * @param errorMessage - error message
     */
    public FileIsMissingException(String errorMessage){
        super(errorMessage);
    }
}
