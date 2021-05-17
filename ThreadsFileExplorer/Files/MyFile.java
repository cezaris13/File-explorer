package Files;

import java.io.Serializable;
import java.util.Date;
/**
 * Base file Class, it is used to store basic file data:
 * file size, file name, file directory, creation time, modification time,
 * execution program
 *  */
public class MyFile extends EditableFile implements Cloneable,Serializable{
    /**
     * fileIcon- An Icon class object which saves info about specific file type icon
     * creationTime saves time when MyFile object was created
     * exProgram saves execution program in string
     *  */
    public Icon fileIcon=new Icon();
    private Date creationTime;
    public String exProgram;
    /**
       * Constructor MyFile()
       *
       * Creates empty MyFile object
       * adds file icon
       *
       * */
    public MyFile(){
        this(0,"","");
        fileIcon = new Icon("/home/pijus/IdeaProjects/FIleExplorer/src/Files/file.png",65,65);
    }
    /**
     * Constructor MyFile(String fileName)
     *
     * Creates New empty MyFile with given file name
     * assigns file name value to fileName variable
     * assigns fileIcon new icon(txt file icon)
     * file name- should not include fullPath to the file)
     * @param fileName(String)
     * */
    public MyFile(String fileName){
        this(0,"","");
        fileIcon = new Icon("/home/pijus/IdeaProjects/FIleExplorer/src/Files/file.png",65,65);
    }
    /**
     * Constructor MyFile(int fileSize, String fileDir, String fileName)
     *
     * Creates New MyFile with given file size,directory, name
     * assigns file name value to fileName variable(Same with fileDir,fileSize)
     * assigns fileIcon new icon(txt file icon)
     * file name-should not include fullPath to the file)
     * creates Modification date and creation date
     * @param fileSize(int),
     * @param fileDir(String),
     * @param fileName(String)
     * */
    public MyFile(int fileSize,String fileDir,String fileName){//this()
        this.fileSize=fileSize;
        this.fileDir=fileDir;
        this.fileName=fileName;
        creationTime=new Date();
        modificationTime=new Date();
        fileIcon = new Icon("/home/pijus/IdeaProjects/FIleExplorer/src/Files/file.png",65,65);
    }
    /**
     * Method Date getCreationtime()
     *
     * returns creation date
     *
     * @return creation time(Date)
     *  */
    public final Date getCreationTime(){
        return creationTime;
    }
    /**
     * Method String getExProgram()
     *
     * returns execution program
     *
     * @return exProgram(String)
     *  */
    public String getExProgram(){
        return exProgram;
    }
    /**
     * Method String getFiledir()
     *
     * returns file directory
     *
     * @return fileDir(String)
     *  */
    public final String getFileDir(){
        return fileDir;
    }
    /**
     * Method String getFileName()
     *
     * returns file name
     *
     * @return fileName(String)
     *  */
    public final String getFileName(){
        return fileName;
    }
    /**
     * Method String getFilesize()
     *
     * @return fileSize(long)
     *  */
    public final long getFileSize(){
        return fileSize;
    }
    /**
     * Method String getModificationTime()
     *
     * @return modificationTime(Date)
     *  */
    public final Date getModificationTime(){
        return modificationTime;
    }
    /**
     * Method String setFileDir(String fileDir)
     *
     * sets new File directory
     * @param fileDir - file directory
     *  */
    public void setFileDir(String fileDir){
        this.fileDir=fileDir;
    }
    /**
     * Method String setFileName(String fileName)
     *
     * sets new fileName
     * @param fileName - file Name
     *  */
    public void setFileName(String fileName){
        this.fileName=fileName;
    }
    /**
     * Method String setFileSize()
     *
     * sets file Size
     * @param fileSize - file size
     *  */
    public final void setFileSize(long fileSize){
        this.fileSize=fileSize;
    }
    /**
     * Method String setModificationTime(Date date)
     *
     * changes modification time
     *
     * @param date - modification date
     *  */
    public void setModificationTime(Date date){
        this.modificationTime=date;
    }
    /**
     * Method toString()
     * returns a string consisting of all MyFile
     * variables description
     *
     * @return String of variables
     *  */
    @Override
    public String toString(){
        return "CreationTime:"+getCreationTime()+"\n"
            +"FileSize: "+getFileSize()+"\n"
            +"ModificationDate: "+getModificationTime()+"\n"
            +"Filedir: "+getFileDir()+"\n"
            +"Filename: "+getFileName()+"\n"
            +"exProgram: "+exProgram+"\n";
    }
    /**
     * Method clone()
     *
     * @throws CloneNotSupportedException
     * returns Object type object(Clone)
     * */
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
