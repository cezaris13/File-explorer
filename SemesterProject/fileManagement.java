import java.io.File;
import java.io.IOException;

/**
 * FileManagement class is used to manage files:
 * create file/directory
 * delete file/directory
 * rename file/directory
 */
public class FileManagement {
    /**
     * Method createFile(String name,String directory)
     *
     * This method tries to create file;
     * if it fails, It is reported
     *
     * @param name(String)-file name, directory(String) file location
     */
    void createFile(String name, String directory) {
        try {
            File file = new File(directory + "/" + name);
            if (file.createNewFile()) {
                System.out.println("File created: " + name);
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Method createDirectory(String name,String directory)
     *
     * This method tries to create folder;
     * if it fails, It is reported
     *
     * @param name(String)-folder name, directory(String) folder location
     */
    void createDirectory(String name, String directory) {
        File dir = new File(directory + "/" + name);
        boolean create = dir.mkdir();
        if (create) {
            System.out.println("created directory " + name);
        } else {
            System.out.println("failed to create directory");
        }
    }

    /**
     * Method deleteFile(String name,String directory)
     *
     * This method tries to delete file;
     * if it fails, It is reported
     *
     * @param name(String)-file name, directory(String) file location
     */
    void deleteFile(String name, String directory) {
        File file = new File(directory + "/" + name);
        if (file.delete()) {
            System.out.println("Deleted the file: " + file.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }

    /**
     * Method deleteDirectory(String name,String directory)
     *
     * This method tries to delete folder;
     * if it fails, It is reported
     *
     * @param name(String)-folder name, directory(String) folder location
     */
    void deleteDirectory(String name, String directory) {
        File dir = new File(directory + "/" + name);
        if (dir.length() > 0) {
            System.out.println("There are files in this directory. Do you want to delete this directory? y/n");// todo
                                                                                                               // with
                                                                                                               // multiple
                                                                                                               // files
                                                                                                               // in
                                                                                                               // directory
        }
        boolean del = dir.delete();
        if (del) {
            System.out.println("deleted directory: " + name);
        } else {
            System.out.println("failed to create directory");
        }
    }

    /**
     * Method renameFile(String name,String newName,String directory)
     *
     * This method tries to rename file;
     * if it fails, It is reported
     *
     * @param name(String)-file name,newName(String) new file name,
     *                          directory(String) file location
     */
    void renameFile(String name, String newName, String directory) {
        File oldFile = new File(directory + "/" + name);
        File newFile = new File(directory + "/" + newName);
        if (newFile.exists()) {
            System.out.println("file exists");
            return;
        }
        boolean success = oldFile.renameTo(newFile);
        if (!success)
            System.out.println("failed to rename file");
    }

    /**
     * Method renameDirectory(String name,String newName,String directory)
     *
     * This method tries to rename folder;
     * if it fails, It is reported
     *
     * @param name(String)-folder name,newName(String) new folder name,
     *                            directory(String) folder location
     */
    void renameDirectory(String name, String newName, String directory) {
        File oldDir = new File(directory + "/" + name);
        File newDir = new File(directory + "/" + newName);
        if (newDir.exists()) {
            System.out.println("directory exists");
            return;
        }
        boolean success = oldDir.renameTo(newDir);
        if (!success)
            System.out.println("failed to rename directory");
    }
}
