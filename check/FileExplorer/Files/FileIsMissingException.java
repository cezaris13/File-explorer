package Files;
public class FileIsMissingException extends Exception{
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
