package Files;
public class FileIsMissingException extends Exception{
    public FileIsMissingException(String errorMessage,String fileName){
        super(errorMessage);
        System.out.printf("Failed to open file. File %s not found\n",fileName);
    }
   public FileIsMissingException(String errorMessage,String fileName,String fileDir){
        super(errorMessage);
        System.out.printf("Failed to delete file File %s not found at %s\n",fileName,fileDir);
    }
}
