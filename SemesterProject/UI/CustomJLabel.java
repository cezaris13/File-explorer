

import java.io.Serializable;
import javax.swing.*;

import Files.MyFile;

/**
 * CustomJLabel extends JLabel by adding MyFile object,
 * which is used to give information about the icon of the file and other data
 * from MyFile class
 */
public class CustomJLabel extends JLabel implements Serializable {
    /**
     * Additional file variable to store data about the file
     */
    public MyFile file = new MyFile();

    /**
     * Creates New CustomJLabel with given name,icon, alignment and MyFile object
     * name- file name(it should not include fullPath to the file)
     *
     * @param name                - file name
     * @param icon                - file icon
     * @param horizontalAlignment - JLabel allignment
     * @param file                - MyFile object type
     */
    public CustomJLabel(String name, ImageIcon icon, int horizontalAlignment, MyFile file) {
        super(name, icon, horizontalAlignment);
        this.file = file;
    }

    /**
     * Creates empty MyFile object
     */
    public CustomJLabel() {
        super();
        this.file = new MyFile();
    }
}
