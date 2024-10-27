package UI;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.BorderLayout;

/**
 * CustomPanel class is used as a group of variables
 * panel x,y sizes
 * Jpanel panel
 * current directory
 * ScrollBar(is not used yet)
 */
public class CustomPanel {
    JScrollPane scrollbar;
    private int xSize = 0;
    private int ySize = 0;
    public JPanel panel = new JPanel();
    public static String directory;

    /**
     * Constructor CustomPanel(int x,int y,int xSize,int ySize)
     * <p>
     * creates JPanel object and resizes it to given sizes
     *
     * @param x(int)-     x coordinate of panel,
     * @param y(int)-     y coordinate of panel,
     * @param xSize(int)- x size of panel,
     * @param ySize(int)- y size of panel
     */
    public CustomPanel(int x, int y, int xSize, int ySize) {// topPanel and filePanel
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(xSize, ySize));
        panel.setLocation(x, y);
        setSize(xSize, ySize);
    }

    /**
     * Constructor CustomPanel(int x,int y,int xSize,int ySize, Jtree tree)
     * <p>
     * creates JPanel object and adds to it JTree variable(file list)
     *
     * @param x(int)-      x coordinate of panel
     * @param y(int)-      y coordinate of panel
     * @param xSize(int)-  x size of panel
     * @param ySize(int)-  y size of panel
     * @param tree(JTree)- JTree Object containing fileList
     */
    public CustomPanel(int x, int y, int xSize, int ySize, JTree tree) {// left panel
        panel.setBounds(x, y, xSize, ySize);
        setSize(xSize, ySize);
        panel.setLayout(new BorderLayout());
        scrollbar = new JScrollPane(tree);
        scrollbar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollbar);
    }

    /**
     * Method void setSize(int xSize,int ySize)
     * <p>
     * Changes Panel dimension,xSize and ySize variables
     *
     * @param xSize - x panel size
     * @param ySize - y panel size
     */
    public void setSize(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        panel.setSize(xSize, ySize);
    }

    /**
     * @return xSize(int)
     */
    public int getXSize() {
        return xSize;
    }

    /**
     * @return ySize(int)
     */
    public int getYSize() {
        return ySize;
    }
}
