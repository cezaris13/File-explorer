package Files;
import java.util.Date;
import java.io.IOException;
public abstract class EditableFile implements Editable{
    String exProgram="";
    String fileDir;
    String fileName;
    long fileSize=0;//kb
    Date modificationTime;
    public void editFile(int fileSize){
        this.fileSize=fileSize;
        modificationTime=new Date();
    }
    public void editFile(int fileSize, String fileDir){
        this.fileSize=fileSize;
        this.fileDir=fileDir;
        modificationTime=new Date();
    }
    public void openFile(){
        try{
            Process p=Runtime.getRuntime().exec(exProgram+" "+fileDir+"/"+fileName);
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
    public void deleteFile(){
        System.out.println("deleting file");
    }//todo later
}
