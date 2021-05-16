package Files;
import java.util.Date;
import java.io.IOException;
import java.io.File;

public abstract class EditableFile implements Editable{
    String exProgram;
    String fileDir;
    String fileName="";
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
    public void openFile(String exProgram,String fileName)
        throws FileIsMissingException{
        try{
            System.out.println(fileName);
            String replaceSpaces=fileName;
            System.out.println(replaceSpaces);
            File tmpFile=new File(fileDir+"/"+replaceSpaces);
            if(!isCorrectFileName(fileName)){
                throw new IncorrectFileNameException("Incorrect filename : " + fileName);
            }
            if(!tmpFile.exists()){
                throw new FileIsMissingException("File not Found",fileName);
            }
            Runtime.getRuntime().exec(exProgram+" "+fileDir+"/"+replaceSpaces);
        }
        catch(IOException ex){}
    }
    public void deleteFile()
        throws FileIsMissingException{//add stuff
        File file=new File(fileDir+"/"+fileName);
        if(!isCorrectFileName(fileName)){
            throw new IncorrectFileNameException("Incorrect filename : " + fileName);
        }
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
    public boolean isCorrectFileName(String fileName){
        String illegalCaracters="@$%&\\/:*?\"'<>|~`#^+={}[];!";
        for(int i=0;i<fileName.length();i++) {
            for(int j=0;j<illegalCaracters.length();j++) {
                if(fileName.charAt(i)==illegalCaracters.charAt(j)){
                    return false;
                }
            }
        }
        return true;
    }
    protected Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
