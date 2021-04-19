package Files;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.io.File;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.*;
public class MyFile extends EditableFile implements Cloneable{
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
    public Object clone() throws CloneNotSupportedException{
        MyFile fileCopy=(MyFile)super.clone();
        fileCopy.creationTime=new Date();
        fileCopy.modificationTime=new Date();
        File tempFilecopy=new File(fileCopy.fileDir+"/"+fileCopy.fileName);
        String tempNewName=new String(fileCopy.getFileName());
        File newFile=new File(fileCopy.fileDir+"/"+tempNewName);
        Integer i=1;
        String newFileName=new String();
        while(newFile.exists()){
            int j = tempNewName.lastIndexOf('.');
            newFile=new File(fileCopy.fileDir+"/"+tempNewName.substring(0,j)+i.toString()+"."+tempNewName.substring(j+1));
            newFileName=tempNewName.substring(0,j)+i.toString()+"."+tempNewName.substring(j+1);
            i++;
        }
        fileCopy.fileName=newFileName;
        try{
            Files.copy(tempFilecopy.toPath(),newFile.toPath(),REPLACE_EXISTING);
        }
        catch(IOException ex){
            System.out.println(ex);
        }
        return fileCopy;
    }
}
