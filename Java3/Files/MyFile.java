package Files;
import java.io.IOException;
import java.util.Date;
public class MyFile extends EditableFile{
    //vartotojas static
    Date creationTime;
    // protected Icon;
    public MyFile(){
        this(0,"","");
    }
    public MyFile(int fileSize,String fileDir,String fileName){//this()
        this.fileSize=fileSize;
        this.fileDir=fileDir;
        this.fileName=fileName;
        creationTime=new Date();
        modificationTime=new Date();
    }
    public final Date getCreationTime(){
        return creationTime;
    }
    public String getExProgram(){
        return exProgram;
    }
    public final String getFileDir(){
        return fileDir;
    }
    public final String getFileName(){
        return fileName;
    }
    public final long getFileSize(){
        return fileSize;
    }
    public final Date getModificationTime(){
        return modificationTime;
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
