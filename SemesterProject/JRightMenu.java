import UI.CustomPanel;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class JRightMenu {
    public JPopupMenu rightMenu;
    private JFrame frame;

    public JRightMenu(JFrame frame, RightMenuOptions rightMenuOption){
        this.frame = frame;
        switch(rightMenuOption) {
            case Edit -> createRightEditMenu();
            case EditFile -> createRightFileEditMenu();
            case EditFolder -> createRightFolderEditMenu();
        }
    }

    private void createRightEditMenu() {
        rightMenu = new JPopupMenu("Edit");
        JMenuItem newFileMenuItem = new JMenuItem("New file");
        JMenuItem newFolderMenuItem = new JMenuItem("New folder");
        rightMenu.add(newFileMenuItem);
        rightMenu.add(newFolderMenuItem);

        newFileMenuItem.addActionListener(e -> {
            new Dialog(frame, "enter file name", DialogType.NewFile, CustomPanel.directory);
            // updateFiles(CustomPanel.directory);
        });
        newFolderMenuItem.addActionListener(e -> {
            new Dialog(frame, "enter directory name", DialogType.NewDirectory, CustomPanel.directory);
            // updateFiles(CustomPanel.directory);
        });
    }

    private void createRightFileEditMenu() {
        rightMenu = new JPopupMenu("Edit");
        JMenuItem renameFileMenuItem = new JMenuItem("Rename file");
        JMenuItem deleteFileMenuItem = new JMenuItem("Delete file");
        rightMenu.add(renameFileMenuItem);
        rightMenu.add(deleteFileMenuItem);

        deleteFileMenuItem.addActionListener(e -> {
            // new Dialog(frame, "delete file", DialogType.DeleteFile, CustomPanel.directory, currSelected);
            // updateFiles(CustomPanel.directory);
        });

        renameFileMenuItem.addActionListener(e -> {
            // new Dialog(frame, "rename file", DialogType.RenameFile, CustomPanel.directory, currSelected);
            // updateFiles(CustomPanel.directory);
        });

    }

    private void createRightFolderEditMenu() {
        rightMenu = new JPopupMenu("Edit");
        JMenuItem deleteFolderMenuItem = new JMenuItem("Delete directory");
        JMenuItem renameFolderMenuItem = new JMenuItem("Rename directory");
        rightMenu.add(renameFolderMenuItem);
        rightMenu.add(deleteFolderMenuItem);

        renameFolderMenuItem.addActionListener(e -> {
            // new Dialog(frame, "rename directory", DialogType.RenameDirectory, CustomPanel.directory, currSelected);
            // updateFiles(CustomPanel.directory);
        });

        deleteFolderMenuItem.addActionListener(e -> {
            // new Dialog(frame, "delete directory", DialogType.DeleteDirectory, CustomPanel.directory, currSelected);
            // updateFiles(CustomPanel.directory);
        });

    }
}
