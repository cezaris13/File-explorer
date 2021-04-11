package Files;
import java.util.Date;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

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
            File tmpFile = new File(fileDir+"/"+fileName);
            if(!tmpFile.exists()){
                throw new FileIsMissingException("Test",fileName);
            }
            Process p=Runtime.getRuntime().exec(exProgram+" "+fileDir+"/"+fileName);
            try{
                p.waitFor();
            }
            catch(InterruptedException ex){
                System.out.println("interruption error");
            }
        }
        catch(FileIsMissingException ex){
            // System.out.println("sth failed");
        }
        catch(IOException ex){
            // System.out.println("sth failed");
        }
    }
    public void deleteFile(){//add stuff
        File file = new File(fileDir+"/"+fileName);
        try{
            if(!file.exists()){
                throw new FileIsMissingException("test",fileName,fileDir);
            }
            if (file.delete()) {
                System.out.println("Deleted the file: " + file.getName());
            }
            else {
                System.out.println("Failed to delete the file.");
            }
        }
        catch(FileIsMissingException ex){
            // System.out.println("");
        }
    }//todo later
}
