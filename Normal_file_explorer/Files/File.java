import java.util.Date;
public class File{
    //vartotojas static
    private final Date creationTime;
    private int fileSize=0;//kb
    private Date modificationTime;
    private String fileDir;
    private String fileName;
    protected String exProgram="";
    public File(){
        fileSize=0;
        fileDir="";
        fileName="";
        creationTime=new Date();
        modificationTime=new Date();
    }
    public File(int fileSize,String fileDir,String fileName){//this()
        this.fileSize=fileSize;
        this.fileDir=fileDir;
        this.fileName=fileName;
        creationTime=new Date();
        modificationTime=new Date();
    }
    public void editFile(int fileSize){
        this.fileSize=fileSize;
        modificationTime=new Date();
    }
    public void editFile(int fileSize, String fileDir){
        this.fileSize=fileSize;
        this.fileDir=fileDir;
        modificationTime=new Date();
    }
    public final int getFileSize(){
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
    public void println(){
        System.out.println("fileSize: "+fileSize);
        System.out.println("modificationTime: "+modificationTime);
        System.out.println("directory: "+fileDir);
        System.out.println("File_name: "+fileName);
        System.out.println("creation_time: "+creationTime);
    }
}
//txt

//docx
//excel
//pptx
//pdf

//image
//
