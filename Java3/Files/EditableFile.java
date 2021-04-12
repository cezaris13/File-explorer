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
    public void openFile()
        throws IncorrectFileNameException{
        try{
            File tmpFile=new File(fileDir+"/"+fileName);
            if(!tmpFile.exists()){
                throw new FileIsMissingException("File not Found",fileName);
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
            System.out.println("Failed to open file. "+ex+"\n\tFile: "+ex.getFileName()+"\n");
            if(!isCorrectFileName(ex.getFileName())){
                throw new IncorrectFileNameException("Incorrect filename : " + ex.getFileName() , ex);
            }
        }
        catch(IOException ex){}
    }
    public void deleteFile()
        throws IncorrectFileNameException{//add stuff
        try{
            File file=new File(fileDir+"/"+fileName);
            if(!file.exists()){
                throw new FileIsMissingException("File not Found",fileName,fileDir);
            }
            if(file.delete()) {
                System.out.println("Deleted the file: " + file.getName());
            }
            else {
                System.out.println("Failed to delete the file.");
            }
        }
        catch(FileIsMissingException ex){
            System.out.println("Failed to delete the file. "+ex+"\n\tFile:"+ex.getFileName()+"\n\tAt: "+ex.getFileDir()+"\n");
            if(!isCorrectFileName(ex.getFileName())){
                throw new IncorrectFileNameException("Incorrect filename : " + ex.getFileName() ,ex);
            }
        }
    }
    public boolean isCorrectFileName(String fileName){
        String illegalCaracters="@$%&\\/:*?\"'<>|~`#^+={}[];!";
        for (int i=0;i<fileName.length();i++) {
            for (int j=0;j<illegalCaracters.length();j++) {
                if(fileName.charAt(i)==illegalCaracters.charAt(j)){
                    return false;
                }
            }
        }
        return true;
    }
}
