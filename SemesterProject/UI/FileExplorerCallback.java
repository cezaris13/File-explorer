package UI;

import java.util.List;

public interface FileExplorerCallback {
    void updateFiles();

    void updateCurrentSelectedFile(String newDirectory);

    String getCurrentSelectedFile();

    List<CustomJLabel> getFolderList();

    List<CustomJLabel> getFileList();

    void addMouseListener(CustomJLabel jLabel);

    void addMouseListener(CustomJLabel jLabel, String directory);
}
