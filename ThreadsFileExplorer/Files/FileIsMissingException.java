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
    public FileIsMissingException(){}
    public FileIsMissingException(String errorMessage,String fileName){
        super(errorMessage);
        this.fileName=fileName;
    }
    public FileIsMissingException(String errorMessage,String fileName,String fileDir){
        super(errorMessage);
        this.fileName=fileName;
        this.fileDir=fileDir;
    }
    public FileIsMissingException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
    public String getFileName() {
        return fileName;
    }
    public String getFileDir() {
        return fileDir;
    }
    public FileIsMissingException(String errorMessage){
        super(errorMessage);
    }
}
