package Files;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

/**
 * FileManagement class is used to manage files:
 * create file/directory
 * delete file/directory
 * rename file/directory
 */
public class FileManagement {
    private static final String separator = FileSystems.getDefault().getSeparator();

    /**
     * Method createFile(String name,String directory)
     * <p>
     * This method tries to create file;
     * if it fails, It is reported
     *
     * @param name(String)-file name, directory(String) file location
     */
    public static void createFile(String name, String directory) {
        try {
            File file = new File(directory + separator + name);
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
     * <p>
     * This method tries to create folder;
     * if it fails, It is reported
     *
     * @param name(String)-folder name, directory(String) folder location
     */
    public static void createDirectory(String name, String directory) {
        File dir = new File(directory + separator + name);
        boolean create = dir.mkdir();
        if (create) {
            System.out.println("created directory " + name);
        } else {
            System.out.println("failed to create directory");
        }
    }

    /**
     * Method deleteFile(String name,String directory)
     * <p>
     * This method tries to delete file;
     * if it fails, It is reported
     *
     * @param name(String)-file name, directory(String) file location
     */
    public static void deleteFile(String name, String directory) {
        File file = new File(directory + separator + name);
        if (file.delete()) {
            System.out.println("Deleted the file: " + file.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }

    /**
     * Method deleteDirectory(String name,String directory)
     * <p>
     * This method tries to delete folder;
     * if it fails, It is reported
     *
     * @param name(String)-folder name, directory(String) folder location
     */
    public static void deleteDirectory(String name, String directory) {
        File dir = new File(directory + separator + name);
        if (dir.length() > 0)
            System.out.println("There are files in this directory. Do you want to delete this directory? y/n");

        boolean del = dir.delete();
        if (del) {
            System.out.println("deleted directory: " + name);
        } else {
            System.out.println("failed to create directory");
        }
    }

    /**
     * Method renameFile(String name,String newName,String directory)
     * <p>
     * This method tries to rename file;
     * if it fails, It is reported
     *
     * @param name(String)-file name,newName(String) new file name,
     *                          directory(String) file location
     */
    public static void renameFile(String name, String newName, String directory) {
        File oldFile = new File(directory + separator + name);
        File newFile = new File(directory + separator + newName);
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
     * <p>
     * This method tries to rename folder;
     * if it fails, It is reported
     *
     * @param name(String)-folder name,newName(String) new folder name,
     *                            directory(String) folder location
     */
    public static void renameDirectory(String name, String newName, String directory) {
        File oldDir = new File(directory + separator + name);
        File newDir = new File(directory + separator + newName);
        if (newDir.exists()) {
            System.out.println("directory exists");
            return;
        }
        boolean success = oldDir.renameTo(newDir);
        if (!success)
            System.out.println("failed to rename directory");
    }

    public static void recursiveFiles(String directory, DefaultMutableTreeNode head) {
        File f = new File(directory);
        if (!f.exists()) {
            System.out.println("Directory not found");
            return;
        }

        File[] files = f.listFiles();
        if (files == null)
            return;

        for (File file : files) {
            DefaultMutableTreeNode temp = new DefaultMutableTreeNode(file.getAbsolutePath());
            head.add(temp);

            if (file.isDirectory())
                recursiveFiles(file.getAbsolutePath(), temp);
        }
    }
}
