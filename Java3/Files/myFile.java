package Files;
import java.io.IOException;
import java.util.Date;
public class myFile extends FileFunctions{
    //vartotojas static
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
    public final void openProgram(){
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
}
