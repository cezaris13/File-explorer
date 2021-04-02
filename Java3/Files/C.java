package Files;
import java.util.Date;
public abstract class C implements B{
    Date creationTime;
    long fileSize;//kb
    Date modificationTime;
    String fileDir;
    String fileName;
    String exProgram;
    public final long getFileSize(){
        return fileSize;
    }
    public final Date getCreationTime(){
        return creationTime;
    }
    public final Date getModificationTime(){
        return modificationTime;
    }
    public final String getFileDir(){
        return fileDir;
    }
    public final String getFileName(){
        return fileName;
    }
    public void setFileDir(String fileDir){
        this.fileDir=fileDir;
    }
    public void setFileName(String fileName){
        this.fileName=fileName;
    }
    public final void setFileSize(long fileSize){
        this.fileSize=fileSize;
    }
}
