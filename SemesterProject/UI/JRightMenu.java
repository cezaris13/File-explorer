package UI;

import javax.swing.*;

public class JRightMenu {
    private final FileExplorerCallback fileExplorerCallback;
    private final JFrame frame;

    public JRightMenu(JFrame frame, FileExplorerCallback fileExplorerCallback) {
        this.frame = frame;
        this.fileExplorerCallback = fileExplorerCallback;
    }

    public JPopupMenu createFolderRightMenu() {
        JPopupMenu rightMenu = new JPopupMenu();
        JMenuItem renameDirectoryMenuItem = new JMenuItem("rename directory");
        JMenuItem deleteDirectoryMenuItem = new JMenuItem("delete directory");
        rightMenu.add(renameDirectoryMenuItem);
        rightMenu.add(deleteDirectoryMenuItem);
        renameDirectoryMenuItem.addActionListener(e -> {
            new Dialog(frame, "rename directory", DialogType.RenameDirectory, fileExplorerCallback.getCurrentSelectedFile());
            fileExplorerCallback.updateFiles();
        });
        deleteDirectoryMenuItem.addActionListener(e -> {
            new Dialog(frame, "delete directory", DialogType.DeleteDirectory, fileExplorerCallback.getCurrentSelectedFile());
            fileExplorerCallback.updateFiles();
        });
        return rightMenu;
    }

    public JPopupMenu createFileRightMenu() {
        JPopupMenu rightMenu = new JPopupMenu();
        JMenuItem renameFileMenuItem = new JMenuItem("rename file");
        JMenuItem deleteFileMenuItem = new JMenuItem("delete file");
        rightMenu.add(renameFileMenuItem);
        rightMenu.add(deleteFileMenuItem);

        renameFileMenuItem.addActionListener(e -> {
            new Dialog(frame, "rename file", DialogType.RenameFile, fileExplorerCallback.getCurrentSelectedFile());
            fileExplorerCallback.updateFiles();
        });
        deleteFileMenuItem.addActionListener(e -> {
            new Dialog(frame, "delete file", DialogType.DeleteFile, fileExplorerCallback.getCurrentSelectedFile());
            fileExplorerCallback.updateFiles();
        });
        return rightMenu;
    }

    public JPopupMenu createRightMenu() {
        JPopupMenu rightMenu = new JPopupMenu();
        JMenuItem newFileMenuItem = new JMenuItem("New file");
        JMenuItem newDirectoryMenuItem = new JMenuItem("New folder");
        rightMenu.add(newFileMenuItem);
        rightMenu.add(newDirectoryMenuItem);
        newFileMenuItem.addActionListener(e -> {
            new Dialog(frame, "enter file name", DialogType.NewFile);
            fileExplorerCallback.updateFiles();
        });
        newDirectoryMenuItem.addActionListener(e -> {
            new Dialog(frame, "enter directory name", DialogType.NewDirectory);
            fileExplorerCallback.updateFiles();
        });
        return rightMenu;
    }
}