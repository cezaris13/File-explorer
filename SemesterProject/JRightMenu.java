import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class JRightMenu {
    public JRightMenu(RightMenuOptions rightMenuOption){
        switch(rightMenuOption) {
            case EditFile -> createRightEditMenu();
            default -> throw new IllegalArgumentException("Unexpected value: " + rightMenuOption);
        }
    }

    private JPopupMenu createRightEditMenu() {
        JPopupMenu rightMenu = new JPopupMenu("Edit");
        JMenuItem newFileMenuItem = new JMenuItem("New file");
        JMenuItem newFolderMenuItem = new JMenuItem("New folder");
        rightMenu.add(newFileMenuItem);
        rightMenu.add(newFolderMenuItem);

        newFileMenuItem.addActionListener(e -> {
            // new Dialog(frame, "enter file name", "new file", CustomPanel.directory);
            // updateFiles(CustomPanel.directory);
        });
        newFolderMenuItem.addActionListener(e -> {
            // new Dialog(frame, "enter directory name", "new directory", CustomPanel.directory);
            // updateFiles(CustomPanel.directory);
        });

        return rightMenu;
    }
}
