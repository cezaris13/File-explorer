import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.io.IOException;

public class myFile{
    //vartotojas static
    private final Date creationTime;
    private long fileSize=0;//kb
    private Date modificationTime;
    private String fileDir;
    private String fileName;
    protected String exProgram="";
    // protected Icon;
    public myFile(){
        this(0,"","");
    }
    public myFile(int fileSize,String fileDir,String fileName){//this()
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
    protected void openProgram(){
        try{
            Process p=Runtime.getRuntime().exec(exProgram+" "+getFileDir()+"/"+getFileName());
            try{
                p.waitFor();
            }
            catch(InterruptedException ex){
                System.out.println("something went wrong");
            }
        }
        catch(IOException ex){
            System.out.println("something went wrong");
        }
    }
    public void println(){
        System.out.println("Creation_time: "+creationTime);
        System.out.println("FileSize: "+fileSize);
        System.out.println("modificationTime: "+modificationTime);
        System.out.println("directory: "+fileDir);
        System.out.println("File_name: "+fileName);
    }
}
