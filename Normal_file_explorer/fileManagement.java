import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class fileManagement{

    static void createFile(String name,String location){
        try {
            File file = new File(location+"/"+name);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    static void deleteFile(String name,String location){
        File file = new File(location+"/"+name);
        if (file.delete()) {
            System.out.println("Deleted the file: " + file.getName());
        }
        else {
            System.out.println("Failed to delete the file.");
        }
    }
    static void renameFile(String name,String newName, String location){
        File oldFile = new File(location+"/"+name);
        File newFile = new File(location+"/"+newName);
        if (newFile.exists()){
            System.out.println("file exists");
            return;
        }
        boolean success = oldFile.renameTo(newFile);
        if (!success) {
            System.out.println("failed to rename file");
        }
    }
    static void createDirectory(String name,String location){
        File dir = new File(location+"/"+name);
        boolean create=dir.mkdir();
        if(create){
            System.out.println("created directory");
        }
        else{
            System.out.println("failed to create directory");
        }
    }
    static void deleteDirectory(String name,String location) {
        File dir = new File(location+"/"+name);
        if(dir.length()>0){
            // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("There are files in this directory. Do you want to delete this directory? y/n");
            // String ans = br.readLine();
            // if(ans.equals("y")){

            // }
        }
        boolean del=dir.delete();
        if(del){
            System.out.println("deleted directory");
        }
        else{
            System.out.println("failed to create directory");
        }
    }
    static void renameDirectory(String name,String newName,String location){
        File oldDir = new File(location+"/"+name);
        File newDir = new File(location+"/"+newName);
        if (newDir.exists()){
            System.out.println("directory exists");
            return;
        }
        boolean success = oldDir.renameTo(newDir);
        if (!success) {
            System.out.println("failed to rename directory");
        }
    }
    //copy file && copy directory+ delete directory if something is in there

    // public static void main(String args[]){

    //     System.out.println("hellp");
    //     // createFile("kazkas.txt","/home/pijus/Desktop/empty");
    //     // createDirectory("kazkas.txt","/home/pijus/Desktop/empty");
    //     // deleteFile("kazkas.txt","/home/pijus/Desktop/empty");
    //     deleteDirectory("temp","/home/pijus/Desktop/empty");
    //     // renameFile("kazkas.txt","kazkas2.txt","/home/pijus/Desktop/empty");
    //     // renameDirectory("naujas_folderis","temp","/home/pijus/Desktop/empty");
    // }
}
