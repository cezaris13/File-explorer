package Files;
public class FileIsMissingException extends Exception{
    private String fileName="";
    private String fileDir="";
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
    public String getFileName() {
        return fileName;
    }
    public String getFileDir() {
        return fileDir;
    }
}
