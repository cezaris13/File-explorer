import java.io.File;
import java.io.IOException;

public class fileManagement{
    void createFile(String name,String dirPath){
        try {
            File file = new File(dirPath+"/"+name);
            if (file.createNewFile()) {
                System.out.println("File created: " + name);
            }
            else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    void createDirectory(String name,String dirPath){
        File dir = new File(dirPath+"/"+name);
        boolean create = dir.mkdir();
        if(create){
            System.out.println("created directory "+name);
        }
        else{
            System.out.println("failed to create directory");
        }
    }
    void deleteFile(String name,String dirPath){
        File file = new File(dirPath+"/"+name);
        if (file.delete()) {
            System.out.println("Deleted the file: " + file.getName());
        }
        else {
            System.out.println("Failed to delete the file.");
        }
    }
    void deleteDirectory(String name,String dirPath) {
        File dir = new File(dirPath+"/"+name);
        if(dir.length()>0){
            System.out.println("There are files in this directory. Do you want to delete this directory? y/n");//todo with multiple files in directory
        }
        boolean del = dir.delete();
        if(del){
            System.out.println("deleted directory: "+name);
        }
        else{
            System.out.println("failed to create directory");
        }
    }
    void renameFile(String name,String newName, String dirPath){
        File oldFile = new File(dirPath+"/"+name);
        File newFile = new File(dirPath+"/"+newName);
        if (newFile.exists()){
            System.out.println("file exists");
            return;
        }
        boolean success = oldFile.renameTo(newFile);
        if (!success) {
            System.out.println("failed to rename file");
        }
    }
    void renameDirectory(String name,String newName,String dirPath){
        File oldDir = new File(dirPath+"/"+name);
        File newDir = new File(dirPath+"/"+newName);
        if (newDir.exists()){
            System.out.println("directory exists");
            return;
        }
        boolean success = oldDir.renameTo(newDir);
        if (!success) {
            System.out.println("failed to rename directory");
        }
    }
}
