package UI;

import javax.swing.*;
import java.awt.*;

/**
 * CustomPanel class is used as a group of variables
 * panel x,y sizes
 * Jpanel panel
 * current directory
 * ScrollBar(is not used yet)
 */
public class CustomPanel {
    JScrollPane scrollbar;
    public JPanel panel = new JPanel();

    /**
     * Constructor CustomPanel(int x,int y,int xSize,int ySize)
     * <p>
     * creates JPanel object and resizes it to given sizes
     *
     * @param x(int)-     x coordinate of panel,
     * @param y(int)-     y coordinate of panel,
     * @param width(int)- x size of panel,
     * @param height(int)- y size of panel
     */
    public CustomPanel(int x, int y, int width, int height) {// topPanel and filePanel
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(width, height));
        panel.setLocation(x, y);
        setSize(width, height);
    }

    /**
     * Constructor CustomPanel(int x,int y,int xSize,int ySize, Jtree tree)
     * <p>
     * creates JPanel object and adds to it JTree variable(file list)
     *
     * @param x(int)-      x coordinate of panel
     * @param y(int)-      y coordinate of panel
     * @param width(int)-  x size of panel
     * @param height(int)-  y size of panel
     * @param tree(JTree)- JTree Object containing fileList
     */
    public CustomPanel(int x, int y, int width, int height, JTree tree) {// left panel
        panel.setBounds(x, y, width, height);
        setSize(width, height);
        panel.setLayout(new BorderLayout());
        scrollbar = new JScrollPane(tree);
        scrollbar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollbar);
    }

    public void setSize(int width, int height) {
        panel.setSize(width, height);
    }

    public int getWidth() {
        return panel.getWidth();
    }

    public void setWidth(int width) {
        panel.setSize(width, panel.getHeight());
    }

    public int getHeight() {
        return panel.getHeight();
    }

    public void setHeight(int height) {
        panel.setSize(panel.getWidth(), height);
    }
}
