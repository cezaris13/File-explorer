package Files;

import java.io.Serializable;
import javax.swing.*;
/**
 * CustomJLabelForders extends JLabel by adding Icon object,
 * which is used to give information about the folder icon and other data from Icon class
 *  */
public class CustomJLabelFolders extends JLabel implements Serializable {
    /**
     * Additional icon variable to store data about the folder icon */
    public Icon folderIcon=new Icon();
     /**
     * Creates New CustomJLabelFolders with given name,icon, alignment
     * name- folder name(it should not include fullPath to the file)
      * @param name - file name
      * @param icon - file icon
      * @param horizontalAlignment - JLabel allignment
      * */
    public CustomJLabelFolders(String name,ImageIcon icon,int horizontalAlignment){
        super(name,icon,horizontalAlignment);
        this.folderIcon=new Icon("/home/pijus/IdeaProjects/FIleExplorer/src/Files/folder.png",65,65);
    }
}
