import UI.*;

public interface FileExplorerCallback {
    void updateFiles();

    void updateCurrentDirectory(String newDirectory);

    String getCurrentDirectory();

    void addMouseListener(CustomJLabel jLabel, String fileName);

    void addMouseListener(CustomJLabel jLabel, String directory, String folderName);
}
