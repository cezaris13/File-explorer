package Files;
import java.util.Date;
public abstract class FileFunctions implements ExtendedFile{
    protected Date creationTime;
    protected long fileSize=0;//kb
    protected Date modificationTime;
    protected String fileDir;
    protected String fileName;
    protected String exProgram="";
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
    abstract void openProgram();
}
