package Files;

import java.util.Date;

public class MyFile extends EditableFile implements Cloneable{
    public Icon fileIcon=new Icon();
    private Date creationTime;
    public String exProgram;
    public MyFile(){
        this(0,"","");
        fileIcon = new Icon("/home/pijus/IdeaProjects/FIleExplorer/src/Files/file.png",65,65);
    }
    public MyFile(String fileName){
        this(0,"","");
        fileIcon = new Icon("/home/pijus/IdeaProjects/FIleExplorer/src/Files/file.png",65,65);
    }
    public MyFile(int fileSize,String fileDir,String fileName){//this()
        this.fileSize=fileSize;
        this.fileDir=fileDir;
        this.fileName=fileName;
        creationTime=new Date();
        modificationTime=new Date();
        fileIcon = new Icon("/home/pijus/IdeaProjects/FIleExplorer/src/Files/file.png",65,65);
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
    public void setModificationTime(Date date){
        this.modificationTime=date;
    }
    public void setCreationTime(Date date){
        this.creationTime=date;
    }
    public String toString(){
        return "CreationTime:"+getCreationTime()+"\n"
            +"FileSize: "+getFileSize()+"\n"
            +"ModificationDate: "+getModificationTime()+"\n"
            +"Filedir: "+getFileDir()+"\n"
            +"Filename: "+getFileName()+"\n"
            +"exProgram: "+exProgram+"\n";
    }
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
